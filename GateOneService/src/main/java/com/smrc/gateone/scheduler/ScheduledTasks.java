package com.smrc.gateone.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.smrc.gateone.service.EmailSenderService;
import java.io.IOException;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	@Autowired
	private EmailSenderService emailSenderService;

	@Scheduled(cron = "*/60 * * * * *")
	public void sendEmail() throws IOException{

		//emailSenderService.sendSimpleEmail("anil.saw@mind-infotech.com", "hello", "Email Scheduler Test");
		emailSenderService.sendEmailFromSendGrid();

		log.info("email scheduling");

	}

}
