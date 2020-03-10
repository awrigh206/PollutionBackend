/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.Batch;
import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;
import com.Group15.PollutionBackend.Model.Country;
import com.Group15.PollutionBackend.Model.CountryCodes;
import com.Group15.PollutionBackend.Service.CountryService;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
/**
 *
 * @author Andrew Wright
 */
@Transactional
@Component
public class FetcherThread implements Runnable
{
    RetrieveData data;
    CountryService countryService;
    private final Integer truePageLimit;
    private final Log log = LogFactory.getLog(FetcherThread.class);
    private final TaskExecutor taskExecutor;

    public FetcherThread(TaskExecutor taskExecutor,RetrieveData data, CountryService countryService, Integer truePageLimit) 
    {
        this.data = data;
        this.countryService = countryService;
        this.truePageLimit = truePageLimit;
        this.taskExecutor =taskExecutor;
    }
    

    @Override
    public void run() 
    {
        log.info("This thing actually gets called");
        int increment =6;
        int skipFactor =3;
        data.setLimit(10000);
        
        for(int i =1; i < truePageLimit;i=i+skipFactor+increment)
        {
            for(String countryCode: CountryCodes.getIsoCodes())
            {
                try
                {
                    Country currentCountry = countryService.findByCountryCode(countryCode);
                    log.info("Adding stuff to: " + currentCountry.getCountryCode());
                    currentCountry.fillInCityData(data,skipFactor, increment);
                    countryService.save(currentCountry);
                    
                    
                    
                    System.gc();
                }
                
                catch(NullPointerException e)
                {
                    log.info("We got a null one");
                   continue;
                }
                

            }
        }

    }
    
    
    
}
