package com.smrc.gateone.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pip_action")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PipAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	
	private Integer id;

	@OneToOne
	@JoinColumn(name = "projectId", foreignKey = @ForeignKey(name = "pipaction_projectId"), referencedColumnName = "id")
	private Project project;
	
	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="pipActionId")
    private Set<PipTask> pipTasks;

	@Column(name = "pipaction")
	private String pipaction;

	@Column(name = "lcVeh")
	private String lcVeh;

	@Column(name = "conflevel")
	private String conflevel;

	@Column(name = "ebitImpact")
	private String ebitImpact;

	@Column(name = "irrImpact")
	private String irrImpact;

	@Column(name = "roceImpact")
	private String roceImpact;

	@Column(name = "implDate")
	private String implDate;

	@Column(name = "comment")
	private String comment;

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
