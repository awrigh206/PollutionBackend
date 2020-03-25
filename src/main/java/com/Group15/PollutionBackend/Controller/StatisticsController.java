/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Controller;

import com.Group15.PollutionBackend.DataProcessing.Batch.CalculationsHelper;
import com.Group15.PollutionBackend.Model.Country;
import com.Group15.PollutionBackend.Model.Statistics;
import com.Group15.PollutionBackend.Repository.CityRepository;
import com.Group15.PollutionBackend.Service.CountryService;
import com.Group15.PollutionBackend.Service.StatisticsService;
import com.neovisionaries.i18n.CountryCode;
import java.util.List;
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
public class StatisticsController 
{
    @Autowired
    private StatisticsService statsService;
    @Autowired 
    private CountryService countryService;
    @Autowired 
    private CityRepository cityRepo;
    
    @GetMapping (path ="/currentStatsCity")
    public List<Statistics> getStats(@RequestParam(value="city")String city, @RequestParam(value="country") String country)
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
    
    @GetMapping (path ="/currentStatsCountry")
    public List<Statistics> getStats(@RequestParam(value="country") String country)
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
    
    @GetMapping (path ="/StatsCountry")
    public List<Statistics> getSavedStats(@RequestParam(value="country") String country)
    {
        try
        {
            List<Statistics> stats = statsService.findCountry(country);
            return stats;
        }
        
        catch(Exception e)
        {
            return null;
        }
        
    }
    
    @GetMapping (path ="/savedStats")
    public List<Statistics> getAllSaved()
    {
        try
        {
            List<Statistics> stats = statsService.getAll();
            return stats;
        }
        
        catch(Exception e)
        {
            return null;
        }
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler (NoSuchElementException.class)
    public String return400(NoSuchElementException ex)
    {
        return ex.getMessage();
    }
}
