/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Controller;

import Alerts.EmailAlert;
import Alerts.IAlert;
import Alerts.TextAlert;
import com.Group15.PollutionBackend.DTO.AlertDto;
import com.Group15.PollutionBackend.DTO.UserDto;
import com.Group15.PollutionBackend.Model.City;
import com.Group15.PollutionBackend.StartupRunner;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
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
public class AlertController 
{
    private final Log log = LogFactory.getLog(AlertController.class);
    IAlert alert;
    TextAlert textAlert;
    public AlertController() 
    {
    }
    
    @RequestMapping(method = RequestMethod.POST, path = "/email")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendEmailAlert( @RequestBody AlertDto alertDto) 
    {
        alert = new EmailAlert();
        alert.sendAlert(alertDto.getMessage(), alertDto.getAddress());
    }
    
    @RequestMapping(method = RequestMethod.POST, path = "/text")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendTextAlert( @RequestBody AlertDto alertDto) 
    {
        alert = new TextAlert();
        alert.sendAlert(alertDto.getMessage(), alertDto.getAddress());
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/location")
    public String getCity(@RequestParam(value ="ip")String ip)
    {
        try
        {
            File database = new File("src/main/resources/GeoLite2-City_20200211/GeoLite2-City.mmdb");
            DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
            InetAddress ipAddress = InetAddress.getByName(ip);
            CityResponse response = dbReader.city(ipAddress);
            return response.getCity().getName();
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
