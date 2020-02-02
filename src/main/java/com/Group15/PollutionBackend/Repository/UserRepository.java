/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Repository;

import com.Group15.PollutionBackend.Model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Andrew Wright
 */
public interface UserRepository extends PagingAndSortingRepository<User,Integer> 
{
    @Override
    public void deleteAll();

    @Override
    public void deleteAll(Iterable<? extends User> itrbl);

    @Override
    public void delete(User u);

    @Override
    public void deleteById(Integer id);

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> itrbl);

    @Override
    public <S extends User> S save(S s);
    
    User findByUserName(@Param("name") String name);
    User findByEmail(@Param("email") String email);
}
