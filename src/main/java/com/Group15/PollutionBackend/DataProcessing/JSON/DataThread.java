/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.JSON;

import com.Group15.PollutionBackend.Service.CityService;
import com.Group15.PollutionBackend.Service.IService;

/**
 *
 * @author Andrew Wright
 */
public class DataThread implements Runnable
{
    private final int start;
    private final int end;
    
    private final RetrieveData data;
    private final IService service;

    //Using Iservice as interface facade
    //Faster to use direct implementation rather than casting, change this if the performance is needed
    public DataThread(int start, int end, RetrieveData data, IService service) {
        this.start = start;
        this.end = end;
        this.data = data;
        this.service = service;
    }

    @Override
    public void run() 
    {
        try
        {
            for(int i= start; i< end; i++)
            {
                Result result = data.processPageSingle("https://api.openaq.org/v1/latest",i);
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
        result.getResults().stream().filter((toAdd) -> (toAdd != null)).forEachOrdered((toAdd) -> {
            service.createNew(toAdd);
        });
    }
    
}
