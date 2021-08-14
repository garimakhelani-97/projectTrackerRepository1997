package com.smrc.mdm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//,uniqueConstraints=@UniqueConstraint(columnNames={"projectId", "imageType"}
@Entity
@Table(name="file_metadata")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileMetaData implements Serializable {

	private static final long serialVersionUID = -1310156571509780352L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="fileName")
	private String fileName;
	@Column(name="fileType")
	private String fileType;
	@Column(name="fileSize")
	private long fileSize;
	@Column(name="filePath")
	private String filePath;
	@Column(name="imageType")
	private String imageType;	
	@Column(name="blobName")
	private String blobName;
	@Column(name="uploadedDate")
	private String uploadedDate;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name =
	 * "projectId",foreignKey=@ForeignKey(name="file_metadata_projectId_fk"),
	 * referencedColumnName = "id") private Project project;
	 */
}
