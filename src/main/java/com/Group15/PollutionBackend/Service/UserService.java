/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Service;

import com.Group15.PollutionBackend.DTO.UserDto;
import com.Group15.PollutionBackend.Model.User;
import com.Group15.PollutionBackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrew Wright
 */
@Service
public class UserService 
{
    private UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) 
    {
        this.userRepository = userRepository;
    }
    
    public User createUser(String name, String password, String email, String number)
    {
        User user = userRepository.findByUserName(name);
        //TourPackage tourPackage = tourPackageOpt.get();
        if(user == null)
        {
            return userRepository.save(new User(name,password,email,number));
        }
        else
        {
            return null;
        }
        
    }
    
    public User createUser(UserDto dto)
    {
        User user = userRepository.findByUserName(dto.getUserName());
        //TourPackage tourPackage = tourPackageOpt.get();
        if(user == null)
        {
            return userRepository.save(new User(dto.getUserName(),dto.getPassword(),dto.getEmail(),dto.getNumber()));
        }
        else
        {
            return null;
        }
        
    }
    
    public User createUser(User toAdd)
    {

        return userRepository.save(toAdd);
        
    }
    
    
    public long total()
    {
        return userRepository.count();
    }
}
