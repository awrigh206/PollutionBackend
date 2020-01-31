/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Andrew Wright
 */
@Embeddable 
public class AveragingPeriod implements Serializable
{
    @SerializedName("value")
    private Integer valueOf;
    private String unit;

    protected AveragingPeriod() {
    }

    public AveragingPeriod(int valueOf, String unit) {
        this.valueOf = valueOf;
        this.unit = unit;
    }

    public Integer getValue() {
        return valueOf;
    }

    public void setValue(Integer value) {
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
