/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Controller;

import com.Group15.PollutionBackend.DataProcessing.Batch.CalculationsHelper;
import com.Group15.PollutionBackend.Model.AirQuality;
import com.Group15.PollutionBackend.Model.City;
import com.Group15.PollutionBackend.Repository.CityRepository;
import com.Group15.PollutionBackend.Service.StartupRunner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    private final Log log = LogFactory.getLog(CityController.class);
    CityRepository cityRepo;
    @Autowired
    public CityController (CityRepository cityRepo)
    {
        this.cityRepo = cityRepo;
    }

    protected CityController() {
    }
    
    /**
     * Will return the city specified by it's name, in a URL GET request, in a JSON format 
     * @param cityName
     * @return 
     */
    @RequestMapping(method = RequestMethod.GET, path = "/City")
    public ArrayList<City> getCity(@RequestParam(value ="cityName")String cityName)
    {
        ArrayList<City> cities = cityRepo.findAllByName(cityName);
        return cities;
        //return tourRatingRepository.findByPkTourId(tourId).stream().map(tourRating -> toDto(tourRating)).collect(Collectors.toList());
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/Cities")
    public ArrayList<City> getCities()
    {
        ArrayList<City> cities = new ArrayList<>();
        Iterator it = cityRepo.findAll().iterator();
        while (it.hasNext())
        {
            cities.add((City)it.next());
        }
        
        return cities;
        //return tourRatingRepository.findByPkTourId(tourId).stream().map(tourRating -> toDto(tourRating)).collect(Collectors.toList());
    }
    
    @RequestMapping(method = RequestMethod.GET, path="/country")
    public City[] getCities(@RequestParam(value="country")String country)
    {
        return cityRepo.findByCountry(country);
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler (NoSuchElementException.class)
    public String return400(NoSuchElementException ex)
    {
        return ex.getMessage();
    }
    
    @RequestMapping(method = RequestMethod.GET, path="/test")
    public void getTest()
    {
        averagingTest();
    }
    
    private City averagingTest()
    {
        City avg = CalculationsHelper.averageCitywide(cityRepo.findAllByName("Glasgow"),"Glasgow");
        return avg;
    }
    
}
