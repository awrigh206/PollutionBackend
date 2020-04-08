/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Alerts;

/**
 *
 * @author Andrew Wright
 */
public interface IAlert 
{
    public void sendAlert(String message, String person);
}
