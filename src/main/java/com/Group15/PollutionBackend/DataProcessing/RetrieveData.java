/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing;

import com.Group15.PollutionBackend.Model.City;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Andrew Wright
 */
public class RetrieveData 
{
    public void sendRequest(String param)
    {
        try
        {
            URL url = new URL(param);
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
                
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                City city = gson.fromJson(json, City.class);
            }
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
