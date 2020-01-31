/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package com.Group15.PollutionBackend.Service;

import com.Group15.PollutionBackend.Model.Coordinates;
import com.Group15.PollutionBackend.Repository.CityRepository;
import com.Group15.PollutionBackend.Repository.CoordinatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrew Wright
 
@Service
public class CoordinateService 
{
    private CoordinatesRepository coordRepo;
    
    @Autowired
    public CoordinateService (CoordinatesRepository coordRepo) 
    {
        this.coordRepo = coordRepo;
    }
    
    public Coordinates createCoord(Double x, Double y)
    {
        Coordinates coords = null;
        //Coordinates coords = coordRepo.findById(Double(x+y)).get();
        //TourPackage tourPackage = tourPackageOpt.get();
        if(coords == null)
        {
            return coordRepo.save(new Coordinates(x,y));
        }
        else
        {
            return null;
        }
        
    }
            
    public long total()
    {
        return coordRepo.count();
    }
}
*/