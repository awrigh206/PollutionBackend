/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Service;

import com.Group15.PollutionBackend.Model.AirQuality;
import com.Group15.PollutionBackend.Model.City;
import com.Group15.PollutionBackend.Model.Coordinates;
import com.Group15.PollutionBackend.Repository.CityRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrew Wright
 */
@Service
public class CityService 
{
    private CityRepository cityRepository;
    
    @Autowired
    public CityService(CityRepository cityRepository) 
    {
        this.cityRepository = cityRepository;
    }
    
    public City createCity(String name, String country, String location, Integer count, ArrayList<AirQuality> airQuality,Integer x, Integer y)
    {
        City city = cityRepository.findByName(name);
        //TourPackage tourPackage = tourPackageOpt.get();
        if(city == null)
        {
            return cityRepository.save(new City(name,country,location,count,airQuality,x,y));
        }
        else
        {
            return null;
        }
        
    }
            
    public long total()
    {
        return cityRepository.count();
    }
    
}
