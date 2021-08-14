package com.smrc.gateone.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smrc.gateone.dto.GateAndMetricDetailsDTO;
import com.smrc.gateone.dto.ProjectGateDetailsDTO;
import com.smrc.gateone.dto.ProjectMetricDetailsDTO;
import com.smrc.gateone.model.Gate;
import com.smrc.gateone.model.Metrics;
import com.smrc.gateone.model.PreviewsScreen;
import com.smrc.gateone.model.ProjectGateDetails;
import com.smrc.gateone.model.ProjectMetricsDetail;
import com.smrc.gateone.repository.GateRepository;
import com.smrc.gateone.repository.MetricsRepository;
import com.smrc.gateone.repository.PreviewsScreenRepository;
import com.smrc.gateone.repository.ProjectGateDetailRepository;
import com.smrc.gateone.repository.ProjectMetricsDetailRepository;
import com.smrc.gateone.service.GateAndMetricService;

@Service
public class GateAndMetricServiceImpl implements GateAndMetricService {
	@Autowired
	private ProjectMetricsDetailRepository projectMetricsDetailRepository;
	@Autowired
	private MetricsRepository metricsRepository;
	@Autowired
	GateRepository gateRepository;
	@Autowired
	PreviewsScreenRepository previewsScreenRepository;
	@Autowired
	ProjectGateDetailRepository projectGateDetailRepository;

	@Override
	public List<Map> getMetricDetails(Integer projectId) {

		List<Metrics> defaultMetrics = metricsRepository.findAll();
		List<ProjectMetricsDetail> resultList = projectMetricsDetailRepository.findByprojectId(projectId);
		List<Map> defaultList = new ArrayList<Map>();
		// check data already present or not
		if (resultList.size() == 0) {
			for (Metrics singleMetric : defaultMetrics) {
				Map<String, Object> metricObject = new HashMap<String, Object>();
				metricObject.put("projectId", projectId);
				metricObject.put("name", singleMetric.getName());
				metricObject.put("description", singleMetric.getDescription());
				metricObject.put("metricsId", singleMetric.getId());
				metricObject.put("comment", null);
				metricObject.put("metricPKId", null);
				defaultList.add(metricObject);
			}
		} else {
			HashMap<Integer, ProjectMetricsDetail> custom = prepareMatricsDetailMap(resultList);
			ProjectMetricsDetail projectMetricsDetail = null;
			for (Metrics singleMetric : defaultMetrics) {
				Map<String, Object> metricObject = new HashMap<String, Object>();
				metricObject.put("projectId", projectId);
				metricObject.put("name", singleMetric.getName());
				metricObject.put("description", singleMetric.getDescription());
				metricObject.put("metricsId", singleMetric.getId());
				projectMetricsDetail = custom.get(singleMetric.getId());
				// Handle null pointer
				if (projectMetricsDetail != null) {
					metricObject.put("comment", projectMetricsDetail.getComment());
					metricObject.put("metricPKId", projectMetricsDetail.getId());
				} else {
					metricObject.put("comment", null);
					metricObject.put("metricPKId", null);
				}
				defaultList.add(metricObject);
			}
		}
		return defaultList;
	}

	private HashMap<Integer, ProjectMetricsDetail> prepareMatricsDetailMap(List<ProjectMetricsDetail> resultList) {
		HashMap<Integer, ProjectMetricsDetail> metricDataMap = new HashMap<Integer, ProjectMetricsDetail>();
		for (ProjectMetricsDetail projectMetricsDetail : resultList) {
//			if (StringUtils.isNotEmpty(projectMetricsDetail.getComment())) {
//				metricCommentMap.put(projectMetricsDetail.getMetricsId(), projectMetricsDetail.getComment());
//			}else {
//				metricCommentMap.put(projectMetricsDetail.getMetricsId(), "");
//			} 
			if (projectMetricsDetail != null) {
				metricDataMap.put(projectMetricsDetail.getMetricsId(), projectMetricsDetail);
			}
		}
		return metricDataMap;
	}

