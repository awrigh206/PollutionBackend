/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Repository;

import com.Group15.PollutionBackend.Model.City;
import java.util.ArrayList;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Andrew Wright
 */
public interface CityRepository extends PagingAndSortingRepository<City,Long> 
{

    @Override
    public void deleteAll();

    @Override
    public void deleteAll(Iterable<? extends City> itrbl);

    @Override
    public void delete(City u);


    @Override
    public <S extends City> Iterable<S> saveAll(Iterable<S> itrbl);

    @Override
    public <S extends City> S save(S s);
    
    City findByName(@Param("name") String name);
    ArrayList<City> findAllByName(@Param("name") String name);
    ArrayList<City> findAllByCountryCode(@Param("country") String country);
    ArrayList<City> findAllByNameAndCountryCode(@Param("name") String name, @Param("country") String country);
    City findByNameAndCountryCode(@Param("name") String name, @Param ("country") String country);
    City[] findByCountryCode(@Param("country") String country);
    
    
    
}
