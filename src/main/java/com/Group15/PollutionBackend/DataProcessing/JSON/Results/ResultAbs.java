/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.JSON.Results;

import com.Group15.PollutionBackend.DataProcessing.JSON.MetaData;
import com.Group15.PollutionBackend.Model.City;
import java.util.Set;

/**
 *
 * @author Andrew Wright
 */
public abstract class ResultAbs 
{
    private MetaData meta;

    protected ResultAbs() {
    }

    public MetaData getMeta() {
        return meta;
    }

    public void setMeta(MetaData meta) {
        this.meta = meta;
    }
    
    
}
