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
import com.Group15.PollutionBackend.DataProcessing.JSON.Location;
import com.Group15.PollutionBackend.DataProcessing.JSON.Results.LocationResult;
import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;
import com.Group15.PollutionBackend.Model.AirQuality;
import com.Group15.PollutionBackend.Model.City;
import com.Group15.PollutionBackend.Model.CountryCodes;
import com.Group15.PollutionBackend.Repository.AirQualityRepository;
import com.Group15.PollutionBackend.Repository.CityRepository;
import com.Group15.PollutionBackend.Repository.CoordinatesRepository;
import java.util.ArrayList;
import java.util.List;
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
    private RetrieveData retData;
    @Autowired
    private AirQualityRepository airRepo;
    @Autowired 
    private CoordinatesRepository coordRepo;
    @Autowired
    private CityRepository cityRepo;
    
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
        Country country = (Country) toAdd;
        /*
        for(City currentCity : country.getCitiesWithinCountry())
        {
            coordRepo.save(currentCity.getCoordinates());
            for (AirQuality currentAirQuality : currentCity.getAirQuality())
            {
                airRepo.save(currentAirQuality);
            }
            cityRepo.save(currentCity);
        }*/
        return countryRepository.saveAndFlush(country);
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
        //country.fillInCityData(data,3,6);
        fillInCityData(country, 3, 6);
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
            //current.fillInCityData(data,3,6);
            fillInCityData(current, 1, 1);
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
    
    @Transactional
    public Country findByCountryCode(String code)
    {
        Country country =countryRepository.findByCountryCode(code);
        List<City> citiesInCountry = country.getCitiesWithinCountry();

        return country;
        
    }
    
    public Country save(Country country)
    {
        return countryRepository.saveAndFlush(country);
    }
    
    @Transactional
    public void fillInCityData(Country currentCountry,int skipFactor, int pageJump)
    {
        retData.setLimit(1000);
        int pageReached = currentCountry.getPageReached();
        int pageLimit = pageReached +pageJump;
        pageReached = pageLimit;
        currentCountry.setPageReached(pageReached);
        //RetrieveData data = new RetrieveData(1200);
        String url = "https://api.openaq.org/v1/measurements";
        int totalPages = retData.getTotalPages(url,LocationResult.class);
        
        try
        {
            
            for(int i =pageReached; i<totalPages+1;i=i+skipFactor)
            {
                if(i>pageLimit)
                    break;
                LocationResult result = (LocationResult)retData.processPageSingle(url, i, LocationResult.class, currentCountry.getCountryCode());
                for(Location current : result.getLocations())
                {
                    City relevantCity = findCity (current,currentCountry);
                    if(relevantCity != null)
                        relevantCity.addQuality(current.getAirQuality());
                    else
                        currentCountry.addCity((current.toNewCity()));
                }
            }
            
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @Transactional
    public City findCity(Location location, Country country)
    {
        for(City current : country.getCitiesWithinCountry())
        {
            if(current.getName().equalsIgnoreCase(location.getCity()))
            {
                current.getAirQuality();
                return current;
            }
        }
        return null;
    }
    
    
    
}
