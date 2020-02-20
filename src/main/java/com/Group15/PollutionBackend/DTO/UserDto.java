/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DTO;


/**
 *
 * @author Andrew Wright
 */
public class UserDto
{
    //@Min(0)
    //@Max(100)
    private String userName;
    private String password;
    //@Size( max = 30)
    private String email;
    private String number;

    public UserDto(String userName, String password, String email, String number) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.number = number;
    }
    
    protected UserDto()
    {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    
}
