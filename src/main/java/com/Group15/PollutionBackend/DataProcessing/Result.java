/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing;

import com.Group15.PollutionBackend.Model.City;
import java.util.Set;


/**
 *
 * @author Andrew Wright
 */
public class Result 
{
    private MetaData meta;
    private Set<City> results;

    public Result(MetaData meta, Set<City> results) {
        this.meta = meta;
        this.results = results;
    }

    protected Result() 
    {
    }
    
    

    public MetaData getMeta() {
        return meta;
    }

    public void setMeta(MetaData meta) {
        this.meta = meta;
    }

    public Set<City> getResults() {
        return results;
    }

    public void setResults(Set<City> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Result{" + "meta=" + meta + ", results=" + results + '}';
    }
    
    
    
}
