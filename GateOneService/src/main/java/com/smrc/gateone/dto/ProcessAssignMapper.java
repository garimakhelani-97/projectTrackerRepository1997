package com.smrc.gateone.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProcessAssignMapper implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Map> processWithDateList;
	private Map projectController;
	private Map projectManager;
	private Map programManager;
	
	
	
}

 class Process implements Serializable{
	private Date endDate;
	private Map process;
	private Date startDate;

}
