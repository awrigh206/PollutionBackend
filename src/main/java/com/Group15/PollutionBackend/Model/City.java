/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Andrew Wright
 */
@Entity
public class City implements Serializable 
{
    @Id
    private String name;
    private String country;
    private String location;
    private Integer count;
    private ArrayList<AirQuality> airQuality;
    
    private Integer xCoord;
    private Integer yCoord;

    public City(String name, String country, String location, Integer count, ArrayList<AirQuality> airQuality, Integer x, Integer y) {
        this.name = name;
        this.country = country;
        this.location = location;
        this.count = count;
        this.airQuality = airQuality;
        this.xCoord = x;
        this.yCoord = y;
    }
    
    protected City()
    {
        
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ArrayList<AirQuality> getAirQuality() {
        return airQuality;
    }

    public void setAirQuality(ArrayList<AirQuality> airQuality) {
        this.airQuality = airQuality;
    }

    public Integer getxCoord() {
        return xCoord;
    }

    public void setxCoord(Integer xCoord) {
        this.xCoord = xCoord;
    }

    public Integer getyCoord() {
        return yCoord;
    }

    public void setyCoord(Integer yCoord) {
        this.yCoord = yCoord;
    }

    @Override
    public String toString() {
        return "City{" + "name=" + name + ", country=" + country + ", location=" + location + ", count=" + count + ", airQuality=" + airQuality + ", xCoord=" + xCoord + ", yCoord=" + yCoord + '}';
    }
    
    

    
}
