/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model;

/**
 *
 * @author Andrew Wright
 */
public class AirQuality 
{
    private String parameter;
    private Integer value;
    private String unit;
    private String dateTaken;

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

    public String getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    @Override
    public String toString() {
        return "AirQuality{" + "parameter=" + parameter + ", value=" + value + ", unit=" + unit + ", dateTaken=" + dateTaken + '}';
    }
    
    
    
    
}
