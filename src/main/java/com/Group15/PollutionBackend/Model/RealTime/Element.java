/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model.RealTime;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javax.persistence.Embeddable;

/**
 *
 * @author Andrew Wright 
 */
@Embeddable
public class Element 
{
    private String nameOfElement;
    private Integer v;

    public Element() {
    }

    public Element(String nameOfElement, Integer value) {
        this.nameOfElement = nameOfElement;
        this.v = value;
    }

    public String getNameOfElement() {
        return nameOfElement;
    }

    public void setNameOfElement(String nameOfElement) {
        this.nameOfElement = nameOfElement;
    }

    public Integer getValue() {
        return v;
    }

    public void setValue(Integer value) {
        this.v = value;
    }
    
    public JsonNode toJson()
    {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.createObjectNode();
        JsonNode childNode = mapper.createObjectNode();
        ((ObjectNode) childNode).put("v", v);
        ((ObjectNode) rootNode).set(nameOfElement, childNode);
        
        return rootNode;
        
    }
    
    
}
