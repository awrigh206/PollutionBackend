/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andrew Wright
 */
@RestController
public class MapController 
{
    @GetMapping
    (path ="/countryMap")
    public String getCountryMap(@RequestParam(value ="countryCode")String code)
    {
        code = code.toUpperCase();
        return getFileData("src/main/resources//geodata/geodata/countries/"+code+".json");
    }
    
    @GetMapping
    (path ="/map")
    public String getCountryMap()
    {
        return getFileData("src/main/resources/geodata/geodata/worldMapCountries.json");
    }
    
    private String getFileData(String path)
    {
        String result ="";
        try
        {
            try (BufferedReader br = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) 
            {
                for (String line = null; (line = br.readLine()) != null;) 
                {
                    result+=line;
                }
            }
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;

    }
}
