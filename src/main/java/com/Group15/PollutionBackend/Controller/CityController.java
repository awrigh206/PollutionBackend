/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Controller;

import com.Group15.PollutionBackend.DataProcessing.Batch.CalculationsHelper;
import com.Group15.PollutionBackend.Model.City;
import com.Group15.PollutionBackend.Model.Country;
import com.Group15.PollutionBackend.Model.Statistics;
import com.Group15.PollutionBackend.Repository.CityRepository;
import com.Group15.PollutionBackend.Repository.CountryRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andrew Wright
 */
@RestController
@RequestMapping()
public class CityController 
{
    CountryRepository countryRepo;
    CityRepository cityRepo;
    
    @Autowired
    public CityController (CityRepository cityRepo, CountryRepository countryRepo)
    {
        this.cityRepo = cityRepo;
        this.countryRepo = countryRepo;
    }

    protected CityController() {
    }
    
    /**
     * Will return the city specified by it's name, in a URL GET request, in a JSON format 
     * @param cityName
     * @return 
     */
    @RequestMapping(method = RequestMethod.GET, path = "/city")
    public ArrayList<City> getCity(@RequestParam(value ="cityName")String cityName)
    {
        ArrayList<City> cities = cityRepo.findAllByName(cityName);
        return cities;
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/cities")
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
    
    @RequestMapping(method = RequestMethod.GET, path="/averageCity")
    public City getAverage(@RequestParam(value="city")String city, @RequestParam(value="country") String country)
    {
        City avg = CalculationsHelper.averageCityWide(cityRepo.findAllByNameAndCountry(city,country),city);
        return avg;
    }
    
    @RequestMapping(method = RequestMethod.GET, path="/averageCountry")
    public City getAverage(@RequestParam(value="country") String country)
    {
        City avg = CalculationsHelper.averageCityWide(cityRepo.findAllByCountry(country),country);
        return avg;
    }
    
    @RequestMapping(method = RequestMethod.GET, path="/statsCity")
    public List<Statistics> getStats(@RequestParam(value="city")String city, @RequestParam(value="country") String country)
    {
        List<Statistics> stats = CalculationsHelper.stats(cityRepo.findAllByNameAndCountry(city,country));
        return stats;
    }
    
    @RequestMapping(method = RequestMethod.GET, path="/statsCountry")
    public List<Statistics> getStats(@RequestParam(value="country") String country)
    {
        Country countryModel = countryRepo.findByCountryCode(country);
        List<Statistics> stats = CalculationsHelper.stats(countryModel);
        return stats;
    }
    
    private City averagingTest()
    {
        City avg = CalculationsHelper.averageCityWide(cityRepo.findAllByNameAndCountry("Glasgow","GB"),"Glasgow");
        return avg;
    }
    
}
