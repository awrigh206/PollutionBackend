/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model;
import com.Group15.PollutionBackend.DataProcessing.JSON.IRepo;
import com.Group15.PollutionBackend.DataProcessing.JSON.Location;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

/**
 *
 * @author Andrew Wright
 */
@Entity
@Transactional
public class Country implements Serializable, IRepo
{
    @GeneratedValue
    @Id
    private Long countryId;
    private Integer locations;
    @JsonProperty("cities")
    private Integer numberOfCities;
    private String name;
    @JsonProperty("code")
    private String countryCode;
    private Integer count;
    @OneToMany (fetch = FetchType.EAGER, cascade={CascadeType.ALL} , orphanRemoval = true)
    //@JoinColumn(name="id") 
    private List<City> citiesWithinCountry;
    private Integer pageReached =1;

    public Country() 
    {
        citiesWithinCountry = new ArrayList<>();
    }

    public Long getId() {
        return countryId;
    }

    public void setId(Long id) {
        this.countryId= id;
    }

    public Integer getLocations() {
        return locations;
    }

    public void setLocations(Integer locations) {
        this.locations = locations;
    }

    public Integer getNumberOfCities() {
        return numberOfCities;
    }

    public void setNumberOfCities(Integer numberOfCities) {
        this.numberOfCities = numberOfCities;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<City> getCitiesWithinCountry() {
        return citiesWithinCountry;
    }

    public void setCitiesWithinCountry(List<City> citiesWithinCountry) {
        this.citiesWithinCountry = citiesWithinCountry;
    }
    
    public void addCity(City toAdd)
    {
        this.citiesWithinCountry.add(toAdd);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.countryCode);
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
        final Country other = (Country) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.countryCode, other.countryCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Country{" + "id=" + countryId + ", locations=" + locations + ", numberOfCities=" + numberOfCities + ", name=" + name + ", countryCode=" + countryCode + ", count=" + count + ", citiesWithinCountry=" + citiesWithinCountry + '}';
    }
    
    public City findCity(Location location)
    {
        for(City current : citiesWithinCountry)
        {
            if(current.getName().equalsIgnoreCase(location.getCity()))
            {
                return current;
            }
        }
        return null;
    }

    public Integer getPageReached() {
        return pageReached;
    }

    public void setPageReached(Integer pageReached) {
        this.pageReached = pageReached;
    }
    
    
    
    
    
    
}
