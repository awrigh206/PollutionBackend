/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package com.Group15.PollutionBackend.Repository;

import com.Group15.PollutionBackend.Model.Coordinates;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Andrew Wright
 
public interface CoordinatesRepository extends PagingAndSortingRepository<Coordinates,Integer> 
{
    @Override
    public void deleteAll();

    @Override
    public void deleteAll(Iterable<? extends Coordinates> itrbl);

    @Override
    public void delete(Coordinates u);

    @Override
    public void deleteById(Integer id);

    @Override
    public <S extends Coordinates> Iterable<S> saveAll(Iterable<S> itrbl);

    @Override
    public <S extends Coordinates> S save(S s);
    
}
*/