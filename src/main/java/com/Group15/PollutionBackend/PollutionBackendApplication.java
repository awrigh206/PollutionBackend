package com.Group15.PollutionBackend;


import com.Group15.PollutionBackend.Repository.CityRepository;
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
    private CityRepository cityRepository;
    

    public static void main(String[] args) {
            SpringApplication.run(PollutionBackendApplication.class, args);
    }
        
    @Override
    public void run(String... args) throws Exception 
    {

        //long startTime = System.nanoTime();
        //long endTime = System.nanoTime();
        //long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        //log.info("That took about: " +duration);
        
    }
    
 


}
