/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Andrew Wright
 */
@Entity
public class Coordinates implements Serializable 
{
    @Id
    private Double xCoord;
    private Double yCoord;

    public Coordinates( Double xCoord, Double yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }
    
    protected Coordinates()
    {
        
    }

    public Double getxCoord() {
        return xCoord;
    }

    public void setxCoord(Double xCoord) {
        this.xCoord = xCoord;
    }

    public Double getyCoord() {
        return yCoord;
    }

    public void setyCoord(Double yCoord) {
        this.yCoord = yCoord;
    }

    @Override
    public String toString() {
        return "Coordinates{" + "xCoord=" + xCoord + ", yCoord=" + yCoord + '}';
    }


    
    
    
    
    
    
    
}
