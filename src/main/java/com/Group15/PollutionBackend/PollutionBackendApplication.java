package com.Group15.PollutionBackend;

import com.Group15.PollutionBackend.DataProcessing.JSON.DataThread;
import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;
import com.Group15.PollutionBackend.Service.CityService;
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

    public static void main(String[] args) {
            SpringApplication.run(PollutionBackendApplication.class, args);
    }
        
    @Override
    public void run(String... args) throws Exception 
    {

        long startTime = System.nanoTime();
        RetrieveData retData = new RetrieveData(1200);
        getData(retData);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        log.info("That took about: " +duration);
    }
    
    private void getData(RetrieveData data)
    {
        int totalPages = data.getTotalPages();
        
        for(int i =0; i<totalPages+1;i++)
        {
            Thread t = new Thread(new DataThread(i,i+1,data,cityService), "data"+i);
            t.start();
        }
        
    }

}
