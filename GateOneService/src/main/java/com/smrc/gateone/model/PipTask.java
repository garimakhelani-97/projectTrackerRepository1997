package com.smrc.gateone.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pip_task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PipTask implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name="pipActionId")
	private Integer pipActionId;

	@Column(name = "taskDesc")
	private String taskDesc;

	@Column(name = "implDate")
	private String implDate;

	@Column(name = "percentCompletion")
	private String percentCompletion;

	@Column(name = "nextStep")
	private String nextStep;

	@Column(name = "confidenceLevel")
	private String confidenceLevel;

	@Column(name = "createdBy")
	private Integer createdBy;

	@Column(name = "createdDate")
	private String createdDate;

	@Column(name = "updatedBy")
	private Integer updatedBy;

	@Column(name = "updatedDate")
	private String updatedDate;

	@Column(name = "ipAddress")
	private String ipAddress;
	
	

	 

}
