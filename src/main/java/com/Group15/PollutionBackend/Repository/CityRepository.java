/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Repository;

import com.Group15.PollutionBackend.Model.City;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Andrew Wright
 */
public interface CityRepository extends PagingAndSortingRepository<City,Integer> 
{

    @Override
    public void deleteAll();

    @Override
    public void deleteAll(Iterable<? extends City> itrbl);

    @Override
    public void delete(City u);

    @Override
    public void deleteById(Integer id);

    @Override
    public <S extends City> Iterable<S> saveAll(Iterable<S> itrbl);

    @Override
    public <S extends City> S save(S s);
    
    City findByName(@Param("name") String name);
    
    
    
}
