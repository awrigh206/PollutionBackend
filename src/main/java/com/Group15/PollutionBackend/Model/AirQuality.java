/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 *
 * @author Andrew Wright
 */
//@Embeddable
@Entity 
public class AirQuality implements Serializable
{
    @JsonProperty("parameter")
    private String parameterUsed;
    @JsonProperty("value")
    private Double paramValue;
    private String lastUpdated;
    @JsonProperty("unit")
    private String units;
    private String sourceName;
    @Embedded
    private AveragingPeriod averagingPeriod;
    @GeneratedValue
    @Id
    private Long qualityId;
    
    @ManyToOne
    private City city;

    public AirQuality(String parameterUsed, Double valueOf, String lastUpdated, String units, String sourceName, AveragingPeriod averagingPeriod) {
        this.parameterUsed = parameterUsed;
        this.paramValue = valueOf;
        this.lastUpdated = lastUpdated;
        this.units = units;
        this.sourceName = sourceName;
        this.averagingPeriod = averagingPeriod;
    }

    public AirQuality() {
    }
    
    /**
     * 
     * @return will return the parameter of the measurement (pm25,CO2, etc)
     */
    public String getParameterUsed() {
        return parameterUsed;
    }

    public void setParameterUsed(String parameterUsed) {
        this.parameterUsed = parameterUsed;
    }

    public Double getValueOf() {
        return paramValue;
    }

    public void setValueOf(Double valueOf) {
        this.paramValue = valueOf;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setDateTaken(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public AveragingPeriod getAveragingPeriod() {
        return averagingPeriod;
    }

    public void setAveragingPeriod(AveragingPeriod averagingPeriod) {
        this.averagingPeriod = averagingPeriod;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    
    @Override
    public String toString() {
        return "AirQuality{" + "parameter=" + parameterUsed + ", value=" + paramValue + ", lastUpdated=" + lastUpdated + ", unit=" + units + ", sourceName=" + sourceName + ", averagingPeriod=" + averagingPeriod + '}';
    }

    public Long getId() {
        return qualityId;
    }

    public void setId(Long id) {
        this.qualityId = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    
    
    
}
