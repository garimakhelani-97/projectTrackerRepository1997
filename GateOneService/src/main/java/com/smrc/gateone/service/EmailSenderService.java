package com.smrc.gateone.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailSenderService {
	
	@Value("${sendgridApi}")
	String sendGridApi;

	@Autowired
	private JavaMailSender mailsender;

	public void sendSimpleEmail(String toEmail, String body, String subject) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("Vaibhav.Dhawan@mind-infotech.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		 
		mailsender.send(message);

	}
	
	public void sendEmailFromSendGrid() throws IOException {
      
		Email from = new Email();
		from.setEmail("Anil.Saw@mind-infotech.com");
	    String subject = "Sending with SendGrid";
	    Email to = new Email();
	    to.setEmail("Vaibhav.Dhawan@mind-infotech.com");
	    Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
	    Mail mail = new Mail(from, subject, to, content);
	    //mail.setTemplateId("irYGcMSVRUOjgw9Uz4Cmyg");

	    SendGrid sg = new SendGrid(sendGridApi);
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      throw ex;
	    }
		
	}
	

}