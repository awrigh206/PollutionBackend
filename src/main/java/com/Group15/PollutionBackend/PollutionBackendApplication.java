package com.Group15.PollutionBackend;

import com.Group15.PollutionBackend.DataProcessing.Batch.CalculationsHelper;
import com.Group15.PollutionBackend.DataProcessing.JSON.DataThread;
import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;
import com.Group15.PollutionBackend.Model.AirQuality;
import com.Group15.PollutionBackend.Model.City;
import com.Group15.PollutionBackend.Repository.CityRepository;
import com.Group15.PollutionBackend.Service.CityService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class PollutionBackendApplication implements CommandLineRunner
{
    private final Log log = LogFactory.getLog(PollutionBackendApplication.class);
    @Autowired
    private CityService cityService;
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
        
        log.info("Running Test:");
        averagingTest();
    }
    
    private void averagingTest()
    {
        System.out.println("it does a thing");
        List<City> glasgows = cityRepository.findAllByName("Aberdeen");
        
        for (City current: glasgows)
        {
            log.info(current.getName());
        }
        //City avg = CalculationsHelper.averageCitywide(cityRepository.findAllByName("Glasgow"));
        /*
        log.info("average name: " + avg.getName());
        log.info("Average Air quality metrics shown below ");
        for(AirQuality quality:avg.getAirQuality())
        {
            log.info(quality.getValueOf() + quality.getUnits());
        }
*/
    }


}
