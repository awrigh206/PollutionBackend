/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Andrew Wright
 */
@Entity
public class AveragingPeriod implements Serializable
{
    @Id
    @GeneratedValue
    private Integer id;
     @SerializedName("value")
    private int valueOf;
    private String unit;

    public int getValue() {
        return valueOf;
    }

    public void setValue(int value) {
        this.valueOf = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "AveragingPeriod{" + "value=" + valueOf + ", unit=" + unit + '}';
    }
}
