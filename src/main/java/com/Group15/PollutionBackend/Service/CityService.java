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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Group15.PollutionBackend.DataProcessing.JSON.IRepo;
import com.Group15.PollutionBackend.DataProcessing.JSON.Location;
import com.Group15.PollutionBackend.DataProcessing.JSON.Results.LocationResult;
import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;
import com.Group15.PollutionBackend.Model.Country;

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
        LocationResult latest = (LocationResult)toAdd;
        for(Location current: latest.getLocations())
        {
            City relevantCity;
            try
            {
                 relevantCity = cityRepository.findByNameAndCountry(current.getCity(), current.getCountry());
            }
            
            catch(Exception e)
            {
                relevantCity = null;
            }
            
            if(relevantCity == null)
            {
                cityRepository.save(current.toNewCity());
            }
            else 
            {
                relevantCity.addQuality(current.getAirQuality());
                cityRepository.deleteById(relevantCity.getCityId());
                cityRepository.save(relevantCity);
            }
        }
    }
    
    /*
    public void createCitiesInCountry(Country country, RetrieveData data)
    {
        country.fillInCityData(this, data);
    }*/

    @Override
    public void createNew(ResultAbs toAdd, RetrieveData data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
