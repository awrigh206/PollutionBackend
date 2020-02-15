/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.JSON;

import com.Group15.PollutionBackend.Model.AirQuality;
import com.Group15.PollutionBackend.Model.AveragingPeriod;
import com.Group15.PollutionBackend.Model.City.City;
import com.Group15.PollutionBackend.Model.Coordinates;
import java.util.ArrayList;

/**
 *
 * @author Andrew Wright
 */
public class Location implements IRepo
{
    private String location;
    private String parameter;
    private String value;
    private String country;
    private String city;
    private Coordinates coordinates;
    private Date date;
    private String unit;

    protected Location()
    {
        
    }
    
    public City toNewCity()
    {
        AirQuality quality = getAirQuality();
        City toReturn = new City(city,country,location,0.0,new ArrayList<>(),coordinates);
        toReturn.addQuality(quality);
        return toReturn;
    }
    
    public AirQuality getAirQuality()
    {
        return new AirQuality(parameter,Double.parseDouble(value),date.getLocal(),unit,location,new AveragingPeriod(1.0,"Hours"));
    }
    
    public Date getDate() {
        return date;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
