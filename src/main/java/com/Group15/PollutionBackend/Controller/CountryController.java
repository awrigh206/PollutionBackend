/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Controller;

import com.Group15.PollutionBackend.Model.Country;
import com.Group15.PollutionBackend.Repository.CountryRepository;
import com.Group15.PollutionBackend.Service.CountryService;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class CountryController 
{
    private CountryRepository countryRepo;
    @Autowired
    private CountryService countryService;
    protected CountryController()
    {
        
    }
    @Autowired
    public CountryController(CountryRepository countryRepo)
    {
        this.countryRepo = countryRepo;
    }
    
    @GetMapping (path ="/country")
    public Country getCountry(@RequestParam(value ="countryCode")String countryCode)
    {
        return countryService.findByCountryCode(countryCode);
    }
    
    @GetMapping (path ="/countries")
    public ArrayList<Country> getCountries()
    {
        return countryRepo.findAll();
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler (NoSuchElementException.class)
    public String return400(NoSuchElementException ex)
    {
        return ex.getMessage();
    }
    
    
}
