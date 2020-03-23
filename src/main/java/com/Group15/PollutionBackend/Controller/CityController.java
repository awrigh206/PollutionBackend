/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Controller;

import com.Group15.PollutionBackend.DataProcessing.Batch.CalculationsHelper;
import com.Group15.PollutionBackend.Model.AirQuality;
import com.Group15.PollutionBackend.Model.City;
import com.Group15.PollutionBackend.Model.Country;
import com.Group15.PollutionBackend.Model.Statistics;
import com.Group15.PollutionBackend.Repository.AirQualityRepository;
import com.Group15.PollutionBackend.Repository.CityRepository;
import com.Group15.PollutionBackend.Repository.CoordinatesRepository;
import com.Group15.PollutionBackend.Repository.CountryRepository;
import com.Group15.PollutionBackend.Service.CountryService;
import com.neovisionaries.i18n.CountryCode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andrew Wright
 */
@RestController
@CrossOrigin
public class CityController 
{
    CountryRepository countryRepo;
    CityRepository cityRepo;
    CountryService countryService;
    
    @Autowired
    private AirQualityRepository airRepo;
    @Autowired 
    private CoordinatesRepository coordRepo;
    
    @Autowired
    public CityController (CityRepository cityRepo, CountryRepository countryRepo, CountryService countryService)
    {
        this.cityRepo = cityRepo;
        this.countryRepo = countryRepo;
        this.countryService =  countryService;
    }

    protected CityController() {
    }
    
    /**
     * Will return the city specified by it's name, in a URL GET request, in a JSON format 
     * @param cityName
     * @return 
     */
    @GetMapping (path ="/city")
    public ArrayList<City> getCity(@RequestParam(value ="cityName")String cityName)
    {
        ArrayList<City> cities = cityRepo.findAllByName(cityName);
        return cities;
    }
    @GetMapping (path ="/airs")
    public ArrayList<AirQuality> getAirs()
    {
        ArrayList<AirQuality> air = new ArrayList<>();
        Iterator it = airRepo.findAll().iterator();
        while (it.hasNext())
        {
            air.add((AirQuality)it.next());
        }
        
        return air;
    }
    
    @GetMapping (path ="/cities")
    public ArrayList<City> getCities()
    {
        ArrayList<City> cities = new ArrayList<>();
        Iterator it = cityRepo.findAll().iterator();
        while (it.hasNext())
        {
            cities.add((City)it.next());
        }
        
        return cities;
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler (NoSuchElementException.class)
    public String return400(NoSuchElementException ex)
    {
        return ex.getMessage();
    }
    
    @GetMapping (path ="/statsCity")
    public Object getStats(@RequestParam(value="city")String city, @RequestParam(value="country") String country)
    {
        try
        {
            CountryCode code = CountryCode.getByCode(country);
            List<Statistics> stats = CalculationsHelper.stats(cityRepo.findAllByNameAndCountryCode(city,code.getAlpha2()));
            return stats;
        }
        
        catch(Exception e)
        {
            return null;
        }
        
    }
    
    @GetMapping (path ="/statsCountry")
    public Object getStats(@RequestParam(value="country") String country)
    {
        try
        {
            CountryCode code = CountryCode.getByCode(country);
            Country countryModel = countryService.findByCountryCode(code.getAlpha2());
            List<Statistics> stats = CalculationsHelper.stats(countryModel);
            return stats;
        }
        
        catch(Exception e)
        {
            return null;
        }
        
    }
    

    
}
