/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.DataProcessing.Batch;

import com.Group15.PollutionBackend.DataProcessing.JSON.RetrieveData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * @author Andrew Wright
 */
@Configuration
public class ThreadConfig 
{
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() 
    {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("default_task_executor_thread");
        executor.initialize();
        return executor;
    }
    
    @Bean
     public RetrieveData retdata() {
        return new RetrieveData(10000);
    } 
     
     @Bean
     public Integer number() {
        return 0;
    }
}