/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Repository;

import com.Group15.PollutionBackend.Model.RealTime.ParsedData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andrew Wright
 */
@Repository
public interface ParsedDataRepository extends JpaRepository<ParsedData,Integer> 
{
    //public ParsedData findByCoordinates(CoordinateDto coords);
    public ParsedData findByLatAndLon(@Param("lat")Double lat, @Param("lon")Double lon);
    
}
