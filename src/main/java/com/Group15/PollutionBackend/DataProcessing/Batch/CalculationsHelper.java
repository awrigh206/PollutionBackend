/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.Batch;

import com.Group15.PollutionBackend.Model.AirQuality;
import com.Group15.PollutionBackend.Model.City;
import com.Group15.PollutionBackend.Model.Country;
import com.Group15.PollutionBackend.Model.Statistics;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author Andrew Wright
 */
public class CalculationsHelper 
{
    private static final Log log = LogFactory.getLog(CalculationsHelper.class);
    
    
    public static List<Statistics> stats(Country countryForStats)
    {
        return stats(countryForStats.getCitiesWithinCountry());
    }
    
    
    /**
     * Use the apache commons maths package to calculate relevant statistics for the data and return those stats in a list
     * @param cityForStats
     * @return 
     */
    public static List<Statistics> stats(List<City> cityForStats)
    {
        
        List<ArrayList<AirQuality>> listOfQualityLists = splitIntoTypesOfPollution(cityForStats);
        List<Statistics> calculatedStats = new ArrayList<>();
        
        for (List<AirQuality> qualityList : listOfQualityLists)
        {
            List<Double> rawData = getRawData(qualityList);
            DescriptiveStatistics stats = addToStats(rawData);
            Statistics statsModelObject = new Statistics();
            
            try
            {
                statsModelObject.setPollutionType(qualityList.get(0).getParameterUsed());
            }
            
            catch (Exception e)
            {
                statsModelObject.setPollutionType(null);
            }
            

            statsModelObject.setMax(stats.getMax());
            statsModelObject.setMin(stats.getMin());
            statsModelObject.setStandardDeviation(stats.getStandardDeviation());
            statsModelObject.setVariance(stats.getVariance());
            statsModelObject.setMean(stats.getMean());
            statsModelObject.setGeometricMean(stats.getGeometricMean());
            statsModelObject.setKurtoise(stats.getKurtosis());
            statsModelObject.setnTerms(stats.getN());
            statsModelObject.setTrend(calculateTrend(statsModelObject));
            calculatedStats.add(statsModelObject);
        }
        return calculatedStats;
        
    }
    
    private static Double calculateTrend(Statistics statsModelObject)
    {
        double x1 =0;
        double x2 =1;
        double y1 = statsModelObject.getMin();
        double y2 = statsModelObject.getMax();
        
        Double gradient = (y2-y1)/(x2-x1);
        return gradient;
    }
    
    private static DescriptiveStatistics addToStats(List<Double> doublesToAdd)
    {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (Double toAdd: doublesToAdd)
        {
            stats.addValue(toAdd);
        }
        return stats;
    }
    
    private static List<Double> getRawData(List<AirQuality> qualityList)
    {
        List<Double> doublesData = new ArrayList<>();
        for (AirQuality quality: qualityList)
        {
            doublesData.add(quality.getValueOf());
        }
        return doublesData;
    }
    
    private static List<ArrayList<AirQuality>> splitIntoTypesOfPollution(List<City> cities)
    {
        List<ArrayList<AirQuality>> listOfQualityLists = new ArrayList<ArrayList<AirQuality>>(); 
        ArrayList<AirQuality> pm25List = new ArrayList<>();
        ArrayList<AirQuality> pm10List = new ArrayList<>();
        ArrayList<AirQuality> o3List = new ArrayList<>();
        ArrayList<AirQuality> no2List = new ArrayList<>();
        ArrayList<AirQuality> so2List = new ArrayList<>();
        ArrayList<AirQuality> coList = new ArrayList<>();
        ArrayList<AirQuality> bcList = new ArrayList<>();
        
        for (City city: cities)
        {
            for(AirQuality quality : city.getAirQuality())
            {
                switch (quality.getParameterUsed().toLowerCase())
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
                    case "bc":
                        bcList.add(quality);
                        break;
                    case"no2":
                        no2List.add(quality);
                        break;
                    case"so2":
                        so2List.add(quality);
                        break;
                    case"co":
                        coList.add(quality);
                        break;
                    default:
                        System.out.println("There's a parameter that doesn't get caught in the avergaing and it is: " +quality.getParameterUsed());
                        continue;
                }
                
            }
        }
        listOfQualityLists = addIfHasValues(pm25List,listOfQualityLists);
        listOfQualityLists = addIfHasValues(pm10List,listOfQualityLists);
        listOfQualityLists = addIfHasValues(o3List,listOfQualityLists);
        listOfQualityLists = addIfHasValues(no2List,listOfQualityLists);
        listOfQualityLists = addIfHasValues(so2List,listOfQualityLists);
        listOfQualityLists = addIfHasValues(coList,listOfQualityLists);
        listOfQualityLists = addIfHasValues(bcList,listOfQualityLists);
        
        return listOfQualityLists;
    }
    
    private static List<ArrayList<AirQuality>> addIfHasValues(ArrayList<AirQuality> qualityList,List<ArrayList<AirQuality>> listOfQualityLists )
    {
        if(!qualityList.isEmpty())
            listOfQualityLists.add(qualityList);
        return listOfQualityLists;
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

}
