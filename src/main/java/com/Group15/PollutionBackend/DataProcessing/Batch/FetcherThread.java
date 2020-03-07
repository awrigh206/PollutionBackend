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
/**
 *
 * @author Andrew Wright
 */
@Transactional
public class FetcherThread implements Runnable
{
    RetrieveData data;
    CountryService countryService;
    private final int truePageLimit;
     private final Log log = LogFactory.getLog(FetcherThread.class);

    public FetcherThread(RetrieveData data, CountryService countryService, int truePageLimit) 
    {
        this.data = data;
        this.countryService = countryService;
        this.truePageLimit = truePageLimit;
    }
    

    @Override
    public void run() 
    {
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
                    currentCountry.fillInCityData(data,skipFactor, increment);
                    countryService.save(currentCountry);
                    
                    log.info("Added stuff to: " + currentCountry.getCountryCode());
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
