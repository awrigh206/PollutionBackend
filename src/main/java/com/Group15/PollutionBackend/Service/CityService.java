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
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrew Wright
 */
@Service
public class CityService implements IService 
{
    private final CityRepository cityRepository;
    
    @Autowired
    public CityService(CityRepository cityRepository) 
    {
        this.cityRepository = cityRepository;
    }
    
    public City createCity(String name, String country, String location, Double distance, HashSet<AirQuality> airQuality,Coordinates coords)
    {
        City city = cityRepository.findByName(name);
        //TourPackage tourPackage = tourPackageOpt.get();
        if(city == null)
        {
            return cityRepository.save(new City(name,country,location,distance,airQuality,coords));
        }
        else
        {
            return null;
        }
        
    }
    
    @Override
    public Object createNew(Object toAdd)
    {

        return cityRepository.save((City)toAdd);
        
    }
            
    public long total()
    {
        return cityRepository.count();
    }
    
}
