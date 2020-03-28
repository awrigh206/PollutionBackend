/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.Batch;

import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;
import com.Group15.PollutionBackend.Service.RealTimeService;
import java.net.URL;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andrew Wright
 */
public class RealTimeDataFetcherThread 
{
    private RealTimeService realTimeService;
    private final Log log = LogFactory.getLog(RealTimeDataFetcherThread.class);
    private final RetrieveData retData;

    public RealTimeDataFetcherThread(RealTimeService realTimeService, RetrieveData retData) {
        this.realTimeService = realTimeService;
        this.retData = retData;
    }
    
    private void store() 
    {
        int lat =0;
        int longitude =0;
        String token = "a3c205e5e20ddf248ae5a20e92b6a2b327132f95";
        try
        {
            String url = "https://api.waqi.info/feed/geo:"+lat+";"+longitude+"/?token="+token;
            realTimeService.save(retData.processRealTime(new URL(url)));
        }
        
        catch (Exception e)
        {
            log.info(e.getMessage());
        }
    }
    
    
    
}
