/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Service;

import com.Group15.PollutionBackend.Model.RealTime.RealTimeData;
import com.Group15.PollutionBackend.Repository.RealTimeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrew Wright
 */
@Service
public class RealTimeService 
{
    @Autowired
    private RealTimeRepository realTimeRepo;

    public RealTimeData save(RealTimeData data)
    {
        return realTimeRepo.save(data);
    }
    
    public void clear()
    {
        realTimeRepo.deleteAll();
    }
    
    public void deleteOne(RealTimeData data)
    {
        realTimeRepo.delete(data);
    }
    
    public RealTimeData findByCoords(Double latitude, Double longitude)
    {
        return realTimeRepo.findByLatitudeAndLongitude(latitude, longitude);
    }
    
    public List<RealTimeData> findAll()
    {
        return realTimeRepo.findAll();
    }
    
}
