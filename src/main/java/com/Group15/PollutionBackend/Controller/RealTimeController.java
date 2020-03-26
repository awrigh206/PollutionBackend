/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Controller;

import com.Group15.PollutionBackend.Model.Coordinates;
import java.util.NoSuchElementException;
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
public class RealTimeController 
{
    
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler (NoSuchElementException.class)
    public String return400(NoSuchElementException ex)
    {
        return ex.getMessage();
    }
    
    @GetMapping (path ="/realTime")
    public Object getCountries(@RequestParam(value ="coordinates")Coordinates coords)
    {
        return null;
    }
}
