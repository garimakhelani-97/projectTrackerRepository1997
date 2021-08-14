package com.smrc.gpor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="preview_screen")
@Data
public class PreviewsScreen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	private Integer projectId; 
	private String reportedMonth; 
	private Integer isFreezed;
	
}
