/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Service;

import com.Group15.PollutionBackend.Model.Statistics;
import com.Group15.PollutionBackend.Repository.StatisticsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrew Wright
 */
@Service
public class StatisticsService 
{
    @Autowired
    private StatisticsRepository statsRepo;
    
    public void saveStat(Statistics toSave)
    {
        statsRepo.save(toSave);
    }
    
    public void deleteStat(Statistics toDelete)
    {
        statsRepo.delete(toDelete);
    }
    
    public List<Statistics> findCountry(String code)
    {
        return statsRepo.findAllByCountryCode(code);
    }
    
    public List<Statistics> getAll()
    {
        return statsRepo.findAll();
    }
    
    public void findCity(String countryCode, String cityName)
    {
        
    }
    
}
