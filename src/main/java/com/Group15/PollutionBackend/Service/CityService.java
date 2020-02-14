/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Service;

import com.Group15.PollutionBackend.DataProcessing.JSON.Results.LatestResult;
import com.Group15.PollutionBackend.DataProcessing.JSON.Results.ResultAbs;
import com.Group15.PollutionBackend.Model.AirQuality;
import com.Group15.PollutionBackend.Model.City;
import com.Group15.PollutionBackend.Model.Coordinates;
import com.Group15.PollutionBackend.Repository.CityRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Group15.PollutionBackend.DataProcessing.JSON.IRepo;

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
    
    public City createCity(String name, String country, String location, Double distance, ArrayList<AirQuality> airQuality,Coordinates coords)
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
    
    public List<City> findAllByName(String name)
    {
        //TODO: Add code here to check that it is the same city, check it is in the same country. In extreme cases, may need to check coordinates are in proximity
        return cityRepository.findAllByName(name);
    }
    
    @Override
    public IRepo createNew(IRepo toAdd)
    {

        return cityRepository.save((City)toAdd);
        
    }
            
    public long total()
    {
        return cityRepository.count();
    }

    @Override
    public void createNew(ResultAbs toAdd) 
    {
        LatestResult latest = (LatestResult)toAdd;
        for(City current: latest.getResults())
        {
            createNew(current);
        }
    }
    
}
