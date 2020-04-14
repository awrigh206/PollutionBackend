/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DTO;

import java.util.List;

/**
 *
 * @author Andrew Wright
 */
public class LocationDto 
{
    private List<CoordinateDto> coords;

    public List<CoordinateDto> getCoords() {
        return coords;
    }

    public void setCoords(List<CoordinateDto> coords) {
        this.coords = coords;
    }

    public LocationDto() {
    }

    @Override
    public String toString() {
        return "LocationDto{" + "coords=" + coords + '}';
    }

    
    
    
    
}
