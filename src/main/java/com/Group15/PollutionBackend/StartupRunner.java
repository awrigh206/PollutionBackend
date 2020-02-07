/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend;

import com.Group15.PollutionBackend.DataProcessing.Batch.CalculationsHelper;
import com.Group15.PollutionBackend.DataProcessing.JSON.DataThread;
import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;
import com.Group15.PollutionBackend.Model.AirQuality;
import com.Group15.PollutionBackend.Model.City;
import com.Group15.PollutionBackend.PollutionBackendApplication;
import com.Group15.PollutionBackend.Repository.CityRepository;
import com.Group15.PollutionBackend.Service.CityService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andrew Wright
 */
@Component
public class StartupRunner implements ApplicationListener<ContextRefreshedEvent>
{
    private final Log log = LogFactory.getLog(StartupRunner.class);
    @Autowired
    private CityService cityService;
    @Autowired
    private CityRepository cityRepository;
    
    @PostConstruct
    public void init()
    {
        
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) 
    {
        log.info("Application intialised");
        log.info("Retrieving data");
        cityRepository.deleteAll();
        RetrieveData retData = new RetrieveData(1200);
        getData(retData);

    }
    
    private void getData(RetrieveData data)
    {
        int totalPages = data.getTotalPages();
        
        try
        {
            for(int i =0; i<totalPages+1;i++)
            {
                Thread t = new Thread(new DataThread(i,i+1,data,cityService), "data"+i);
                t.start();
            }
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }

    
}
