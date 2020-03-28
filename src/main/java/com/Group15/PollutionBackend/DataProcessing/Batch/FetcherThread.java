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
import com.Group15.PollutionBackend.Service.IService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 *
 * @author Andrew Wright
 */
@Component
public class FetcherThread implements Runnable
{
    RetrieveData data;
    @Autowired
    IService service;
    private final Integer truePageLimit;
    private final Log log = LogFactory.getLog(FetcherThread.class);
    private final Range range;

    public FetcherThread(IService countryService,RetrieveData retdata, Integer truePageLimit, Range range) 
    {
        this.truePageLimit = truePageLimit;
        this.data = retdata;
        this.service = countryService;
        this.range = range;
    }
    

    @Override
    public void run() 
    {
        while(true)
        {
            try
            {
                updateInformation();
                Thread.sleep(3600000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
        }

    }
    
    protected void updateInformation()
    {
        int increment =1;
        int skipFactor =2;
        data.setLimit(10000);
        
        for(int i =1; i < truePageLimit;i=i+skipFactor+increment)
        {
            for (int y =range.getStart(); y< CountryCodes.getIsoCodes().size() && y< range.getEnd(); y++)
            {
                store(skipFactor, increment, y);
            }
        }
    }
    
    protected void store(int skipFactor, int increment, int country)
    {
        CountryService countryService = (CountryService) service;
        try
        {
            Country currentCountry = countryService.findByCountryCode(CountryCodes.getIsoCodes().get(country));
            countryService.delete(currentCountry);
            countryService.fillInCityData(currentCountry, skipFactor, increment);
            countryService.save(currentCountry);

            System.gc();
        }

        catch(NullPointerException e)
        {
            log.info("We got a null one");
        }

        catch (LazyInitializationException laz)
        {
            log.info("Lazy problem");
        }
}
    
    
    
}
