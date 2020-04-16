/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Configuration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andrew Wright
 */
@Component
public class TokenConfig 
{
    @Autowired
    private ResourceLoader resourceLoader;
    private String token;
    private final Log log = LogFactory.getLog(TokenConfig.class);
    
    @PostConstruct
    public void init()
    {
        Resource resource = resourceLoader.getResource("file:"+"token.txt");
        String result ="";
        try
        {
            InputStream input = resource.getInputStream();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            for (String line = null; (line = br.readLine()) != null;) 
            {
                result+=line;
            }
            token = result;

        }
        catch(Exception e)
        {
            log.info(e.getMessage());
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
}
