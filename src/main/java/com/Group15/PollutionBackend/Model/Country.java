/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model;
import com.Group15.PollutionBackend.DataProcessing.JSON.IRepo;
import com.Group15.PollutionBackend.DataProcessing.JSON.Location;
import com.Group15.PollutionBackend.DataProcessing.JSON.Results.LocationResult;
import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Andrew Wright
 */
@Entity
public class Country implements Serializable, IRepo
{
    //@GeneratedValue
    //@Id
    //private Integer id;
    private Integer locations;
    @JsonProperty("cities")
    private Integer numberOfCities;
    private String name;
    @JsonProperty("code")
    @Id
    private String countryCode;
    private Integer count;
    @OneToMany (cascade={CascadeType.PERSIST, CascadeType.REMOVE} , orphanRemoval = true)
    @JoinColumn(name="id") 
    private List<City> citiesWithinCountry;

    public Country() 
    {
        citiesWithinCountry = new ArrayList<>();
    }
    
    public void fillInCityData( RetrieveData data)
    {
        //reset these defaults when you want to release the system. Set smaller values to decrease testing time
        //data.setLimit(1000);
        //int pageLimit = 2;
        data.setLimit(10000);
        int pageLimit = 3;
        //RetrieveData data = new RetrieveData(1200);
        String url = "https://api.openaq.org/v1/measurements";
        int totalPages = data.getTotalPages(url,LocationResult.class);
        
        try
        {
            
            for(int i =1; i<totalPages+1;i++)
            {
                if(i>pageLimit)
                    break;
                LocationResult result = (LocationResult)data.processPageSingle(url, i, LocationResult.class, countryCode);
                for(Location current : result.getLocations())
                {
                    City relevantCity = findCity (current);
                    if(relevantCity != null)
                        relevantCity.addQuality(current.getAirQuality());
                    else
                        citiesWithinCountry.add(current.toNewCity());
                }
            }
            
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /*
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }*/

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
        return "Country{" + "id=" + id + ", locations=" + locations + ", numberOfCities=" + numberOfCities + ", name=" + name + ", countryCode=" + countryCode + ", count=" + count + ", citiesWithinCountry=" + citiesWithinCountry + '}';
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
    
    
    
    
    
}
