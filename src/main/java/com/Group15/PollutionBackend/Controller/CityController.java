/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Controller;

import com.Group15.PollutionBackend.Model.City;
import com.Group15.PollutionBackend.Repository.CityRepository;
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
@RequestMapping(path ="/City")
public class CityController 
{
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
    @RequestMapping(method = RequestMethod.GET)
    public City getUser(@RequestParam(value ="cityName")String cityName)
    {
        City city = cityRepo.findByName(cityName);
        return city;
        //return tourRatingRepository.findByPkTourId(tourId).stream().map(tourRating -> toDto(tourRating)).collect(Collectors.toList());
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler (NoSuchElementException.class)
    public String return400(NoSuchElementException ex)
    {
        return ex.getMessage();
    }
    
}
