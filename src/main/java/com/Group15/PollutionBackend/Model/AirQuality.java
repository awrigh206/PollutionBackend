/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
/**
 *
 * @author Andrew Wright
 */
@Embeddable
public class AirQuality implements Serializable
{
    @SerializedName("parameter")
    private String parameterUsed;
    @SerializedName("value")
    private Integer valueOf;
    private String lastUpdated;
    @SerializedName("unit")
    private String units;
    private String sourceName;
    @Embedded
    private AveragingPeriod averagingPeriod;

    public AirQuality(String parameterUsed, Integer valueOf, String lastUpdated, String units, String sourceName, AveragingPeriod averagingPeriod) {
        this.parameterUsed = parameterUsed;
        this.valueOf = valueOf;
        this.lastUpdated = lastUpdated;
        this.units = units;
        this.sourceName = sourceName;
        this.averagingPeriod = averagingPeriod;
    }

    protected AirQuality() {
    }
    
    
    public String getParameterUsed() {
        return parameterUsed;
    }

    public void setParameterUsed(String parameterUsed) {
        this.parameterUsed = parameterUsed;
    }

    public Integer getValueOf() {
        return valueOf;
    }

    public void setValueOf(Integer valueOf) {
        this.valueOf = valueOf;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    

    public String getUnit() {
        return units;
    }

    public void setUnit(String unit) {
        this.units = unit;
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
        return "AirQuality{" + "parameter=" + parameterUsed + ", value=" + valueOf + ", lastUpdated=" + lastUpdated + ", unit=" + units + ", sourceName=" + sourceName + ", averagingPeriod=" + averagingPeriod + '}';
    }
    
}
