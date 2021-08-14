package com.smrc.gateone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="preview_screen")
public class PreviewsScreen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	private Integer projectId; 
	private Integer isFreezed;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getIsFreezed() {
		return isFreezed;
	}
	public void setIsFreezed(Integer isFreezed) {
		this.isFreezed = isFreezed;
	}
	
}
