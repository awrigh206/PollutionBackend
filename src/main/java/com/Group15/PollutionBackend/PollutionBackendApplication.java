package com.Group15.PollutionBackend;

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
        ArrayList<AirQuality> quality = new ArrayList<>();
        AveragingPeriod period = new AveragingPeriod(1.0,"s");
        //quality.add(new AirQuality("Carbon", 200, "yesterday", "G", "me", period));
        //quality.add(new AirQuality("Not Carbon", 500, "tomorrow", "G", "me", period));
        //quality.add(new AirQuality("Really Not Carbon", 1000, "yesterday", "KG", "me", period));
        Coordinates coords = new Coordinates(2.0,3.0);
        //coordService.createCoord(2.0, 3.0);
        /*
        cityService.createCity("myCity", "GB", "GB", 100.0, quality, coords);
        cityService.createCity("glasgow", "GB", "GB", 65.0, quality, coords);
        cityService.createCity("edinburgh", "GB", "GB", 3.0, quality,coords);
        cityService.createCity("Aberdeen", "GB", "GB", 201.0, quality, coords);
        cityService.createCity("Dundee", "GB", "GB", 150.0, quality, coords);
        */
        RetrieveData retData = new RetrieveData(2000);
        List<Result> results = retData.sendRequest();
        //log.info(results);
        
        for(Result result: results)
        {
            
            for(City toAdd : result.getResults())
            {
                cityService.createCity(toAdd);
            }
        }
        
        
        
    }

}
