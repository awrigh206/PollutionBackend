/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing;

import com.Group15.PollutionBackend.Model.City;
import com.Group15.PollutionBackend.Model.MetaData;
import java.util.List;


/**
 *
 * @author Andrew Wright
 */
public class Result 
{
    private MetaData meta;
    private List<City> results;

    public Result(MetaData meta, List<City> results) {
        this.meta = meta;
        this.results = results;
    }

    protected Result() {
    }

    public MetaData getMeta() {
        return meta;
    }

    public void setMeta(MetaData meta) {
        this.meta = meta;
    }

    public List<City> getResults() {
        return results;
    }

    public void setResults(List<City> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Result{" + "meta=" + meta + ", results=" + results + '}';
    }
    
    
    
}
