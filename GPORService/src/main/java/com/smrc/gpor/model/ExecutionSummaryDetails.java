package com.smrc.gpor.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "executive_summary" )
public class ExecutionSummaryDetails implements Serializable {
	
	
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id; 
		private String month;
		private String year;
		private Integer processId;
		private String summary;
		private Integer kpiId;
		private Integer userId;
		private String ipAddress;
		//private Integer createdBy;
		private String createdDate;
		private Integer updatedBy;
		private String updatedDate;
		
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getMonth() {
			return month;
		}
		public void setMonth(String month) {
			this.month = month;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public Integer getProcessId() {
			return processId;
		}
		public void setProcessId(Integer processId) {
			this.processId = processId;
		}
		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}
		public Integer getKpiId() {
			return kpiId;
		}
		public void setKpiId(Integer kpiId) {
			this.kpiId = kpiId;
		}
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public String getIpAddress() {
			return ipAddress;
		}
		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}

		/*
		 * public Integer getCreatedBy() { return createdBy; } public void
		 * setCreatedBy(Integer createdBy) { this.createdBy = createdBy; }
		 */
		public String getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(String createdDate) {
			this.createdDate = createdDate;
		}
		public Integer getUpdatedBy() {
			return updatedBy;
		}
		public void setUpdatedBy(Integer updatedBy) {
			this.updatedBy = updatedBy;
		}
		public String getUpdatedDate() {
			return updatedDate;
		}
		public void setUpdatedDate(String updatedDate) {
			this.updatedDate = updatedDate;
		}
		
		
		
}
