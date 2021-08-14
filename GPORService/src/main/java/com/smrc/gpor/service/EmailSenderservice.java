package com.smrc.gpor.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderservice {
	
	@Autowired
	private JavaMailSender mailsender;
	
	public void sendSimpleEmail(String toEmail[], String body, String subject) throws MessagingException {
		
		MimeMessage message = mailsender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		//SimpleMailMessage message = new SimpleMailMessage();
		//message.setFrom("Vaibhav.Dhawan@mind-infotech.com");
	    helper.setTo(toEmail);
	    helper.setText(body,true);
	    helper.setSubject(subject);

		mailsender.send(message);

	}

}
