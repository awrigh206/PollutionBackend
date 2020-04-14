/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Controller;

import com.Group15.PollutionBackend.DTO.UserDto;
import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;
import com.Group15.PollutionBackend.Model.RealTime.RealTimeData;
import com.Group15.PollutionBackend.Service.RealTimeService;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andrew Wright
 */
@RestController
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class RealTimeController 
{
    @Autowired 
    private RetrieveData retData;
    private final Log log = LogFactory.getLog(RealTimeController.class);
    @Autowired
    private RealTimeService realService;
    private String token = "a3c205e5e20ddf248ae5a20e92b6a2b327132f95";
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler (NoSuchElementException.class)
    public String return400(NoSuchElementException ex)
    {
        return ex.getMessage();
    }
    
    @GetMapping (path ="/realTime")
    public String getCountries(@RequestParam(value ="latitude")Double latitude, @RequestParam(value="longitude") Double longitude)
    {    
        try
        {
            String url = "https://api.waqi.info/feed/geo:"+latitude+";"+longitude+"/?token="+token;
            return retData.processRealTime(new URL(url)).getJson();
        }

        catch (Exception e)
        {
            log.info(e.getMessage());
            return null;
        }
    }
    
    @PostMapping (path="/updateToken")
    public void updateToken(@RequestBody String token)
    {
        this.token = token;
    }
    
    @GetMapping (path ="/realTimeTest")
    public Object getCountriesTest(@RequestParam(value ="latitude")Double latitude, @RequestParam(value="longitude") Double longitude)
    {   
        try
        {
            String url = "https://api.waqi.info/feed/geo:"+latitude+";"+longitude+"/?token="+token;
            //return retData.parseRealTime(new URL(url));
            return retData.parseRealTime(new URL(url)).toJson();
        }

        catch (Exception e)
        {
            log.info(e.getMessage());
            return null;
        }
    }
    
    @GetMapping (path ="/allRealTime")
    public List<RealTimeData> getAllTime()
    {
        String token = "a3c205e5e20ddf248ae5a20e92b6a2b327132f95";
        try
        {
            //String url = "https://api.waqi.info/feed/geo:"+latitude+";"+longitude+"/?token="+token;
            return realService.findAll();
        }
        
        catch (Exception e)
        {
            log.info(e.getMessage());
        }
        
        return null;
    }
}
