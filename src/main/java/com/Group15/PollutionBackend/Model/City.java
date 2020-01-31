/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
    @SerializedName("measurements")
    private ArrayList<AirQuality> airQuality;
    @OneToOne
    private Coordinates coordinates;
    

    public City(String name, String country, String location, Integer count, ArrayList<AirQuality> airQuality, Coordinates coords) {
        this.name = name;
        this.country = country;
        this.location = location;
        this.count = count;
        this.airQuality = airQuality;
        this.coordinates = coords;
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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoords(Coordinates coords) {
        this.coordinates = coords;
    }
    @Override
    public String toString() {
        return "City{" + "name=" + name + ", country=" + country + ", location=" + location + ", count=" + count + ", airQuality=" + airQuality + ", coordinates="+coordinates.toString()+ '}';
    }
    
    

    
}
