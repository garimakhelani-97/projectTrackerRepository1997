package com.smrc.gateone.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table(name ="gate1_project_process_association")
@Data
public class Gate1ProjectProcessAssociation implements Serializable {
	  private static final long serialVersionUID = 1L;
	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private Integer id;
	  private Integer  processId;
	  private Integer projectId;
	  private String startDate;
	  private String endDate ;
	  private Integer projectControllerId;
	  private Integer projectManagerId ;
	  private Integer programManagerId ;
	  private Integer recordStatusId ;
	  private String createdBy ;
	  private String createdDate; 
	  private String updatedBy; 
	  private String updatedDate;
	  private String ipAddress;

}
