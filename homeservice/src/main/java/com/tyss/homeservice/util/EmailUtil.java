package com.tyss.homeservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	@Autowired
	JavaMailSender mailSender;
	
	public void sendEmail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("raviujjwal6@gmail.com");
        message.setSubject("Account created");
        message.setText("Thankyou for registering with HomeService");

        mailSender.send(message);
    }
	public void sendEmailVendor(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("raviujjwal6@gmail.com");
        message.setSubject("Account created");
        message.setText("Thankyou for registering with HomeService to find work");

        mailSender.send(message);
    }
}
