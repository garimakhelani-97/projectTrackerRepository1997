package com.smrc.mdm.controller;

import java.net.URI;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smrc.mdm.model.Project;
import com.smrc.mdm.model.ProjectMapper;
import com.smrc.mdm.service.AzureBlobService;
import com.smrc.mdm.service.ProjectListService;
import com.smrc.mdm.service.SyncGPORServiceImlp;


@RestController
@RequestMapping("/azure")
public class AzureStorageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AzureStorageController.class);
	
	@Autowired
	private AzureBlobService azureBlobService;
	
	@Autowired
	private ProjectListService projectListService;
	
	@Autowired
	private SyncGPORServiceImlp syncGPORServiceImlp;

	@PostMapping("/container")
	public ResponseEntity<Boolean> createContainer(@RequestBody String containerName) {
		boolean created = false;
		try {
			created = azureBlobService.createContainer(containerName);
		} catch (InvalidKeyException e) {
			LOGGER.error("createContainer() : exception while creating container -> " , e);
		}

		return ResponseEntity.ok(created);
	}

	@PostMapping("/uploadBlobFile")
	public ResponseEntity<List<URI>> uploadBlob(@RequestParam MultipartFile multipartFile, @RequestParam String containerName,
			@RequestParam int projectId, @RequestParam String imageType) {
		List<URI> urlList = new ArrayList<URI>();
		URI url = null;
		try {
			url = azureBlobService.upload(multipartFile, containerName, projectId, imageType);
			urlList.add(url);
		} catch (InvalidKeyException e) {
			LOGGER.error("upload(MultipartFile multipartFile,String containerName , int projectId, String imageType) "
					+ ": exception while uploading images -> " , e);
		}
		return ResponseEntity.ok(urlList);
	}
	
	@PostMapping("/uploadFile/{projectId}")
	public ResponseEntity<Map<String,URI>> upload(@RequestParam(value="productImage", required = false) MultipartFile productImg,
			@RequestParam(value="vehicleImage", required = false) MultipartFile vehicleImg, @PathVariable int projectId) {
		URI url = null;
		LOGGER.info("Product image details:"+ productImg);
		LOGGER.info("Vehicle image details:"+ productImg);
		Map<String,URI> urlMap = new HashMap<String,URI>();
		try {
			if(productImg != null || vehicleImg != null) {
				if(productImg != null) {
					url = azureBlobService.upload(productImg, projectId, "product");
					urlMap.put("product", url);
					
				}
				if (vehicleImg != null) {
					url = azureBlobService.upload(vehicleImg, projectId, "part");
					urlMap.put("vehicle", url);
				}
				
				Project project = projectListService.getProjectByProjectId(projectId);
				for(int i=0;i<project.getProjectProcessAssociationList().size();i++) {
					if (project.getProjectProcessAssociationList().get(i).getProcess()!=null) {
						if(project.getProjectProcessAssociationList().get(i).getProcess().getId()==1)
						{
							ProjectMapper projectMapper = new ProjectMapper();
							projectMapper.setProductImageId(project.getProductFileMetaData().getId());
							projectMapper.setProductImageBlobName(project.getProductFileMetaData().getBlobName());
							projectMapper.setVehicleImageId(project.getVehicleFileMetaData().getId());
							projectMapper.setVehicleImageBlobName(project.getVehicleFileMetaData().getBlobName());
							projectMapper.setUploadedDate(project.getVehicleFileMetaData().getUploadedDate());
							//String response = syncGPORServiceImlp.updateProjectImages(projectMapper,project.getProjectId());
						}
					};
				}
				
			}
					
		} catch (Exception e) {
			LOGGER.error("upload(MultipartFile[] multipartFile,String containerName , int projectId, String imageType) "
					+ ": exception while uploading images -> " , e);
		}
		return ResponseEntity.ok(urlMap);
	}

	@GetMapping("/blobs")
	public ResponseEntity<List<Object>> getAllBlobs(@RequestParam String containerName) {
		List<Object> uris = null;
		try {
			uris = azureBlobService.listBlobs(containerName);
		} catch (InvalidKeyException e) {
			LOGGER.error("getAllBlobs(String containerName) : exception while getting blob list -> " , e);
		}
		return ResponseEntity.ok(uris);
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@RequestParam String containerName, @RequestParam String blobName) {
		try {
			azureBlobService.deleteBlob(containerName, blobName);
		} catch (InvalidKeyException e) {
			LOGGER.error("delete(String containerName, String blobName) : exception while deleting blob -> " , e);
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/blobUrl")
	public ResponseEntity<URI> blobUrl(
			@RequestParam int projectId, @RequestParam String imageType) {
		URI url = null;
		try {
			url = azureBlobService.getBlobUri(projectId, imageType);
		} catch (InvalidKeyException e) {
			LOGGER.error("getBlobUri(int projectId, String imageType) : exception while getting blob uri -> " , e);
		}
		return ResponseEntity.ok(url);
	}
	//Delete images by id
	@PostMapping("/deleteImageById/{projectId}")
	public ResponseEntity<Map<String, String>> deleteImageById(
			 @RequestBody Map<String, String> requestParameterMap
			){
		
		String url = null;
		Map<String,String> urlMap = new HashMap<String,String>();
		try {
			Integer projectId = Integer.parseInt(requestParameterMap.get("projectId"));
			String deleteVechicleImage = requestParameterMap.get("deleteVechicleImage");
			String deleteProductImage = requestParameterMap.get("deleteProductImage");
			if(StringUtils.equalsIgnoreCase("true", deleteProductImage)) {
				url = azureBlobService.deleteBlob(projectId, "product");
				urlMap.put("product", url);
				
			}
			if (StringUtils.equalsIgnoreCase("true", deleteVechicleImage)) {
				url = azureBlobService.deleteBlob(projectId, "part");
				urlMap.put("vehicle", url);
			}
			
			Project project = projectListService.getProjectByProjectId(projectId);
			for(int i=0;i<project.getProjectProcessAssociationList().size();i++) {
				if (project.getProjectProcessAssociationList().get(i).getProcess()!=null) {
					if(project.getProjectProcessAssociationList().get(i).getProcess().getId()==1)
					{
						ProjectMapper projectMapper = new ProjectMapper();
						if(StringUtils.equalsIgnoreCase("true", deleteProductImage) && urlMap.get("product") == "success") {
							projectMapper.setProductImageId(null);
							projectMapper.setProductImageBlobName(null);
						}
						
						if(StringUtils.equalsIgnoreCase("true", deleteVechicleImage) && urlMap.get("vehicle") == "success") {
							projectMapper.setVehicleImageId(null);
							projectMapper.setVehicleImageBlobName(null);
						}
						//syncGPORServiceImlp.updateProjectImages(projectMapper,project.getProjectId());
					}
				}
			}
				
		} catch (Exception e) {
			LOGGER.error("deleteImageById(@RequestBody Map<String, String> requestParameterMap) "
					+ ": exception while deleting images -> " , e);
		}
		return ResponseEntity.ok(urlMap);
	}
	
}
