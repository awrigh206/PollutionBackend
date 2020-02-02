package com.Group15.PollutionBackend;

import com.Group15.PollutionBackend.DataProcessing.DataThread;
import com.Group15.PollutionBackend.DataProcessing.Result;
import com.Group15.PollutionBackend.DataProcessing.RetrieveData;
import com.Group15.PollutionBackend.Model.AirQuality;
import com.Group15.PollutionBackend.Model.AveragingPeriod;
import com.Group15.PollutionBackend.Model.City;
import com.Group15.PollutionBackend.Model.Coordinates;
import com.Group15.PollutionBackend.Service.CityService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PollutionBackendApplication implements CommandLineRunner
{
    private final Log log = LogFactory.getLog(PollutionBackendApplication.class);
    @Autowired
    private CityService cityService;
    
   // @Autowired 
    //private CoordinateService coordService;

    public static void main(String[] args) {
            SpringApplication.run(PollutionBackendApplication.class, args);
    }
        
    @Override
    public void run(String... args) throws Exception 
    {

        long startTime = System.nanoTime();
        RetrieveData retData = new RetrieveData(1000);
        //List<Result> results = retData.sendRequest();
        getData(retData);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        log.info("That took about: " +duration);
        
        //for(Result result: results)
        //{
            
         //   for(City toAdd : result.getResults())
           // {
            //    cityService.createCity(toAdd);
           // }
        //}
        
        
        
    }
    
    private void getData(RetrieveData data)
    {
        int totalPages = data.getTotalPages();
        
        for(int i =0; i<totalPages;i++)
        {
            Thread t = new Thread(new DataThread(1,2,data,cityService), "data"+i);
            t.run();
        }
        
    }

}
