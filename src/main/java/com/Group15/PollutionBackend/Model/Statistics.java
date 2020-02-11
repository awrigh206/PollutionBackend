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

/**
 *
 * @author Andrew Wright
 */
@Entity
public class Statistics implements Serializable 
{
    @Id
    @GeneratedValue
    private Integer id;
    private Double mean;
    private Double standardDeviation;
    private Double min;
    private Double max;
    private Double variance;
    private Double geometricMean;
    private Double Kurtoise;
    private Double trend;
    private long nTerms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getnTerms() {
        return nTerms;
    }

    public void setnTerms(long nTerms) {
        this.nTerms = nTerms;
    }

    public Double getTrend() {
        return trend;
    }

    public void setTrend(Double trend) {
        this.trend = trend;
    }

    public Double getGeometricMean() {
        return geometricMean;
    }

    public void setGeometricMean(Double geometricMean) {
        this.geometricMean = geometricMean;
    }

    public Double getKurtoise() {
        return Kurtoise;
    }

    public void setKurtoise(Double Kurtoise) {
        this.Kurtoise = Kurtoise;
    }

    public Double getMean() {
        return mean;
    }

    public void setMean(Double mean) {
        this.mean = mean;
    }

    public Double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(Double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getVariance() {
        return variance;
    }

    public void setVariance(Double variance) {
        this.variance = variance;
    }

    @Override
    public String toString() {
        return "Statistics{" + "id=" + id + ", mean=" + mean + ", standardDeviation=" + standardDeviation + ", min=" + min + ", max=" + max + ", variance=" + variance + '}';
    }
    
    
    
    
}
