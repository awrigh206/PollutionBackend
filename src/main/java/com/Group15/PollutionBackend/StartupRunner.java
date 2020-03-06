/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend;

import com.Group15.PollutionBackend.DataProcessing.Batch.FetcherThread;
import com.Group15.PollutionBackend.DataProcessing.JSON.DataThread;
import com.Group15.PollutionBackend.DataProcessing.JSON.Results.CountryResult;
import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;
import com.Group15.PollutionBackend.Repository.CityRepository;
import com.Group15.PollutionBackend.Service.CountryService;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
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
    
    @PostConstruct
    public void init()
    {
        
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) 
    {
        log.info("Retrieving data");
        cityRepository.deleteAll();
        countryService.deleteAll();
        RetrieveData retData = new RetrieveData(1200);
        getData(retData);
        startDataFetcherThread(retData);
    }
    
    private void startDataFetcherThread(RetrieveData retData)
    {
        int truePageLimit = 100;
        Thread t = new Thread(new FetcherThread(retData,countryService,truePageLimit));
        t.start();
    }
    
    @Transactional
    private void getData(RetrieveData data)
    {
        String baseUrl = "https://api.openaq.org/v1/countries";
        int numberOfCountries = data.getMeta(baseUrl, CountryResult.class).getFound();
        int countriesPerThread = 100;
        //int threadCount = 1;
        int threadCount = numberOfCountries/countriesPerThread;
        if(threadCount<1)
            threadCount=1;
        
        data.setLimit(countriesPerThread);
        
        try
        {
            long beginTime = System.nanoTime();
            Thread[] t = new Thread[threadCount];
            for(int i =0; i<threadCount;i++)
            {
                t[i] = new Thread(new DataThread(i+1,i+2,countriesPerThread,data,countryService,baseUrl,CountryResult.class), "data"+i);
                t[i].start();
            }
            //add this back in for safety, can leave out for faster testing
            waitForFinish(t);
            long endTime = System.nanoTime();
            log.info("That took about: " + (endTime - beginTime));
            data.setLimit(1200);
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
