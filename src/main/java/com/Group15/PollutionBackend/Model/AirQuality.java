/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Andrew Wright
 */
@Entity
public class AirQuality implements Serializable
{
    @Id
    @GeneratedValue
    private Integer id;
    private String parameter;
    private Integer value;
    private String lastUpdated;
    private String unit;
    private String sourceName;
    @OneToOne
    @Cascade({CascadeType.ALL})
    private AveragingPeriod averagingPeriod;

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "AirQuality{" + "parameter=" + parameter + ", value=" + value + ", lastUpdated=" + lastUpdated + ", unit=" + unit + ", sourceName=" + sourceName + ", averagingPeriod=" + averagingPeriod + '}';
    }
    
}
