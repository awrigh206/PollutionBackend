/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.JSON;

import com.Group15.PollutionBackend.DataProcessing.JSON.Results.ResultAbs;
import com.Group15.PollutionBackend.Service.IService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Andrew Wright
 */
public class DataThread implements Runnable
{
    private final int start;
    private final int end;
    
    private final RetrieveData data;
    private final IService service;
    private final String baseUrl;
    private final Class resultType;
    private final int numberOfCountries;
    private final Log log = LogFactory.getLog(DataThread.class);
    private String countryCode;
    

    //Using Iservice as interface facade
    //Faster to use direct implementation rather than casting, change this if the performance is needed
    public DataThread(int start, int end,int countriesPerThread, RetrieveData data, IService service, String baseUrl, Class resultType) {
        this.start = start;
        this.end = end;
        this.data = data;
        this.service = service;
        this.baseUrl = baseUrl;
        this.resultType = resultType;
        this.numberOfCountries = countriesPerThread;
    } 

    @Override
    public void run() 
    {
        data.setLimit(numberOfCountries);
        try
        {
            for(int i= start; i< end; i++)
            {
                ResultAbs result = (ResultAbs)data.processPageSingle(baseUrl,i,resultType,countryCode);
                addResult(result);
                System.gc();
            }
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void addResult(ResultAbs result)
    {
        if(result.hasMany())
            service.createNew(result,data);
        else 
            service.createNew(result);
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
}
