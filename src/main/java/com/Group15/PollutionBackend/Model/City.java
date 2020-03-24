/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.Group15.PollutionBackend.DataProcessing.JSON.IRepo;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Andrew Wright
 */
@Entity 
public class City implements Serializable,IRepo
{
    @GeneratedValue
    @JsonProperty("cityId")
    @Id
    private Long id;
    @JsonProperty("city")
    private String name;
    @JsonProperty("country")
    private String countryCode;
    private String location;
    private Double distance;
    @JsonProperty("measurements")
    @ElementCollection
    @OneToMany ( fetch = FetchType.EAGER,cascade={CascadeType.ALL} , orphanRemoval = true)
    //@LazyCollection(LazyCollectionOption.FALSE)
    //@JoinColumn(name="id") 
    private Set<AirQuality> airQuality;
    @OneToOne (fetch = FetchType.EAGER, cascade={CascadeType.ALL} , orphanRemoval = true)
    //@LazyCollection(LazyCollectionOption.FALSE)
    //@JoinColumn(name="id") 
    private Coordinates coordinates;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Country countryObject;
    

    public City(String name, String country, String location, Double distance, Set<AirQuality> airQuality, Coordinates coords, Country countryObject) {
        this.name = name;
        this.countryCode = country;
        this.location = location;
        this.distance = distance;
        this.airQuality = airQuality;
        this.coordinates = coords;
        this.countryObject = countryObject;
    }

   public City(String name, String country, String location, Double distance, Set<AirQuality> airQuality, Coordinates coords) {
        this.name = name;
        this.countryCode = country;
        this.location = location;
        this.distance = distance;
        this.airQuality = airQuality;
        this.coordinates = coords;
    }
    
    
    
    public City()
    {
        this.airQuality = new HashSet<>();
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

    public Set<AirQuality> getAirQuality() {
        return airQuality;
    }

    public void setAirQuality(Set<AirQuality> airQuality) {
        this.airQuality = airQuality;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoords(Coordinates coords) {
        this.coordinates = coords;
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
        return "City{" + "name=" + name + ", country=" + countryCode + ", location=" + location + ", distance=" + distance + ", airQuality=" + airQuality.toString() + ", coordinates="+coordinates.toString()+ '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.countryCode);
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
        if (!Objects.equals(this.countryCode, other.countryCode)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country getCountryObject() {
        return countryObject;
    }

    public void setCountryObject(Country countryObject) {
        this.countryObject = countryObject;
    }

    
    

    
}
