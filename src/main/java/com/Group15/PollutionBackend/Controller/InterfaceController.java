/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andrew Wright
 */
@RestController
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class InterfaceController 
{
    @Autowired
    private ResourceLoader resourceLoader;
    @GetMapping
    (path ="/")
    public String getIndex()
    {
        Resource resource = resourceLoader.getResource("classpath:"+"index.html");
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
