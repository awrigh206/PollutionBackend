/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Andrew Wright
 */
public class MetaData 
{
    private String name;
    private String license;
    private String website;
    private String page;
    private Integer limit;
    private Integer found;

    public MetaData(String name, String license, String website, String page, Integer limit, Integer found) {
        this.name = name;
        this.license = license;
        this.website = website;
        this.page = page;
        this.limit = limit;
        this.found = found;
    }

    protected MetaData() {
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getFound() {
        return found;
    }

    public void setFound(Integer found) {
        this.found = found;
    }

    @Override
    public String toString() {
        return "MetaData{" + "name=" + name + ", license=" + license + ", website=" + website + ", page=" + page + ", limit=" + limit + ", found=" + found + '}';
    }
    
    
    
    
    
}
