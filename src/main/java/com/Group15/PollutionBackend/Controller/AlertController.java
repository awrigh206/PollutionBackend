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
import com.Group15.PollutionBackend.Model.City;
import com.Group15.PollutionBackend.Repository.CityRepository;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import java.io.File;
import java.net.InetAddress;
import java.util.NoSuchElementException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class AlertController 
{
    private final Log log = LogFactory.getLog(AlertController.class);
    private IAlert alert;
    private final CityRepository cityRepo;
    
    @Autowired
    public AlertController(CityRepository cityRepo) 
    {
        this.cityRepo = cityRepo;
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping (path ="/email")
    public void sendEmailAlert( @RequestBody AlertDto alertDto) 
    {
        alert = new EmailAlert();
        alert.sendAlert(alertDto.getMessage(), alertDto.getAddress());
    }
    
    @PostMapping (path ="/text")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendTextAlert( @RequestBody AlertDto alertDto) 
    {
        alert = new TextAlert();
        alert.sendAlert(alertDto.getMessage(), alertDto.getAddress());
    }
    
    @GetMapping (path ="/location")
    public Object getCity(@RequestParam(value ="ip")String ip)
    {
        try
        {
            File database = new File("src/main/resources/GeoLite2-City_20200211/GeoLite2-City.mmdb");
            DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
            InetAddress ipAddress = InetAddress.getByName(ip);
            CityResponse response = dbReader.city(ipAddress);
            String cityName = response.getCity().getName();
            String countryCode = response.getCountry().getIsoCode();
            
            City city = cityRepo.findByNameAndCountry(cityName,countryCode);
            if(city!=null)
                return city;
            
            else
                return "This system has no data on the nearest city to you which is: " + response.getCity().getName();
            
            
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
