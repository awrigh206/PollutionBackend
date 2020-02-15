/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.JSON.Results;

import com.Group15.PollutionBackend.Model.Country;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/**
 *
 * @author Andrew Wright
 */
public class CountryResult extends ResultAbs
{
    @JsonProperty("results")
    private ArrayList<Country> countries;
    
    protected CountryResult()
    {
        
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "CountryResult{" + "countries=" + countries + '}';
    }
    
    
    
    
    
}
