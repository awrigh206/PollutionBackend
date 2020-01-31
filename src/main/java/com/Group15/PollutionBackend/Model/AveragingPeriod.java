/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Andrew Wright
 */
@Embeddable 
public class AveragingPeriod implements Serializable
{
    @SerializedName("value")
    private int valueOf;
    private String unit;

    protected AveragingPeriod() {
    }

    public AveragingPeriod(int valueOf, String unit) {
        this.valueOf = valueOf;
        this.unit = unit;
    }

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

    @Override
    public String toString() {
        return "AveragingPeriod{" + "value=" + valueOf + ", unit=" + unit + '}';
    }
}
