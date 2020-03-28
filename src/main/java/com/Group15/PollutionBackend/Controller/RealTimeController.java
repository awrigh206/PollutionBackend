/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Controller;

import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;
import com.Group15.PollutionBackend.Service.RealTimeService;
import java.net.URL;
import java.util.NoSuchElementException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class RealTimeController 
{
    @Autowired 
    private RetrieveData retData;
    private final Log log = LogFactory.getLog(RealTimeController.class);
    @Autowired
    private RealTimeService realService;
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler (NoSuchElementException.class)
    public String return400(NoSuchElementException ex)
    {
        return ex.getMessage();
    }
    
    @GetMapping (path ="/realTime")
    public String getCountries(@RequestParam(value ="latitude")Double latitude, @RequestParam(value="longitude") Double longitude)
    {
        String token = "a3c205e5e20ddf248ae5a20e92b6a2b327132f95";
        try
        {
            String url = "https://api.waqi.info/feed/geo:"+latitude+";"+longitude+"/?token="+token;
            return realService.findByCoords(latitude, longitude).getJson();
        }
        
        catch (Exception e)
        {
            log.info(e.getMessage());
        }
        
        return null;
    }
}
