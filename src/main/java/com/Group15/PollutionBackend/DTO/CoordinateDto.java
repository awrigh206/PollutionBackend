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
    private Integer coordId;
    private Double lon;
    private Double lat;

    public CoordinateDto(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
    }
    
    

    public CoordinateDto() {
    }

    public Integer getCoordId() {
        return coordId;
    }

    public void setCoordId(Integer coordId) {
        this.coordId = coordId;
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
        return "CoordinateDto{" + "id=" + coordId + ", longitude=" + lon + ", latitude=" + lat + '}';
    }
    
    
    
}
