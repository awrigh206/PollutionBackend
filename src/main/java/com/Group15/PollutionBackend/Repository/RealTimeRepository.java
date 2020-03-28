/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Repository;

import com.Group15.PollutionBackend.Model.RealTime.RealTimeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andrew Wright
 */
@Repository
public interface RealTimeRepository extends JpaRepository<RealTimeData,Integer> 
{
    public void deleteAllByJson(String json);
    public RealTimeData findByLatitudeAndLongitude(Double latitude, Double longitude);
}
