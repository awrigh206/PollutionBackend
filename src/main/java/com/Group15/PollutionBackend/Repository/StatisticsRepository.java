/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Repository;

import com.Group15.PollutionBackend.Model.Statistics;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Andrew Wright
 */
public interface StatisticsRepository extends JpaRepository<Statistics,Integer> 
{
    List<Statistics> findAllByCountryCode(@Param("code") String countryCode);
    List<Statistics> deleteAllByCountryCode(@Param("code")String countryCode);
}
