/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DTO;

/**
 *
 * @author Andrew Wright
 */
public class CoordinateDto 
{
    private Integer id;
    private Double lon;
    private Double lat;

    public CoordinateDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
    
    

    @Override
    public String toString() {
        return "CoordinateDto{" + "id=" + id + ", longitude=" + lon + ", latitude=" + lat + '}';
    }
    
    
    
}
