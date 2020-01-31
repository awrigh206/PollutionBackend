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
public class Coordinates implements Serializable 
{
    @Id
    @GeneratedValue
    private Integer id;
    private Double latitude;
    private Double longitude;

    public Coordinates( Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    protected Coordinates()
    {
        
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setlongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Coordinates{" + "latitude=" + latitude + ", yCoord=" + longitude + '}';
    }


    
    
    
    
    
    
    
}
