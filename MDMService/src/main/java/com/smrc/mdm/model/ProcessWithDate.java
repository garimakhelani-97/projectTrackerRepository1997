package com.smrc.mdm.model;

import java.io.Serializable;
import java.util.Date;

public class ProcessWithDate implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Process process;
	private Date startDate;
	private Date endDate;
	
	public Process getProcess() {
		return process;
	}
	public void setProcess(Process process) {
		this.process = process;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
