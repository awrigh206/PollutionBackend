/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model.RealTime;

import java.util.List;

/**
 *
 * @author Andrew Wright
 */
public class RealTimeData 
{
    private List<Data> iaqi;
    
    public void addToList(Data toAdd)
    {
        iaqi.add(toAdd);
    }

    public List<Data> getIaqi() {
        return iaqi;
    }
}
