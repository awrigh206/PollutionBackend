/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.Batch;

import com.Group15.PollutionBackend.Model.AirQuality;
import com.Group15.PollutionBackend.Model.City;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Andrew Wright
 */
public class Calculations 
{
    
    /**
     * Calculates the average air qualities within a country, based off the values of the cities within that country
     * @param citiesInCountry
     * @return 
     */
    public Set<City> averageCountryWid(Set<City> citiesInCountry)
    {
        return null;
    }
    /**
     * Calculates the averages of all the air qualities values within a city
     * @param citiesToAverage A list of all the cities which are the same city (more than one reading within a city is possible)
     * @return 
     */
    public City averageCitywide(List<City> citiesToAverage)
    {
        City averageCity = new City();
        List<List<AirQuality>> listOfQualityLists = new ArrayList<>(); 
        for (City city: citiesToAverage)
        {
            listOfQualityLists.add(city.getAirQuality());
        }
        
        for (List<AirQuality> qualityList:listOfQualityLists)
        {
            for(AirQuality quality: qualityList)
            {
                AirQuality averageQuality = new AirQuality();
                averageQuality.setDateTaken(new Date().toString());
                averageQuality.setSourceName("Averaging of all available data for the city");
                averageQuality.setUnits(quality.getUnits());
                averageQuality.setValueOf(findAverage(qualityList,quality.getParameterUsed()));
            }
            
        }
        return averageCity;
    }
    /**
     * 
     * @param qualities the set of air qualities that you wish to average
     * @param param the parameter within the air qualities that you wish to average
     * @return 
     */
    private double findAverage(List<AirQuality> qualities, String param)
    {
        long runningTotal =0;
        int numberOfThingsAdded=0;
        for(AirQuality quality: qualities )
        {
            if(quality.getParameterUsed().equalsIgnoreCase(param))
            {
                runningTotal += quality.getValueOf();
                numberOfThingsAdded++;
            }
            
        }
        return runningTotal/numberOfThingsAdded;
    }
}
