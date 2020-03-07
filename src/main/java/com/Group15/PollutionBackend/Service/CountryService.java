/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Service;

import com.Group15.PollutionBackend.DataProcessing.JSON.Results.CountryResult;
import com.Group15.PollutionBackend.DataProcessing.JSON.Results.ResultAbs;
import com.Group15.PollutionBackend.Model.Country;
import com.Group15.PollutionBackend.Repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Group15.PollutionBackend.DataProcessing.JSON.IRepo;
import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;
import com.Group15.PollutionBackend.Model.CountryCodes;
import java.util.ArrayList;
import javax.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andrew Wright
 */
@Service
@Transactional
public class CountryService implements IService
{
    
    private final CountryRepository countryRepository;
    private final Log log = LogFactory.getLog(CountryService.class);
    
      
    
    
    @Autowired
    public CountryService(CountryRepository countryRepository) 
    {
        this.countryRepository = countryRepository;
    }
    
    public void deleteAll()
    {
        countryRepository.deleteAll();
    }

    @Override
    @Transactional
    public IRepo createNew(IRepo toAdd) 
    {
            
        System.gc();
        return countryRepository.saveAndFlush((Country)toAdd);
    }

    @Override
    public long total() 
    {
        return countryRepository.count();
    }
    
    @Transactional
    public Country fetchAdditonalMesurementsForCountry(Country country, RetrieveData data) 
    {
        data.setLimit(500);
        country.fillInCityData(data,3,6);
        return countryRepository.saveAndFlush(country);
    }

    @Override
    @Transactional
    public void createNew(ResultAbs toAdd, RetrieveData data) 
    {
        data.setLimit(1000);
        CountryResult countryResult = (CountryResult)toAdd;
        for(Country current: countryResult.getCountries())
        {
            current.fillInCityData(data,3,6);
            createNew(current);
            CountryCodes.addCode(current.getCountryCode());
        } 
        countryResult = null;
        
    }
    
    @Transactional
    public void delete(Country toDelete) 
    {
        countryRepository.delete(toDelete);
    }


    @Override
    public void createNew(ResultAbs toAdd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Country findByCountryCode(String code)
    {
        return countryRepository.findByCountryCode(code);
        
    }
    
    public Country save(Country country)
    {
        return countryRepository.save(country);
    }
    
    
    
}
