package com.appatam.Suzang_Group_Back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	public SimpleMailMessage sendeEmail(String toEmail, String subject,String body) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("sangareadama7930@gmail.com");
		
		message.setTo(toEmail);
		
		message.setText(body);
		
		message.setSubject(subject);
		
		mailSender.send(message);
		
		
		return message;
	}
	
   public SimpleMailMessage ContactUs(String fromEmail, String subject,String body) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(fromEmail);
		
		message.setTo("sangareadama7930@gmail.com");
		
		message.setText(body); 
		
		message.setSubject(subject); 
		
		mailSender.send(message);
		
		
		return message;
	}

}
