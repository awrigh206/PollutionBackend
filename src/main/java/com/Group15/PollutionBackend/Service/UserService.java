/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Service;

import com.Group15.PollutionBackend.DTO.UserDto;
import com.Group15.PollutionBackend.DataProcessing.Batch.FetcherThread;
import com.Group15.PollutionBackend.Model.User;
import com.Group15.PollutionBackend.Repository.UserRepository;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrew Wright
 */
@Service
public class UserService 
{
    private UserRepository userRepository;
    private final Log log = LogFactory.getLog(UserService.class);
    
    @Autowired
    public UserService(UserRepository userRepository) 
    {
        this.userRepository = userRepository;
    }
    
    private User saveUser(String name, String password, String email, String number, List<GrantedAuthority> authorities)
    {
        User user = userRepository.findByUserName(name);
        if(user == null)
        {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);
            return userRepository.save(new User(name,hashedPassword,email,number,authorities));
        }
        else
        {
            return null;
        }
        
    }
    
    public User createUser(UserDto dto)
    {
        //return saveUser(dto.getUserName(),dto.getPassword(),dto.getEmail(),dto.getNumber());
        return null;
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
