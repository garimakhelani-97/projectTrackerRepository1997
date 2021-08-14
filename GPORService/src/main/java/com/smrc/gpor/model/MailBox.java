package com.smrc.gpor.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "mail_box")
@Data
public class MailBox implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer referenceId;
	
	private Integer messageId;
	
	private String mailFromId;
	private String mailToId;
	
	private String mailCcId;
	private String mailBccId;
	
	private String mailSubject;
	private String mailBody;
	private String mailCreatedDate;	
	private Integer mailCreater;
	private Integer trial;
	private String errorSuccess;	
	private String mailSendDate;
	private String sourcePage;
	private String hasAttachment;
	

}