	@Override
	public List<Map> getGateDetails(Integer projectId) {
		List<Gate> gateHeaders = gateRepository.findAllByOrderByOrderAsc();
		List<ProjectGateDetails> gateDataByProject = projectGateDetailRepository.findByprojectId(projectId);
		List<Map> defaultList = new ArrayList<Map>();
		if (gateDataByProject.size() == 0) {
			for (Gate gateDate : gateHeaders) {
				Map<String, Object> gateObject = new HashMap<String, Object>();
				gateObject.put("id", gateDate.getId());
				gateObject.put("name", gateDate.getName());
				gateObject.put("description", gateDate.getDescription());
				gateObject.put("gateId", null);
				gateObject.put("initialDate", null);
				gateObject.put("statusId", null);
				gateObject.put("isFreezed", null);
				gateObject.put("gatepKey", null);
				defaultList.add(gateObject);
			}
		} else {
			HashMap<Integer, ProjectGateDetails> custom = prepareGateDetailMap(gateDataByProject);
			ProjectGateDetails projectGateDetails = null;
			for (Gate gateDate : gateHeaders) {
				Map<String, Object> gateObject = new HashMap<String, Object>();
				// ProjectMetricDetailsDTO projectMetricDetailsDTO =
				gateObject.put("id", gateDate.getId());
				gateObject.put("name", gateDate.getName());
				gateObject.put("description", gateDate.getDescription());
				gateObject.put("gateId", gateDate.getId());
				projectGateDetails = custom.get(gateDate.getId());
				// Handle null pointer
				if (projectGateDetails != null) {
					gateObject.put("initialDate", projectGateDetails.getInitialDate());
					gateObject.put("statusId", projectGateDetails.getStatusId());
					gateObject.put("isFreezed", projectGateDetails.getIsFreezed());
					gateObject.put("gatepKey", projectGateDetails.getId());
				} else {
					gateObject.put("initialDate", null);
					gateObject.put("statusId", null);
					gateObject.put("isFreezed", null);
					gateObject.put("gatepKey", null);
				}
				defaultList.add(gateObject);
			}

		}
		return defaultList;
	}

	private HashMap<Integer, ProjectGateDetails> prepareGateDetailMap(List<ProjectGateDetails> resultList) {
		HashMap<Integer, ProjectGateDetails> gateInitialDateMap = new HashMap<Integer, ProjectGateDetails>();
		for (ProjectGateDetails projectGateDetail : resultList) {
			if (projectGateDetail != null) {
				gateInitialDateMap.put(projectGateDetail.getGateId(), projectGateDetail);
			}
		}
		return gateInitialDateMap;
	}

	public PreviewsScreen getPreviewScreen(Integer projectId) {
		PreviewsScreen previewsScreen = previewsScreenRepository.findByProjectId(projectId);
		return previewsScreen;
	}

	public void saveGateAndMetricsDetails(GateAndMetricDetailsDTO gateAndMetricDetailsDTO) {

		// Logic for save metric in Project metric details table
		ProjectMetricsDetail projectMetricObject = null;
		if (!gateAndMetricDetailsDTO.getMetricDetails().isEmpty()) {
			for (ProjectMetricDetailsDTO projectMetricsData : gateAndMetricDetailsDTO.getMetricDetails()) {
				// Check data is already present in Table or Not.
				projectMetricObject = new ProjectMetricsDetail();
				if (projectMetricsData.getMetricPKId() != null) {
					Optional<ProjectMetricsDetail> projectMetricExistingObject = projectMetricsDetailRepository
							.findById(projectMetricsData.getMetricPKId());
					if (projectMetricExistingObject.isPresent()) {
						projectMetricObject = projectMetricExistingObject.get();
					}
				}
				ProjectMetricsDetail projectMetricExistingObject=projectMetricsDetailRepository.findByProjectIdAndMetricId(projectMetricsData.getProjectId(),projectMetricsData.getMetricsId());
				if(projectMetricExistingObject!=null) {
					projectMetricObject=projectMetricExistingObject;
				}
				projectMetricObject.setProjectId(projectMetricsData.getProjectId());
				projectMetricObject.setComment(projectMetricsData.getComment());
				projectMetricObject.setMetricsId(projectMetricsData.getMetricsId());
				projectMetricsDetailRepository.save(projectMetricObject);
			}
		}
			/* Logic to save GateData in ProjectGateDetails table. */
			ProjectGateDetails entity = null;
			for(ProjectGateDetailsDTO singleGateDetail: gateAndMetricDetailsDTO.getProjectGateDetails()) {
			 entity=new ProjectGateDetails();
			if(singleGateDetail.getGatePKId() != null) {
			  Optional<ProjectGateDetails> entry=projectGateDetailRepository.findById(singleGateDetail.getGatePKId());
			  if(entry.isPresent()) {
			      entity=entry.get();
			  }
			}
			ProjectGateDetails entry=projectGateDetailRepository.findByProjectIdandGateId(singleGateDetail.getProjectId(),singleGateDetail.getGateId());
			if(entry!=null) {
				entity=entry;
			}
			
			entity.setGateId(singleGateDetail.getGateId());
			entity.setProjectId(singleGateDetail.getProjectId());
			entity.setInitialDate(singleGateDetail.getInitialDate());
			entity.setIsFreezed(singleGateDetail.getIsFreezed());
			projectGateDetailRepository.save(entity);
			}

		}
	}

