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
public class LocationDto 
{
    private Double latitude;
    private Double longitude;
    private Integer id;

    public LocationDto(Integer id, Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.id = id;
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

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    
    
}
