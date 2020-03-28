/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.Batch;

import com.Group15.PollutionBackend.Model.Country;
import com.Group15.PollutionBackend.Model.CountryCodes;
import com.Group15.PollutionBackend.Model.Statistics;
import com.Group15.PollutionBackend.Service.CountryService;
import com.Group15.PollutionBackend.Service.StatisticsService;
import com.neovisionaries.i18n.CountryCode;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andrew Wright
 */
@Component
public class StatisticsThread implements Runnable
{
    private final CountryService countryService;
    private final StatisticsService statsService;
    private final Log log = LogFactory.getLog(StatisticsThread.class);

    public StatisticsThread(CountryService countryService, StatisticsService statsService) {
        this.countryService = countryService;
        this.statsService = statsService;
    }

    @Override
    public void run() 
    {
        log.info("Stats thread is now running");
        statsService.deleteALl();
        try
        {
            while (true)
            {
                for(String currentCode: CountryCodes.getIsoCodes())
                {
                    try
                    {
                        calculateCountry(currentCode);
                    }
                    catch(NullPointerException e)
                    {
                        continue;
                    }
                    
                    
                }
                Thread.sleep(60000);
            }
        }
        
        catch(InterruptedException i)
        {
            i.printStackTrace();
        }
        
        
    }
    @Transactional
    private void calculateCountry(String countryCode)
    {
        CountryCode code = CountryCode.getByCode(countryCode);
        Country countryModel = countryService.findByCountryCode(code.getAlpha2());
        List<Statistics> stats = CalculationsHelper.stats(countryModel);
        saveSats(stats, countryModel);
    }
    @Transactional
    private void saveSats(List<Statistics> stats, Country country)
    {
        for (Statistics current : stats)
        {
            //statsService.deleteCountryStats(country.getCountryCode());
            statsService.deleteStat(current);
            statsService.saveStat(current);
        }
    }
    
}
