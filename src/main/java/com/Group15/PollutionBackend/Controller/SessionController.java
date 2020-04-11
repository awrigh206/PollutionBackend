/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andrew Wright
 */
@RestController
@CrossOrigin (origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/session")
public class SessionController 
{
    @PostMapping("/destroy")
    public String destroySession(HttpServletRequest request) {
            request.getSession().invalidate();
            return "redirect:/";
    }
    
    @GetMapping("/user")
    public Object sessionUser(HttpServletRequest request) {
       return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
    @GetMapping("/id")
    public String getSessionId(HttpServletRequest request)
    {
        return request.changeSessionId();
    }
    
    @GetMapping("/exit")
    public String getSessionId()
    {
        System.exit(0);
        return  "Exiting";
    }
    
}
