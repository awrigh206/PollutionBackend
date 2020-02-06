/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.Batch;

import com.Group15.PollutionBackend.Model.AirQuality;
import com.Group15.PollutionBackend.Model.City;
import com.Group15.PollutionBackend.PollutionBackendApplication;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javassist.bytecode.Descriptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andrew Wright
 */
public class CalculationsHelper 
{
    private static final Log log = LogFactory.getLog(CalculationsHelper.class);
    
    /**
     * Calculates the average air qualities within a country, based off the values of the cities within that country
     * @param citiesInCountry - the cities within the country that you want to average
     * @param country - The country in which the cities are best
     * @return 
     */
    public City averageCountryWide(List<City> citiesInCountry, String country)
    {
        return averageCityWide(citiesInCountry, country);
    }
    /**
     * Calculates the averages of all the air qualities values within a city
     * @param citiesToAverage A list of all the cities which are the same city (more than one reading within a city is possible)
     * @param name
     * @return 
     */
    public static City averageCityWide(List<City> citiesToAverage, String name)
    {
        City averageCity = new City();
        averageCity.setName(name + " average");
        List<ArrayList<AirQuality>> listOfQualityLists = new ArrayList<ArrayList<AirQuality>>(); 
        ArrayList<AirQuality> pm25List = new ArrayList<>();
        ArrayList<AirQuality> pm10List = new ArrayList<>();
        ArrayList<AirQuality> o3List = new ArrayList<>();
        ArrayList<AirQuality> no2List = new ArrayList<>();
        
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
                        System.out.println("There's a parameter that doesn't get caught in the avergaing and it is: " +quality.getParameterUsed());
                        continue;
                }
                
            }
        }
        
        listOfQualityLists.add(pm25List);
        listOfQualityLists.add(pm10List);
        listOfQualityLists.add(o3List);
        listOfQualityLists.add(no2List);
        
        for(int i=0; i<listOfQualityLists.size();i++)
        {
            ArrayList<AirQuality> currentList = listOfQualityLists.get(i);
            
            AirQuality average = new AirQuality();
            average.setParameterUsed(getParam(currentList));
            average.setUnits(currentList.get(0).getUnits());
            average.setDateTaken(new Date().toString());
            average.setSourceName("Averaging of all available data");
            average.setValueOf(findAverage(currentList));
            averageCity.addQuality(average);
        }
        
        return averageCity;
    }
    
    private static String getParam(List<AirQuality> qualities)
    {
        for(AirQuality quality: qualities)
        {
            if(quality.getParameterUsed()!=(null) && !quality.getParameterUsed().equals("null"))
                return quality.getParameterUsed();
        }
        return "Could not find it";
    }
    /**
     * 
     * @param qualities the set of air qualities that you wish to average
     * @param param the parameter within the air qualities that you wish to average
     * @return 
     */
    private static double findAverage(List<AirQuality> qualities)
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
