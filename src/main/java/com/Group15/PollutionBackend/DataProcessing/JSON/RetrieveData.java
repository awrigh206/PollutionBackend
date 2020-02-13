/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.JSON;


import com.Group15.PollutionBackend.Model.City;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Andrew Wright
 */
public class RetrieveData 
{
    private final ObjectMapper mapper;
    private final int limit;

    public RetrieveData(int limit) 
    {
        mapper = new ObjectMapper();
        this.limit = limit;
    }
    
    public ResultAbs processPageSingle(String baseUrl,int page, Class resultType) throws Exception
    {
        URL url = new URL(baseUrl+"?limit="+limit+"&page="+page);
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
            
            return (ResultAbs) mapper.readValue(json, resultType);
        }
    }
    
    public int getTotalPages(String baseUrl)
    {
        try
        {
            URL url = new URL(baseUrl+"?limit=1&page=1");
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
                LatestResult result = mapper.readValue(json, LatestResult.class);
                in.close();    
                
                return (int) result.getMeta().getFound()/limit+1;
                
            }
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

}
