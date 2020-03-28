/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.Batch;

import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;
import com.Group15.PollutionBackend.Model.RealTime.RealTimeData;
import com.Group15.PollutionBackend.Service.RealTimeService;
import java.net.URL;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andrew Wright
 */
public class RealTimeDataFetcherThread implements Runnable
{
    private final RealTimeService realTimeService;
    private final Log log = LogFactory.getLog(RealTimeDataFetcherThread.class);
    private final RetrieveData retData;

    public RealTimeDataFetcherThread(RealTimeService realTimeService, RetrieveData retData) {
        this.realTimeService = realTimeService;
        this.retData = retData;
    }
    
    public void findValues()
    {

        
        for(Double longitude = -180.0; longitude<180.0; longitude+=0.01)
        {
            for(Double latitude = -90.0; latitude<90.0; latitude+=0.01)
            {
                store(latitude, longitude);
            }
        }
    }
    
    private void store(Double lat, Double longitude) 
    {
        String token = "a3c205e5e20ddf248ae5a20e92b6a2b327132f95";
        try
        {
            String url = "https://api.waqi.info/feed/geo:"+lat+";"+longitude+"/?token="+token;
            RealTimeData data = retData.processRealTime(new URL(url));
            data.setLatitude(lat);
            data.setLongitude(longitude);
            realTimeService.deleteOne(data);
            realTimeService.save(data);
        }
        
        catch (Exception e)
        {
            log.info(e.getMessage());
        }
    }

    @Override
    public void run() 
    {
        realTimeService.clear();
        while (true)
        {
            try
            {
                findValues();
                Thread.sleep(3600000);
            }
            
            catch (Exception e)
            {
                log.info(e.getMessage());
            }
            
        }
        
    }
    
    
    
}
