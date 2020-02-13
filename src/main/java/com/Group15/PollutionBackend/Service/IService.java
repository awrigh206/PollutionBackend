/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Service;

import com.Group15.PollutionBackend.DataProcessing.JSON.RepoObject;

/**
 *
 * @author Andrew Wright
 */
public interface IService 
{
    public RepoObject createNew(RepoObject toAdd);
    public long total();
}
