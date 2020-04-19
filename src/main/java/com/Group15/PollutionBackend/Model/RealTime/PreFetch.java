/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model.RealTime;

import com.Group15.PollutionBackend.DTO.CoordinateDto;
import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 *
 * @author Andrew Wright
 */
public class PreFetch implements Callable
{
    private final ResourceLoader resourceLoader;
    private final ObjectMapper mapper;
    private final Log log = LogFactory.getLog(PreFetch.class);
    private ExecutorService exec;
    private final String token;
    private final RetrieveData retData;

    public PreFetch(ResourceLoader resourceLoader, String token, RetrieveData retData, ExecutorService exec) 
    {
        this.resourceLoader = resourceLoader;
        this.mapper = new ObjectMapper();
        this.token = token;
        this.retData = retData;
        this.exec = exec;
    }

    @Override
    public Object call() throws Exception 
    {
        List<Future<?>> futures = new ArrayList<Future<?>>();
        List<CoordinateDto> coords = getCoords();
        List<ParsedData> data = new ArrayList<>();
        for(CoordinateDto coord : coords)
        {
            Callable worker = new AsyncHttp(coord,token,retData);
            Future<?> f = exec.submit(worker);
            futures.add(f);
        }
        
        for(Future<?> future : futures)
        {
            while (!future.isDone())
            {
                Thread.sleep(10);
            }
            if(future.isDone())
            {
                ParsedData newItem = (ParsedData)future.get();
                data.add(newItem);
            }
        }
        return data;
    }
    
    private List<CoordinateDto> getCoords()
    {
        String json = getJsonFile();
        ArrayList<CoordinateDto> listOfCoordinatsFromFile = new ArrayList<>();
        try
        {
            JsonNode featuresNode = mapper.readTree(json).get("features");
            
            if(featuresNode.isArray())
            {
                for (JsonNode featureNode : featuresNode)
                {
                    JsonNode propertiesNode = featureNode.path("properties");
                    JsonNode idNode = propertiesNode.path("id");
                    JsonNode capitalNode = propertiesNode.path("capital");
                    JsonNode geoNode = capitalNode.path("geo");
                    
                    ArrayList<Double> coords = new ArrayList<>();
                    for(JsonNode coordNode : geoNode)
                    {
                        coords.add(coordNode.asDouble());
                    }
                    CoordinateDto coordinates = new CoordinateDto(coords.get(0), coords.get(1));
                    listOfCoordinatsFromFile.add(coordinates);
                }
            }
            return listOfCoordinatsFromFile;
            
        }
        catch (Exception e)
        {
            log.info(e.getMessage());
            return null;
        }
    }
    
    private String getJsonFile()
    {
        Resource resource = resourceLoader.getResource("classpath:"+"worldMapCountries.json");
        String result ="";
        try
        {
            InputStream input = resource.getInputStream();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            for (String line = null; (line = br.readLine()) != null;) 
            {
                result+=line;
            }


        }
        catch(Exception e)
        {
            log.info(e.getMessage());
        }
        
        
        return result;
    }
    
}
