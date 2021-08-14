package com.smrc.gpor.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "action_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActionMaster implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String actionKey; 
	private String description; 
	private Integer statusId; 
	private String ipAddress; 
	private Integer createdBy; 
	private String createdDate; 
	private Integer updatedBy; 
	private String updatedDate;


}
