/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.JSON.Results;

import com.Group15.PollutionBackend.DataProcessing.JSON.MetaData;

/**
 *
 * @author Andrew Wright
 */
public class ResultAbs 
{
    //Abstract but not really, more like a super class 
    protected MetaData meta;
    protected boolean hasMany;

    public ResultAbs() 
    {
        hasMany = false;
    }

    public MetaData getMeta() {
        return meta;
    }

    public void setMeta(MetaData meta) {
        this.meta = meta;
    }

    public boolean hasMany() {
        return hasMany;
    }

    public void setHasMany(boolean hasMany) {
        this.hasMany = hasMany;
    }
    
    
    
    
}
