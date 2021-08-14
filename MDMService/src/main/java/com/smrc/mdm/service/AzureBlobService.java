package com.smrc.mdm.service;

import java.net.URI;
import java.security.InvalidKeyException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import com.mind.azure.storage.service.AzureBlobAdapter;
import com.smrc.mdm.model.FileMetaData;
import com.smrc.mdm.model.Project;
import com.smrc.mdm.repository.FileMetaDataRepository;
import com.smrc.mdm.repository.ProjectRepository;

@Configuration
public class AzureBlobService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AzureBlobService.class);

	@Autowired
	private FileMetaDataRepository fileMetaDataRepository;
		
	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private AzureBlobAdapter azureBlobAdapter;

	public boolean createContainer(String containerName) throws InvalidKeyException {
		boolean containerCreated = false;
		try {
			containerCreated = azureBlobAdapter.createContainer(containerName);
		} catch (Exception e) {
			LOGGER.error("createContainer() : exception while creating container -> " , e);
		}
		return containerCreated;
	}

	public List<Object> listBlobs(String containerName) throws InvalidKeyException {
		List<Object> uris = new ArrayList<Object>();
		try {
			uris = azureBlobAdapter.listBlobs(containerName);
		} catch (Exception e) {
			LOGGER.error("listBlobs(String containerName) : exception while getting blob list -> " , e);
		}
		return uris;
	}

	public void deleteBlob(String containerName, String blobName) throws InvalidKeyException {
		try {
			azureBlobAdapter.deleteBlob(containerName, blobName);
		} catch (Exception e) {
			LOGGER.error("deleteBlob(String containerName, String blobName) : exception while deleting blob -> " , e);
		}
	}
	
	public void deleteBlob(String blobName) throws InvalidKeyException {
		try {
			azureBlobAdapter.deleteBlob(blobName);
		} catch (Exception e) {
			LOGGER.error("deleteBlob(String blobName) : exception while deleting blob -> " , e);
		}
	}

	public URI upload(MultipartFile multipartFile,String containerName , int projectId,
			String imageType) throws InvalidKeyException {
		URI uri = null;
		FileMetaData fileMetaData1 = null;
		Project project = null;
		try {
			
			Optional<Project> projectOptional = projectRepository.findById(projectId);
			
			if(projectOptional.isPresent()) {
				project = projectOptional.get();
				
				if(StringUtils.equalsIgnoreCase("product", imageType)) {
					fileMetaData1 = project.getProductFileMetaData();
				} else if(StringUtils.equalsIgnoreCase("part", imageType)) {
					fileMetaData1 = project.getVehicleFileMetaData();
				}
				
				if(fileMetaData1 == null) {
					fileMetaData1 = new FileMetaData();
				}
				
				String blobName = project.getProjectId() + "-" + imageType + "-" + multipartFile.getOriginalFilename();

				uri = azureBlobAdapter.upload(multipartFile.getInputStream(), blobName);
				

				fileMetaData1.setFileName(multipartFile.getOriginalFilename());
				fileMetaData1.setFilePath(uri.toURL().toString());
				long fileSize = multipartFile.getSize();
				fileMetaData1.setFileSize(fileSize);
				
				fileMetaData1.setFileType(multipartFile.getContentType());
				fileMetaData1.setImageType(imageType);
				
				fileMetaData1 = fileMetaDataRepository.save(fileMetaData1);
				
				if(StringUtils.equalsIgnoreCase("product", imageType)) {
					project.setProductFileMetaData(fileMetaData1);
				} else if(StringUtils.equalsIgnoreCase("part", imageType)) {
					project.setVehicleFileMetaData(fileMetaData1);
				}

				projectRepository.save(project);
			}
				
			
			
			
		} catch (Exception e) {
			LOGGER.error("upload(MultipartFile multipartFile,String containerName , int projectId, String imageType) "
					+ ": exception while uploading images -> " , e);
		}
		return uri;
	}
	
	public URI upload(MultipartFile multipartFile, int projectId,
			String imageType) throws InvalidKeyException {
		URI uri = null;
		FileMetaData fileMetaData1 = null;
		Project project = null;
		try {
			
			Optional<Project> projectOptional = projectRepository.findById(projectId);
			
			if(projectOptional.isPresent()) {
				project = projectOptional.get();
				
				if(StringUtils.equalsIgnoreCase("product", imageType)) {
					fileMetaData1 = project.getProductFileMetaData();
				} else if(StringUtils.equalsIgnoreCase("part", imageType)) {
					fileMetaData1 = project.getVehicleFileMetaData();
				}
				
				if(fileMetaData1 == null) {
					fileMetaData1 = new FileMetaData();
				}
				String blobName = project.getProjectId() + "-" + imageType + "-" + multipartFile.getOriginalFilename();

				uri = azureBlobAdapter.upload(multipartFile.getInputStream(), blobName);
				

				fileMetaData1.setFileName(multipartFile.getOriginalFilename());
				fileMetaData1.setFilePath(uri.toURL().toString());
				long fileSize = multipartFile.getSize();
				fileMetaData1.setFileSize(fileSize);
				
				fileMetaData1.setFileType(multipartFile.getContentType());
				fileMetaData1.setImageType(imageType);
				fileMetaData1.setBlobName(blobName);
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
				String uploadedDate = formatter.format(new Date());
				
				fileMetaData1.setUploadedDate(uploadedDate);
				
				fileMetaData1 = fileMetaDataRepository.save(fileMetaData1);
				
				if(StringUtils.equalsIgnoreCase("product", imageType)) {
					project.setProductFileMetaData(fileMetaData1);
				} else if(StringUtils.equalsIgnoreCase("part", imageType)) {
					project.setVehicleFileMetaData(fileMetaData1);
				}

				projectRepository.save(project);
				
			}
				
			
			
		} catch (Exception e) {
			LOGGER.error("upload(MultipartFile multipartFile, int projectId, String imageType) : exception while uploading images -> " , e);
		}
		return uri;
	}

	public URI getBlobUri(int projectId, String imageType)
			throws InvalidKeyException {
		URI uri = null;
		FileMetaData fileMetaData = null;
		try {
		
			
			Optional<Project> projectOptional = projectRepository.findById(projectId);
			
			if(projectOptional.isPresent()) {
			
				if(StringUtils.equalsIgnoreCase("product", imageType)) {
					fileMetaData = projectOptional.get().getProductFileMetaData();
				} else if(StringUtils.equalsIgnoreCase("part", imageType)) {
					fileMetaData = projectOptional.get().getVehicleFileMetaData();
				}
				String blobName = projectOptional.get().getProjectId() + "-"
						+ fileMetaData.getImageType() + "-" + fileMetaData.getFileName();
			
				uri = azureBlobAdapter.getBlobUri(blobName);
			}
			
			
		} catch (Exception e) {
			LOGGER.error("getBlobUri(int projectId, String imageType) : exception while getting blob uri -> " , e);
		}
		return uri;
	}
	
	public URI getBlobUri(int processId, int projectId, String imageType)
			throws InvalidKeyException {
		URI uri = null;
		FileMetaData fileMetaData = null;
		try {
		
			
			Optional<Project> projectOptional = projectRepository.findById(projectId);
			
			if(projectOptional.isPresent()) {
				
				if(StringUtils.equalsIgnoreCase("product", imageType)) {
					fileMetaData = projectOptional.get().getProductFileMetaData();
				} else if(StringUtils.equalsIgnoreCase("part", imageType)) {
					fileMetaData = projectOptional.get().getVehicleFileMetaData();
				}
				
				String blobName = projectOptional.get().getProjectId() + "-"
						+ fileMetaData.getImageType() + "-" + fileMetaData.getFileName();
				uri = azureBlobAdapter.getBlobUri(blobName);
			}
			
			
		} catch (Exception e) {
			LOGGER.error("getBlobUri(int processId, int projectId, String imageType) : exception while getting blob uri -> " , e);
		}
		return uri;
	}
	
	public String deleteBlob(int projectId,
			String imageType) throws InvalidKeyException {
		String status = null;
		FileMetaData fileMetaData1 = null;
		Project project = null;
		try {
			//multipartFile.getResource().getFile()
			Optional<Project> projectOptional = projectRepository.findById(projectId);
			
			if(projectOptional.isPresent()) {
				project = projectOptional.get();
				
				if(StringUtils.equalsIgnoreCase("product", imageType)) {
					fileMetaData1 = project.getProductFileMetaData();
				} else if(StringUtils.equalsIgnoreCase("part", imageType)) {
					fileMetaData1 = project.getVehicleFileMetaData();
				}
				
				if(fileMetaData1 == null) {
					status = "image not uploaded";
				}
				
				int fileMetadataId = fileMetaData1.getId();
				
				
				if(StringUtils.equalsIgnoreCase("product", imageType)) {
					project.setProductFileMetaData(null);
				} else if(StringUtils.equalsIgnoreCase("part", imageType)) {
					project.setVehicleFileMetaData(null);
				}

				projectRepository.save(project);
				
				String blobName = fileMetaData1.getBlobName();
				azureBlobAdapter.deleteBlob(blobName);
				fileMetaDataRepository.deleteById(fileMetadataId);
				
				status = "success";
			}
				
			
			
		} catch (Exception e) {
			status = "failed";
			LOGGER.error("deleteBlob(int projectId,	String imageType) : exception while deleting blob -> " , e);
		}
		return status;
	}

}
