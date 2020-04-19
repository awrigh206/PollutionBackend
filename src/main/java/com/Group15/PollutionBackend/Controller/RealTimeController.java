/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Controller;

import com.Group15.PollutionBackend.Configuration.TokenConfig;
import com.Group15.PollutionBackend.DTO.CoordinateDto;
import com.Group15.PollutionBackend.DTO.LocationDto;
import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;
import com.Group15.PollutionBackend.Model.RealTime.AsyncHttp;
import com.Group15.PollutionBackend.Model.RealTime.ParsedData;
import com.Group15.PollutionBackend.Model.RealTime.RealTimeData;
import com.Group15.PollutionBackend.Service.ParsedDataService;
import com.Group15.PollutionBackend.Service.RealTimeService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andrew Wright
 */
@RestController
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class RealTimeController 
{
    @Autowired 
    private RetrieveData retData;
    private final Log log = LogFactory.getLog(RealTimeController.class);
    @Autowired
    private RealTimeService realService;
    private String token;
    private ObjectMapper mapper = new ObjectMapper();
    private ExecutorService exec = Executors.newFixedThreadPool(200);
    @Autowired 
    private ParsedDataService parsedService;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired 
    private TokenConfig tokenConfig;
    
    @PostConstruct
    public void init()
    {
        this.token = tokenConfig.getToken();
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler (NoSuchElementException.class)
    public String return400(NoSuchElementException ex)
    {
        return ex.getMessage();
    }
    
    @PostMapping (path="/updateToken")
    public void updateToken(@RequestBody String token)
    {
        this.token = token;
    }
    
    @PostMapping (path ="/realTime")
    public Object getCountries(@RequestBody LocationDto locationDto)
    {   
        String response = "";
        response = getCached(locationDto);
        if( response.equals(""))
        {
            response = getNow(locationDto);
        }
        return response;
    }
    
    private String getNow(LocationDto location)
    {
        ArrayNode arrayNode = mapper.createArrayNode();;
        List<Future<?>> futures = new ArrayList<Future<?>>();
        List<CoordinateDto> coords = location.getCoords();
        
        try
        {
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
                    ParsedData item = (ParsedData)future.get();
                    parsedService.save(item);
                    arrayNode.add((JsonNode)item.toJson(item.getRequestId(), mapper));
                }
            }
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
        }
        catch (Exception e)
        {
            log.info(e.getMessage());
        }
        return null;

    }
    
    private String getCached(LocationDto locationDto)
    {
        try
        {
            ArrayNode arrayNode = mapper.createArrayNode();;
            List<ParsedData> data = new ArrayList<>();
            
            for(CoordinateDto coords : locationDto.getCoords())
            {
                ParsedData item = parsedService.find(coords);
                data.add(item);
            }
            for(ParsedData item : data)
            {
                arrayNode.add((JsonNode)item.toJson(item.getRequestId(), mapper));
            }
            

            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
        }

        catch (Exception e)
        {
            log.info(e.getMessage());
            return "";
        }
    }
    
    
    @GetMapping (path ="/stored")
    public List<ParsedData> getAllStored()
    {
        String token = "a3c205e5e20ddf248ae5a20e92b6a2b327132f95";
        try
        {
            return parsedService.findAll();
        }
        
        catch (Exception e)
        {
            log.info(e.getMessage());
        }
        
        return null;
    }
    
    
    @GetMapping (path ="/allRealTime")
    public List<RealTimeData> getAllTime()
    {
        String token = "a3c205e5e20ddf248ae5a20e92b6a2b327132f95";
        try
        {
            //String url = "https://api.waqi.info/feed/geo:"+latitude+";"+longitude+"/?token="+token;
            return realService.findAll();
        }
        
        catch (Exception e)
        {
            log.info(e.getMessage());
        }
        
        return null;
    }
}
