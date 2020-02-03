/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing;

import com.Group15.PollutionBackend.Model.City;
import com.Group15.PollutionBackend.Service.CityService;

/**
 *
 * @author Andrew Wright
 */
public class DataThread implements Runnable
{
    private final int start;
    private final int end;
    
    private RetrieveData data;
    private CityService cityService;

    public DataThread(int start, int end, RetrieveData data, CityService cityService) {
        this.start = start;
        this.end = end;
        this.data = data;
        this.cityService = cityService;
    }

    @Override
    public void run() 
    {
        try
        {
            for(int i= start; i< end; i++)
            {
                Result result = data.processPageSingle(i);
                addCities(result);
            }
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }
    
    private void addCities(Result result)
    {
        for(City toAdd : result.getResults())
        {
            if(toAdd != null)
                cityService.createCity(toAdd);
        }
    }
    
}
