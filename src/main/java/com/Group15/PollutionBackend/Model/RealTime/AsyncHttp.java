/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model.RealTime;

import com.Group15.PollutionBackend.DTO.CoordinateDto;
import com.Group15.PollutionBackend.DataProcessing.Batch.FetcherThread;
import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import java.util.concurrent.Callable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andrew Wright
 */
public class AsyncHttp implements Callable
{
    private final Log log = LogFactory.getLog(AsyncHttp.class);
    private final CoordinateDto coords;
    private final String token;
    private final RetrieveData retData;
    private final ObjectMapper mapper;
    
    private JsonNode finishedNode;

    public AsyncHttp(CoordinateDto coords, String token, RetrieveData retData, ObjectMapper mapper) {
        this.coords = coords;
        this.token = token;
        this.retData = retData;
        this.mapper = mapper;
    }

    public JsonNode getFinishedNode() {
        return finishedNode;
    }

    @Override
    public Object call() throws Exception 
    {
        try
        {
            String url = "https://api.waqi.info/feed/geo:"+coords.getLat()+";"+coords.getLon()+"/?token="+token;
            ParsedData newData = retData.parseRealTime(new URL(url));
            newData.setLon(coords.getLon());
            newData.setLat(coords.getLat());
            newData.setRequestId(coords.getCoordId());
            return newData;
            //return finishedNode = data.toJson(coords.getId(), mapper);
        }
        
        catch (Exception e)
        {
            log.info(e.getMessage());
            return null;
        }
    }
    
    
    
}
