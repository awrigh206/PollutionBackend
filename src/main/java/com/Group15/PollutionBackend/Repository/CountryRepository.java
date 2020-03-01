/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Repository;

import com.Group15.PollutionBackend.Model.Country;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Andrew Wright
 */
public interface CountryRepository extends JpaRepository<Country,Integer> 
{
    @Override
    public void deleteAll();

    @Override
    public void deleteAll(Iterable<? extends Country> itrbl);

    @Override
    public void delete(Country u);

    @Override
    public void deleteById(Integer id);

    //@Override
    //public <S extends Country> Iterable<S> saveAll(Iterable<S> itrbl);

    @Override
    public <S extends Country> List<S> saveAll(Iterable<S> itrbl);
    
    

    @Override
    public <S extends Country> S save(S s);
    @Override
    public <S extends Country> S saveAndFlush(S s);
    
    Country findByName(@Param("name") String name);
    Country findByCountryCode(@Param("code") String code);
    long deleteByCountryCode(@Param("code") String code);
    ArrayList<Country> findAllByCountryCode(@Param("code") String code);
    ArrayList<Country> findAllByName(@Param("name") String name);
    //Country findByCountryCode(@Param("code") String code);
    @Override
    ArrayList<Country> findAll();
}
