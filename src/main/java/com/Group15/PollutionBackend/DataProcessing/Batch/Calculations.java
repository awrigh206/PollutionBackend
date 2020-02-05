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
        List<AirQuality> pm25List = new ArrayList<>();
        List<AirQuality> pm10List = new ArrayList<>();
        List<AirQuality> o3List = new ArrayList<>();
        List<AirQuality> no2List = new ArrayList<>();
        
        for (City city: citiesToAverage)
        {
            for(AirQuality quality : city.getAirQuality())
            {
                switch (quality.getParameterUsed())
                {
                    case "pm25":
                        pm25List.add(quality);
                        break;
                    case "pm10":
                        pm10List.add(quality);
                        break;
                    case "o3":
                        o3List.add(quality);
                        break;
                    case"no2":
                        no2List.add(quality);
                        break;
                    default:
                        System.out.println("Ther's a parameter that doesn't get caught in the avergaing and it is: " +quality.getParameterUsed());
                        continue;
                }
                
            }
        }
        
        listOfQualityLists.add(pm25List);
        listOfQualityLists.add(pm10List);
        listOfQualityLists.add(o3List);
        listOfQualityLists.add(no2List);
        
        AirQuality average = new AirQuality();
        for(List<AirQuality> qualities : listOfQualityLists)
        {

            average.setParameterUsed(qualities.get(0).getParameterUsed());
            average.setDateTaken(new Date().toString());
            average.setSourceName("Averaging of all available dat");
            average.setValueOf(findAverage(qualities));
            averageCity.addQuality(average);
        }
        
        return averageCity;
    }
    /**
     * 
     * @param qualities the set of air qualities that you wish to average
     * @param param the parameter within the air qualities that you wish to average
     * @return 
     */
    private double findAverage(List<AirQuality> qualities)
    {
        long runningTotal =0;
        int numberOfThingsAdded=0;
        for(AirQuality quality: qualities )
        {
                runningTotal += quality.getValueOf();
                numberOfThingsAdded++;
            
        }
        return runningTotal/numberOfThingsAdded;
    }
}
