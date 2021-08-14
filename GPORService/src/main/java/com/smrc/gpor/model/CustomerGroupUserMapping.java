package com.smrc.gpor.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_group_user_mapping")
public class CustomerGroupUserMapping implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Integer  customerGroupId;
  private String customerGroupName;
  private Integer userId;
  private String userName;
  private String userEmail;
  private Integer statusId;
  private String ipAddress;
  private Integer createdBy;
  private String createdDate;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getCustomerGroupId() {
	return customerGroupId;
}
public void setCustomerGroupId(Integer customerGroupId) {
	this.customerGroupId = customerGroupId;
}
public String getCustomerGroupName() {
	return customerGroupName;
}
public void setCustomerGroupName(String customerGroupName) {
	this.customerGroupName = customerGroupName;
}
public Integer getUserId() {
	return userId;
}
public void setUserId(Integer userId) {
	this.userId = userId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getUserEmail() {
	return userEmail;
}
public void setUserEmail(String userEmail) {
	this.userEmail = userEmail;
}
public Integer getStatusId() {
	return statusId;
}
public void setStatusId(Integer statusId) {
	this.statusId = statusId;
}
public String getIpAddress() {
	return ipAddress;
}
public void setIpAddress(String ipAddress) {
	this.ipAddress = ipAddress;
}
public Integer getCreatedBy() {
	return createdBy;
}
public void setCreatedBy(Integer createdBy) {
	this.createdBy = createdBy;
}
public String getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(String createdDate) {
	this.createdDate = createdDate;
}
  
  
}
