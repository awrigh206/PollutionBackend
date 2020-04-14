/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model.RealTime;

import java.util.ArrayList;

/**
 *
 * @author Andrew Wright
 */
public class ParsedData 
{
    private ArrayList<Element> elements;
    private Integer aqi;
    private String dominentpol;

    public ParsedData() 
    {
        this.elements = new ArrayList<>();
    }
    
    public void addElement(Element toAdd)
    {
        elements.add(toAdd);
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public Integer getAqi() {
        return aqi;
    }

    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }

    public String getDominentpol() {
        return dominentpol;
    }

    public void setDominentpol(String dominentpol) {
        this.dominentpol = dominentpol;
    }

    @Override
    public String toString() {
        return "ParsedData{" + "elements=" + elements + ", aqi=" + aqi + ", dominentpol=" + dominentpol + '}';
    }
    
    
    
    
}
