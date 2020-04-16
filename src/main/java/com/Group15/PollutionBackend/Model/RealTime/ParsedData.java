/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model.RealTime;

import com.Group15.PollutionBackend.DTO.CoordinateDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Andrew Wright
 */
@Entity
public class ParsedData 
{
    @Id
    @GeneratedValue
    private Integer id;
    @ElementCollection
    private List<Element> elements;
    private Integer aqi;
    private String dominentpol;
    private Double lat;
    private Double lon;
    private Integer requestId;

    public ParsedData() 
    {
        this.elements = new ArrayList<>();
    }
    
    public void addElement(Element toAdd)
    {
        elements.add(toAdd);
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }
    
    

    @Override
    public String toString() {
        return "ParsedData{" + "elements=" + elements + ", aqi=" + aqi + ", dominentpol=" + dominentpol + '}';
    }

    
    public JsonNode toJson(Integer id, ObjectMapper mapper)
    {
        JsonNode rootNode = mapper.createObjectNode();
        ((ObjectNode) rootNode).put("id", id);
        ((ObjectNode) rootNode).put("aqi", aqi);
        ((ObjectNode) rootNode).put("dominent", dominentpol);
        
        
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
            return rootNode;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
    
    
    
    
}
