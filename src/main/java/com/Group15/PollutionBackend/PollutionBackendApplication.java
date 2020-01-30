package com.Group15.PollutionBackend;

import com.Group15.PollutionBackend.DataProcessing.RetrieveData;
import com.Group15.PollutionBackend.Model.AirQuality;
import com.Group15.PollutionBackend.Model.Coordinates;
import com.Group15.PollutionBackend.Service.CityService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PollutionBackendApplication implements CommandLineRunner
{
    @Autowired
    private CityService cityService;

    public static void main(String[] args) {
            SpringApplication.run(PollutionBackendApplication.class, args);
    }
        
    public void run(String... args) throws Exception 
    {
        ArrayList<AirQuality> quality = new ArrayList<>();
        Coordinates coords = new Coordinates(1,2.0,3.0);
        
        cityService.createCity("myCity", "GB", "GB", 100, quality, 1,2);
        cityService.createCity("glasgow", "GB", "GB", 65, quality, 1,3);
        cityService.createCity("edinburgh", "GB", "GB", 3, quality, 5,6);
        cityService.createCity("Aberdeen", "GB", "GB", 201, quality, 2,3);
        cityService.createCity("Dundee", "GB", "GB", 150, quality, 90,100);
        
        RetrieveData retData = new RetrieveData();
        retData.sendRequest("https://api.openaq.org/v1/latest?country=GB&limit=200");
        
        
    }

}
