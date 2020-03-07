/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Controller;

import java.io.File;
import java.io.InputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andrew Wright
 */
@RestController
@RequestMapping(path = "/map")
public class MapController 
{
    @GetMapping
    public File getCountryMap(@RequestParam(value ="countryCode")String code)
    {
        File mapData = new File("src/main/resources/geodata/countries/"+code+".json");
        return mapData;
    }
}
