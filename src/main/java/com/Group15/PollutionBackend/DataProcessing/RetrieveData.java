/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Andrew Wright
 */
public class RetrieveData 
{
    public List<Result> results = new ArrayList<>();
    private final Gson gson;
    private final int limit;

    public RetrieveData(int limit) 
    {
        gson = new GsonBuilder().create();
        this.limit = limit;
    }
    
    
    public List<Result> sendRequest()
    {
        try
        {
            URL url = new URL("https://api.openaq.org/v1/latest?limit=1&page="+1);
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
                Scanner sc = new Scanner(url.openStream());
                String json = "";
                while(sc.hasNext())
                {
                    json+=sc.nextLine();
                }
                sc.close();
                Result result = gson.fromJson(json, Result.class);
                
                
                int totalPages = (int) result.getMeta().getFound()/limit+1;
                System.out.println("total pages: " + totalPages);
                
                for (int i=1; i< totalPages; i++)
                {
                    processPage(i);
                }
                
                return results;
                
            }
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
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
            Scanner sc = new Scanner(url.openStream());
            String json = "";
            while(sc.hasNext())
            {
                json+=sc.nextLine();
            }
            sc.close();
            
            Result result = gson.fromJson(json, Result.class);
            results.add(result);
            System.out.println("page: " + page);
        }
        
        
    }
    
}
