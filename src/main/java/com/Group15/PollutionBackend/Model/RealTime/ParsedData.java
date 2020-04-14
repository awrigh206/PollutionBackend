/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model.RealTime;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
    
    public String toJson()
    {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.createObjectNode();
        ((ObjectNode) rootNode).put("aqi", aqi);
        ((ObjectNode) rootNode).put("dominentpol", dominentpol);
        
        
        JsonNode iaqiNode = mapper.createObjectNode();
        

        for (Element element : elements)
        {
            JsonNode childNode = mapper.createObjectNode();
            ((ObjectNode) childNode).put("v", element.getValue());
            ((ObjectNode) iaqiNode).set(element.getNameOfElement(), childNode);
        }
        ((ObjectNode) rootNode).set("iaqi", iaqiNode);
        
        try
        {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
    
    
    
    
}
