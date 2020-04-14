/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model.RealTime;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 *
 * @author Andrew Wright 
 */
public class Element 
{
    private String nameOfElement;
    private String v;

    public Element() {
    }

    public Element(String nameOfElement, String value) {
        this.nameOfElement = nameOfElement;
        this.v = value;
    }

    public String getNameOfElement() {
        return nameOfElement;
    }

    public void setNameOfElement(String nameOfElement) {
        this.nameOfElement = nameOfElement;
    }

    public String getValue() {
        return v;
    }

    public void setValue(String value) {
        this.v = value;
    }
    
    public String toJson()
    {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.createObjectNode();
        JsonNode childNode = mapper.createObjectNode();
        ((ObjectNode) childNode).put("v", v);
        ((ObjectNode) rootNode).set(nameOfElement, childNode);
        
        try
        {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
            return json;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
        
    }
    
    
}
