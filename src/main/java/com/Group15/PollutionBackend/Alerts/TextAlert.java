/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Alerts;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Andrew Wright
 */
public class TextAlert implements IAlert
{
    public static final String ACCOUNT_SID ="AC3f9311f27468b770baf4e901592d73bc";
    public static final String AUTH_TOKEN ="3fdb4a590fdc315b4e2b3cb3b33c7ff2";
    
    @Override
    public void sendAlert(String message, String person) 
    {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message textMessage = Message
        .creator(new PhoneNumber(person), // to
                new PhoneNumber("+19315480441"), // from
                message)
        .create();
    }
    
    
}
