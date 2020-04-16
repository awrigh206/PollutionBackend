/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Service;

import com.Group15.PollutionBackend.DTO.CoordinateDto;
import com.Group15.PollutionBackend.Model.RealTime.ParsedData;
import com.Group15.PollutionBackend.Repository.ParsedDataRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrew Wright
 */
@Service
public class ParsedDataService 
{
    @Autowired 
    private ParsedDataRepository parsedRepo;
        
    public ParsedData find(CoordinateDto coords)
    {
        return parsedRepo.findByLatAndLon(coords.getLon(),coords.getLat());
    }
    
    public List<ParsedData> findAll()
    {
        return parsedRepo.findAll();
    }
}
