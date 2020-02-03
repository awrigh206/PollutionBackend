/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing;


import com.Group15.PollutionBackend.Model.City;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoniter.JsonIterator;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.IOUtils;
import java.util.Scanner;

/**
 *
 * @author Andrew Wright
 */
public class RetrieveData 
{
    public List<Result> results = new ArrayList<>();
    private final ObjectMapper mapper;
    private final int limit;

    public RetrieveData(int limit) 
    {
        mapper = new ObjectMapper();
        this.limit = limit;
    }

    
    public void processPage(int page) throws Exception
    {
        URL url = new URL("https://api.openaq.org/v1/latest?limit="+limit+"&page="+page);
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
            Result result = mapper.readValue(json, Result.class);
            in.close();
            results.add(result);
        }
        
        
    }
    
    public Result processPageSingle(int page) throws Exception
    {
        URL url = new URL("https://api.openaq.org/v1/latest?limit="+limit+"&page="+page);
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
            
            return mapper.readValue(json, Result.class);
            //Result result = new Result();
            //result.setResults(read(json));
            //result.setResults(JsonIterator.deserialize(json, City.class));
            
            //return JsonIterator.deserialize(json, Result.class);
        }
    }
    
    //TODO: This currently has no purpose as there are issues with the code but it may be useful for optimisation in the future
    private ArrayList<City> read(String json) throws Exception
    {
        JsonFactory jfactory = new JsonFactory();
        JsonParser jsonParser = jfactory.createParser(json);
        
        ArrayList<City> cities = new ArrayList<>();

        String name ="";
        String country="";
        
        City city = new City();
        city = JsonIterator.deserialize(json, City.class);
        
        //parsing the Json as shown below should be faster, but for some reason it gets stuck on the "Distance" field. Probably to do with that field having a double value
        /*
            for (String field = iter.readObject(); field != null; field = iter.readObject()) 
            {
                //System.out.println("This is a: " + field);
                switch (field) {
                    case "results":
                        while (iter.readArray()) 
                        {
                            for (String field2 = iter.readObject(); field2 != null; field2 = iter.readObject()) 
                            {
                                //System.out.println("This is a: " + field2);
                                switch (field2) {
                                    case "country":
                                        //System.out.println("Found a  country tag, which says: " + iter.readObject());
                                        // (iter.readArray()) 
                                        //{
                                            //iter.skip();
                                        //}
                                        break;
                                    default:
                                        iter.skip();
                                }
                            }
                        }
                        break;
                    default:
                        iter.skip();
                }
            }
        
        */
        
        


        jsonParser.close();
        return cities;
    }
    
    public int getTotalPages()
    {
        try
        {
            URL url = new URL("https://api.openaq.org/v1/latest?limit=1&page=1");
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
                Result result = mapper.readValue(json, Result.class);
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
