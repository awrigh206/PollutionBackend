/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.JSON;

import com.Group15.PollutionBackend.Model.City;
import java.util.Set;

/**
 *
 * @author Andrew Wright
 */
public class ResultAbs 
{
    private Set<City> results;

    public Set<City> getResults() {
        return results;
    }

    public void setResults(Set<City> results) {
        this.results = results;
    }
    
    
}
