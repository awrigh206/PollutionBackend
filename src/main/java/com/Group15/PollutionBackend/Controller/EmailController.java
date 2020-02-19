/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Controller;

import Alerts.EmailAlert;
import Alerts.IAlert;
import com.Group15.PollutionBackend.DTO.EmailDto;
import com.Group15.PollutionBackend.DTO.UserDto;
import com.Group15.PollutionBackend.StartupRunner;
import java.util.NoSuchElementException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andrew Wright
 */
@RestController
@RequestMapping(path = "/email")
public class EmailController 
{
    private final Log log = LogFactory.getLog(EmailController.class);
    EmailAlert alert;
    public EmailController() 
    {
        alert = new EmailAlert();
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser( @RequestBody EmailDto emailDto) 
    {
        //log.info(emailDto.toString());
        alert.sendAlert(emailDto);
    }
    
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler (NoSuchElementException.class)
    public String return400(NoSuchElementException ex)
    {
        return ex.getMessage();
    }
}
