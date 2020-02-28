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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andrew Wright
 */
@Service
public class CountryService implements IService
{
    
    private final CountryRepository countryRepository;
    private final Log log = LogFactory.getLog(CountryService.class);
    
    
    @Autowired
    public CountryService(CountryRepository countryRepository) 
    {
        this.countryRepository = countryRepository;
    }

    @Override
    public IRepo createNew(IRepo toAdd) 
    {
        return countryRepository.save((Country)toAdd);
    }

    @Override
    public long total() 
    {
        return countryRepository.count();
    }

    @Override
    public void createNew(ResultAbs toAdd, RetrieveData data) 
    {
        CountryResult countryResult = (CountryResult)toAdd;
        for(Country current: countryResult.getCountries())
        {
            current.fillInCityData(data);
            createNew(current);
        }
        System.gc();
        
    }

    @Override
    public void createNew(ResultAbs toAdd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
