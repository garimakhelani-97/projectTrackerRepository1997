package com.smrc.api.users.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "UserAppAccess_Log")
@Data
public class UserAppAccessLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "domain")
	String domain;
	
	@Column(name = "winuserid")
	String winuserid;
	
	@Column(name = "token")
	String token;
	
	@Column(name = "createdOn")
	@CreationTimestamp
	Date createdOn;
	
	@Column(name = "AccessDate")
	@CreationTimestamp
    Date accessdate;
	
	@Column(name = "Status")	
    String status;
	
	@Column(name = "connectedTo")	
    String connectedto;
	
	@Column(name = "ErrorCode")	
    int errorcode;
	
	@Column(name = "userAgent")	
    String useragent;

}
