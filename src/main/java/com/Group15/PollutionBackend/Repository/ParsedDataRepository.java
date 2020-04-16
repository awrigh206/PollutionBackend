/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Repository;

import com.Group15.PollutionBackend.DTO.CoordinateDto;
import com.Group15.PollutionBackend.Model.RealTime.ParsedData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Andrew Wright
 */
public interface ParsedDataRepository extends JpaRepository<ParsedData,Integer> 
{
    public ParsedData findByCoordinates(CoordinateDto coords);
}
