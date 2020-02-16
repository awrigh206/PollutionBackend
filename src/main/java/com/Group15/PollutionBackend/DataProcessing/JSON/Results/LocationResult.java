/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.JSON.Results;

import com.Group15.PollutionBackend.DataProcessing.JSON.Location;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/**
 *
 * @author Andrew Wright
 */
public class LocationResult extends ResultAbs
{

    protected LocationResult() 
    {
        super();
    }
    
    @JsonProperty("results")
    private ArrayList<Location> locations;

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "LocationResult{" + "locations=" + locations + '}';
    }
    
    
    
    
}
