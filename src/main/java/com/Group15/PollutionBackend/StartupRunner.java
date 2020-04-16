/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend;

import com.Group15.PollutionBackend.DataProcessing.Batch.RealTimeDataFetcherThread;
import com.Group15.PollutionBackend.DataProcessing.JSON.DataThread;
import com.Group15.PollutionBackend.DataProcessing.JSON.Results.CountryResult;
import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;
import com.Group15.PollutionBackend.Model.Country;
import com.Group15.PollutionBackend.Model.CountryCodes;
import com.Group15.PollutionBackend.Model.RealTime.ParsedData;
import com.Group15.PollutionBackend.Model.RealTime.PreFetch;
import com.Group15.PollutionBackend.Repository.CityRepository;
import com.Group15.PollutionBackend.Repository.ParsedDataRepository;
import com.Group15.PollutionBackend.Service.CountryService;
import com.Group15.PollutionBackend.Service.RealTimeService;
import com.Group15.PollutionBackend.Service.StatisticsService;
import com.Group15.PollutionBackend.Service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andrew Wright
 */
@Component
public class StartupRunner implements ApplicationListener<ContextRefreshedEvent>
{
    private final Log log = LogFactory.getLog(StartupRunner.class);
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryService countryService;
    
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired 
    private StatisticsService statsService;
    @Autowired
    private RetrieveData retData;
    @Autowired 
    private ParsedDataRepository parsedRepo;
    private ExecutorService exec = Executors.newFixedThreadPool(2);
    
    @Autowired 
    private RealTimeService realService;
    
    @Autowired 
    private UserService userService;
    private String token = "a3c205e5e20ddf248ae5a20e92b6a2b327132f95";
    @Autowired
    private ResourceLoader resourceLoader;
    @PostConstruct
    public void init()
    {
        
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) 
    {
        log.info("Retrieving data");
        countryService.deleteAll();
        cityRepository.deleteAll();
        userService.deleteAll();
        retData.setLimit(1200);
        fetchRealTimeData();
        //getData(retData);
    }
    
    //Schedule the method to run once every hour to keep the data fresh
    @Scheduled(fixedDelay = 3600000)
    public void fetchRealTimeData()
    {
        parsedRepo.deleteAll();
        Future future = exec.submit(new PreFetch(resourceLoader,token,retData));
        List<ParsedData> data = new ArrayList<>();
        try
        {
            while (!future.isDone())
            {
                Thread.sleep(10);
            }
            if(future.isDone())
            {
                data =  (List<ParsedData>)future.get();
            }
            
            for(ParsedData singleItem : data)
            {
                parsedRepo.saveAndFlush(singleItem);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
       
    }
    
    public void startBatchOperation(RetrieveData retData)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() 
        {
            public void run() 
            {
                int increment =6;
                int skipFactor =3;
                int truePageLimit = 100;
                retData.setLimit(10000);

                for(int i =1; i < truePageLimit;i=i+skipFactor+increment)
                {
                    for(String countryCode: CountryCodes.getIsoCodes())
                    {
                        try
                        {
                            Country currentCountry = countryService.findByCountryCode(countryCode);
                            log.info("Adding stuff to: " + currentCountry.getCountryCode());
                            countryService.fillInCityData(currentCountry, skipFactor, increment);
                            countryService.save(currentCountry);

                            System.gc();        
                        }

                        catch(NullPointerException e)
                        {
                            log.info("We got a null one");
                           continue;
                        }

                        catch (LazyInitializationException laz)
                        {
                            log.info("Lazy problem");
                        }


                    }
                }
            }
        });
    }
    
    private void getData(RetrieveData data)
    {
        String baseUrl = "https://api.openaq.org/v1/countries";
        int numberOfCountries = data.getMeta(baseUrl, CountryResult.class).getFound();
        int countriesPerThread = 100;
        //int threadCount = 1;
        int threadCount = numberOfCountries/countriesPerThread;
        if(threadCount<1)
            threadCount=1;
        
        try
        {
            long beginTime = System.nanoTime();
           /*
            Thread[] t = new Thread[threadCount];
            for(int i =0; i<threadCount;i++)
            {
                t[i] = new Thread(new DataThread(countriesPerThread,data,countryService,baseUrl,CountryResult.class,taskExecutor), "data"+i);
                t[i].start();
            }*/
            taskExecutor.execute(new DataThread(countriesPerThread,data,countryService,baseUrl,CountryResult.class,taskExecutor,statsService));
            taskExecutor.execute(new RealTimeDataFetcherThread(realService,retData));
            //add this back in for safety, can leave out for faster testing
            //waitForFinish(t);
            long endTime = System.nanoTime();
            log.info("That took about: " + (endTime - beginTime));
        }
        
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void waitForFinish(Thread[] threads) throws Exception
    {
        for (Thread t: threads)
        {
            if(t!=null)
                t.join();
        }
        log.info("All threads have now joined!");
        
    }   
}
