/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model.RealTime;

import com.Group15.PollutionBackend.Model.Coordinates;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.concurrent.Callable;
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

    public PreFetch(ResourceLoader resourceLoader) 
    {
        this.resourceLoader = resourceLoader;
        this.mapper = new ObjectMapper();
    }

    @Override
    public Object call() throws Exception 
    {
        return getCoords();
    }
    
    private Coordinates getCoords()
    {
        String json = getJsonFile();
        try
        {
            JsonNode featureNode = mapper.readTree(json).get("features");
            log.info(json);
            log.info(featureNode.isArray());
            
            return null;
            
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
            e.printStackTrace();
        }
        
        
        return result;
    }
    
}
