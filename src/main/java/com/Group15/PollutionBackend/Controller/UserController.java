/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Controller;

import com.Group15.PollutionBackend.DTO.UserDto;
import com.Group15.PollutionBackend.Model.User;
import com.Group15.PollutionBackend.Repository.UserRepository;
import com.Group15.PollutionBackend.Service.UserService;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andrew Wright
 */
@RestController
@RequestMapping(path = "/user")
public class UserController 
{
    
    UserRepository userRepo;
    UserService userService;
    @Autowired
    public UserController (UserRepository userRepo, UserService userService)
    {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    protected UserController() {
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public User getUser(@RequestParam(value ="userName")String userName)
    {
        User user = userRepo.findByUserName(userName);
        return user;
        //return tourRatingRepository.findByPkTourId(tourId).stream().map(tourRating -> toDto(tourRating)).collect(Collectors.toList());
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(int tourId, @RequestBody @Validated UserDto userDto) 
    {
        userService.createUser(userDto);
    }
    
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler (NoSuchElementException.class)
    public String return400(NoSuchElementException ex)
    {
        return ex.getMessage();
    }
}
