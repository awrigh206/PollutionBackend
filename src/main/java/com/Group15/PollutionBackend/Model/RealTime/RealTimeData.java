/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model.RealTime;

import java.util.Objects;


/**
 *
 * @author Andrew Wright
 */
public class RealTimeData 
{
    private String json;
    private String countryCode;

    public RealTimeData(String json, String countryCode) {
        this.json = json;
        this.countryCode = countryCode;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.json);
        hash = 47 * hash + Objects.hashCode(this.countryCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RealTimeData other = (RealTimeData) obj;
        if (!Objects.equals(this.json, other.json)) {
            return false;
        }
        if (!Objects.equals(this.countryCode, other.countryCode)) {
            return false;
        }
        return true;
    }
    
    
}
