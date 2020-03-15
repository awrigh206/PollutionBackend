/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.Group15.PollutionBackend.DataProcessing.JSON.IRepo;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Andrew Wright
 */
@Entity 
public class City implements Serializable,IRepo
{
    @Id
    @GeneratedValue
    private Integer cityId;
    @JsonProperty("city")
    private String name;
    private String country;
    private String location;
    private Double distance;
    @JsonProperty("measurements")
    @ElementCollection
    @OneToMany (fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.REMOVE} , orphanRemoval = true)
    @JoinColumn(name="id") 
    private List<AirQuality> airQuality;
    @OneToOne (fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.REMOVE} , orphanRemoval = true)
    private Coordinates coordinates;
    

    public City(String name, String country, String location, Double distance, List<AirQuality> airQuality, Coordinates coords) {
        this.name = name;
        this.country = country;
        this.location = location;
        this.distance = distance;
        this.airQuality = airQuality;
        this.coordinates = coords;
    }
    
    public City()
    {
        this.airQuality = new ArrayList<>();
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

    public void setDistance(Double distance) {
        this.distance = distance;
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
    
    public void addQuality(AirQuality quality)
    {
        airQuality.add(quality);
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    
    @Override
    public String toString() {
        return "City{" + "name=" + name + ", country=" + country + ", location=" + location + ", distance=" + distance + ", airQuality=" + airQuality.toString() + ", coordinates="+coordinates.toString()+ '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.cityId);
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.country);
        hash = 67 * hash + Objects.hashCode(this.location);
        hash = 67 * hash + Objects.hashCode(this.distance);
        hash = 67 * hash + Objects.hashCode(this.airQuality);
        hash = 67 * hash + Objects.hashCode(this.coordinates);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final City other = (City) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.cityId, other.cityId)) {
            return false;
        }
        if (!Objects.equals(this.distance, other.distance)) {
            return false;
        }
        if (!Objects.equals(this.airQuality, other.airQuality)) {
            return false;
        }
        if (!Objects.equals(this.coordinates, other.coordinates)) {
            return false;
        }
        return true;
    }
    
    
    

    
}
