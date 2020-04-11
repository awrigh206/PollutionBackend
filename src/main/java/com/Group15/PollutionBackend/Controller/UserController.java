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
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class UserController 
{
    @Autowired 
    private PasswordEncoder passwordEncoder;
    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;
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
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/auth")
    public Object authenticateUser(@RequestParam(value ="userName")String userName, @RequestParam(value="password") String password)
    {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userRepo.findByUserName(userName);
        if(user != null && passwordEncoder.matches(password, user.getPassword()))
        {
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return user;
        }
        else
            return false;
    }
    
    @RequestMapping(method = RequestMethod.POST, path = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Validated UserDto userDto) 
    {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        User user = new User(userDto.getUserName(), passwordEncoder.encode(userDto.getPassword()),userDto.getEmail(),userDto.getNumber(),authorities);
        userService.createUser(user);
        //jdbcUserDetailsManager.createUser(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler (NoSuchElementException.class)
    public String return400(NoSuchElementException ex)
    {
        return ex.getMessage();
    }
}
