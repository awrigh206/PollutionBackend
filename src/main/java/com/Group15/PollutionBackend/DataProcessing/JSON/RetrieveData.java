/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.JSON;


import com.Group15.PollutionBackend.DataProcessing.JSON.Results.ResultAbs;
import com.Group15.PollutionBackend.Model.RealTime.Element;
import com.Group15.PollutionBackend.Model.RealTime.ParsedData;
import com.Group15.PollutionBackend.Model.RealTime.RealTimeData;
import com.Group15.PollutionBackend.StartupRunner;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andrew Wright
 */
@Component
public class RetrieveData 
{
    private ObjectMapper mapper;
    private Integer limit;
    private final Log log = LogFactory.getLog(RetrieveData.class);

    @PostConstruct
    public void init()
    {
        mapper = new ObjectMapper();
    }
    public RetrieveData(Integer limit) 
    {
        mapper = new ObjectMapper();
        this.limit = limit;
    }
    
    /**
     * Method which returns the MetaData of the request as a Java Object
     * @param baseUrl
     * @param resultType
     * @return 
     */
    public MetaData getMeta(String baseUrl,Class resultType) 
    {
        URL url;
        try
        {
            url = new URL(baseUrl+"?limit=1");
        }
        catch(Exception e)
        {
            log.info(e.getMessage());
            return null;
        }
        
        return sendRequest(url, resultType).getMeta();
    }
    
    public ResultAbs processPageSingle(String baseUrl,int page, Class resultType, String countryCode) throws Exception
    {
        URL url;
        if(countryCode!=null)
        {
            url= new URL(baseUrl+"?limit="+limit+"&page="+page +"&country="+countryCode);
        }
        else
        {
            url = new URL(baseUrl+"?limit="+limit+"&page="+page);
        }
        return sendRequest(url,resultType);
    }
    
    public RealTimeData processRealTime(URL url) 
    {
        try
        {
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();


            if(responseCode!=200)
            {
                //responce code 200 means everything has gone well, if it is not this then something must have gone wrong 
                throw new RuntimeException ("HTTPResponseCode: " + responseCode);
            }

            else
            {
                InputStream in = new BufferedInputStream(conn.getInputStream());
                String json = IOUtils.toString(in, "UTF-8");
                in.close();
                conn.disconnect();

                return new RealTimeData(json);
            }
        }
        
        catch(Exception e)
        {
            log.info(e.getMessage());
            return null;
        }
    }
    
    public ParsedData parseRealTime(URL url)
    {
        ParsedData data = new ParsedData();
        String json = processRealTime(url).getJson();
        ArrayList<String> fieldNames = new ArrayList<>();
        
        try
        {
            JsonNode rootNode = mapper.readTree(json);
            JsonNode dataNode = rootNode.path("data");
            JsonNode aqiNode = dataNode.path("aqi");
            data.setAqi(aqiNode.asInt());
            JsonNode dominentpolNode = dataNode.path("dominentpol");
            data.setDominentpol(dominentpolNode.asText());
            
            JsonNode iaqiNode = dataNode.path("iaqi");
            Iterator<JsonNode> elements = iaqiNode.elements();
            
            /*
            while(elements.hasNext())
            {
                Element element = new Element();
                JsonNode titleNode = elements.next();
                
                //element.setNameOfElement(titleNode.fieldNames().next());
                String fieldName = titleNode.fieldNames().next();
                
                JsonNode valueNode = titleNode.path(fieldName);
                element.setValue(valueNode.asText());
                data.addElement(element);
            }*/
            
            Iterator names = iaqiNode.fieldNames();
            while (names.hasNext())
            {
                String key = (String)names.next();
                fieldNames.add(key);
            }
            
            for(String name : fieldNames)
            {
                Element element = new Element();
                JsonNode titleNode = iaqiNode.get(name);
                
                //element.setNameOfElement(titleNode.fieldNames().next());
                String fieldName = titleNode.fieldNames().next();
                element.setNameOfElement(name);
                JsonNode valueNode = titleNode.path(fieldName);
                element.setValue(valueNode.asText());
                data.addElement(element);
            }
            
            return data;
            
        }
        catch (Exception e)
        {
            log.info(e.getMessage());
            return null;
        }

    }
    
    private ResultAbs sendRequest(URL url, Class resultType)
    {
        try
        {
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();


            if(responseCode!=200)
            {
                //responce code 200 means everything has gone well, if it is not this then something must have gone wrong 
                throw new RuntimeException ("HTTPResponseCode: " + responseCode);
            }

            else
            {
                InputStream in = new BufferedInputStream(conn.getInputStream());
                String json = IOUtils.toString(in, "UTF-8");
                in.close();
                conn.disconnect();

                return (ResultAbs)(mapper.readValue(json, resultType)) ;
            }
        }
        
        catch(Exception e)
        {
            log.info(e.getMessage());
            return new ResultAbs();
        }
        
    }
    
    public int getTotalPages(String baseUrl, Class resultType)
    {
        MetaData meta = getMeta(baseUrl, resultType);
        return meta.getFound()/limit+1;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}
