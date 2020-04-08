/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

/**
 *
 * @author Andrew Wright
*/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter 
{
    @Autowired
    private DataSource dataSource;
    @Override  
    public void configure(HttpSecurity http) throws Exception {  
        //http.authorizeRequests().antMatchers("/user").permitAll();
        //http.authorizeRequests().antMatchers("/user/**").permitAll();
        http.authorizeRequests().antMatchers("/**").permitAll();
        http.csrf().disable();
        http  
            .authorizeRequests()  
            .anyRequest().authenticated()  
            .and()  
            .httpBasic();
    }  
    @Override  
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  
             
        
        auth.jdbcAuthentication()
        .dataSource(dataSource)
        .withDefaultSchema()
        .withUser("user")  
        .password("{noop}pass") // Spring Security 5 requires specifying the password storage format  
        .roles("USER"); 
        

    }  
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception {
            JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
            jdbcUserDetailsManager.setDataSource(dataSource);
            return jdbcUserDetailsManager;
    }

}