/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Andrew Wright
 */
@Entity
public class City implements Serializable 
{
    @Id
    @GeneratedValue
    private Integer cityId;
    @SerializedName("city")
    private String name;
    private String country;
    private String location;
    private Double distance;
    @SerializedName("measurements")
    @ElementCollection
    @Embedded
    private List<AirQuality> airQuality;
    @Embedded
    private Coordinates coordinates;
    

    public City(String name, String country, String location, Double distance, List<AirQuality> airQuality, Coordinates coords) {
        this.name = name;
        this.country = country;
        this.location = location;
        this.distance = distance;
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

    public Double getDistance() {
        return distance;
    }

    public void setCount(Double distance) {
        this.distance = distance;
    }

    public List<AirQuality> getAirQuality() {
        return airQuality;
    }

    public void setAirQuality(List<AirQuality> airQuality) {
        this.airQuality = airQuality;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoords(Coordinates coords) {
        this.coordinates = coords;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    
    
    
    @Override
    public String toString() {
        return "City{" + "name=" + name + ", country=" + country + ", location=" + location + ", distance=" + distance + ", airQuality=" + airQuality + ", coordinates="+coordinates.toString()+ '}';
    }
    
    

    
}
