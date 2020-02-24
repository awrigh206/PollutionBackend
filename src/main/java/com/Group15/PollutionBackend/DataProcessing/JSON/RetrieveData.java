/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.JSON;


import com.Group15.PollutionBackend.DataProcessing.JSON.Results.ResultAbs;
import com.Group15.PollutionBackend.StartupRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andrew Wright
 */
public class RetrieveData 
{
    private final ObjectMapper mapper;
    private int limit;
    private final Log log = LogFactory.getLog(StartupRunner.class);

    public RetrieveData(int limit) 
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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
    
    

}
