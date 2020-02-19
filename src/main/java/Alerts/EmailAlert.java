/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alerts;

import com.Group15.PollutionBackend.DTO.EmailDto;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Andrew Wright
 */
public class EmailAlert implements IAlert
{
    
    public void sendAlert(EmailDto emailDto)
    {
        sendAlert(emailDto.getMessage(), emailDto.getAddress());
    }

    /**
     * Send an email alert
     * @param message - The message in the main body of the email
     * @param person - The email address of the person to receive the email alert
     */
    @Override
    public void sendAlert(String message, String person) 
    {
        Email email = new SimpleEmail();
        email.setHostName("smtp.office365.com");
        email.setStartTLSEnabled(true);
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("PollutionBackend@outlook.com","projectAlert"));
        email.setAuthentication("PollutionBackend@outlook.com","projectAlert");
        try
        {
            email.setFrom("PollutionBackend@outlook.com");
            email.setSubject("TestMail");
            email.setMsg(message);
            email.addTo(person);
            email.send();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }
    
}
