/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andrew Wright
 */
@RestController
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class MapController 
{
    @Autowired
    ResourceLoader resourceLoader;
                
    @GetMapping
    (path ="/countryMap")
    public String getCountryMap(@RequestParam(value ="countryCode")String code)
    {
        code = code.toUpperCase();
        return getFileData(code+".json");
    }
    
    @GetMapping
    (path ="/map")
    public String getMap()
    {
        return getFileData(File.separator+"worldMapCountries.json");
    }
    
    private String getFileData(String path)
    {
        String result ="";
	
	Resource resource = resourceLoader.getResource("classpath:"+path);
        
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
