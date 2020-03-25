/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.JSON;

import com.Group15.PollutionBackend.DataProcessing.Batch.FetcherThread;
import com.Group15.PollutionBackend.DataProcessing.Batch.Range;
import com.Group15.PollutionBackend.DataProcessing.JSON.Results.ResultAbs;
import com.Group15.PollutionBackend.Service.CountryService;
import com.Group15.PollutionBackend.Service.IService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

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
    private final TaskExecutor taskExecutor;
    private String countryCode;
    

    //Using Iservice as interface facade
    //Faster to use direct implementation rather than casting, change this if the performance is needed
    public DataThread(int countriesPerThread, RetrieveData data, IService service, String baseUrl, Class resultType, TaskExecutor taskExec) {
        this.start=1;
        this.end=2;
        this.data = data;
        this.service = service;
        this.baseUrl = baseUrl;
        this.resultType = resultType;
        this.numberOfCountries = countriesPerThread;
        this.taskExecutor = taskExec;
    } 

    @Override
    public void run() 
    {
        log.info("data thread has begun");
        data.setLimit(numberOfCountries);
        try
        {
            for(int i= start; i< end; i++)
            {
                ResultAbs result = (ResultAbs)data.processPageSingle(baseUrl,i,resultType,countryCode);
                addResult(result);
                System.gc();
            }
            log.info("finished getting data");
            spawnBatchThreads();
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void spawnBatchThreads()
    {
        int numberOfThreads = 5;
        Range range = new Range(0,0);
        range = calculateRange(range);
        
        for (int count =0; count<numberOfThreads;count++)
        {
            taskExecutor.execute(new FetcherThread((CountryService)service,data,100,range));
            range = calculateRange(range);
        }
        
    }
    
    private Range calculateRange(Range lastRange)
    {
        int countriesPerThread = 20;
        Range newRange = new Range(lastRange.getEnd()+1,lastRange.getEnd()+countriesPerThread);
        return newRange;
        
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
