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
public class LatestResult extends ResultAbs
{
    private MetaData meta;

    public LatestResult(MetaData meta, Set<City> results) {
        this.meta = meta;
    }

    protected LatestResult() 
    {
    }
    
    

    public MetaData getMeta() {
        return meta;
    }

    public void setMeta(MetaData meta) {
        this.meta = meta;
    }

    @Override
    public String toString() {
        return "Result{" + "meta=" + meta + '}';
    }
    
    
    
}
