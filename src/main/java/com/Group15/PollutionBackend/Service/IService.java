/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Service;

import com.Group15.PollutionBackend.DataProcessing.JSON.Results.ResultAbs;
import com.Group15.PollutionBackend.DataProcessing.JSON.IRepo;
import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;

/**
 *
 * @author Andrew Wright
 */
public interface IService 
{
    public IRepo createNew(IRepo toAdd);
    public void createNew(ResultAbs toAdd);
    public long total();
    public void createNew(ResultAbs toAdd, RetrieveData data);
}
