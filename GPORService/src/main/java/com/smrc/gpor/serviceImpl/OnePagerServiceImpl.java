package com.smrc.gpor.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.smrc.gpor.dto.ProjectWithMetricsDTO;
import com.smrc.gpor.model.BudgetPlanExchangeRate;
import com.smrc.gpor.model.Currency;
import com.smrc.gpor.model.Metrics;
import com.smrc.gpor.model.MonthlyExchangeRate;
import com.smrc.gpor.model.PreviewsScreen;
import com.smrc.gpor.model.ProjectMetricsDetail;
import com.smrc.gpor.model.ProjectSubmissionStatus;
import com.smrc.gpor.model.UserRoleAssociation;
import com.smrc.gpor.repository.BudgetPlanExchangeRateRepository;
import com.smrc.gpor.repository.CurrencyRepository;
import com.smrc.gpor.repository.MetricsRepository;
import com.smrc.gpor.repository.MonthlyExchangeRateRepository;
import com.smrc.gpor.repository.PreviewsScreenRepository;
import com.smrc.gpor.repository.ProjectMetricsDetailRepository;
import com.smrc.gpor.repository.ProjectProcessAssociationRepository;
import com.smrc.gpor.repository.ProjectSubmissionStatusRepository;
import com.smrc.gpor.repository.UserRoleAssociationRepository;
import com.smrc.gpor.service.OnePagerService;
import com.smrc.gpor.model.ProjectApprover;
import com.smrc.gpor.repository.ProjectApproverRepository;

@Service
public class OnePagerServiceImpl implements OnePagerService {

	@Autowired
	MetricsRepository metricsRepository;
	@Autowired
	ProjectMetricsDetailRepository projectMetricsDetailRepository;
	@Autowired
	CurrencyRepository currencyRepository;
	@Autowired
	MonthlyExchangeRateRepository monthlyExchangeRateRepository;
	@Autowired
	PreviewsScreenRepository previewsScreenRepository;
	@Autowired
	BudgetPlanExchangeRateRepository budgetPlanExchangeRateRepository;	
    @Autowired 
    ProjectApproverRepository projectApproverRepository;
    
    @Autowired
    ProjectSubmissionStatusRepository projectSubmissionStatusRepository;
    
    @Autowired
    ProjectProcessAssociationRepository projectProcessAssociationRepository;
    
    @Autowired
    UserRoleAssociationRepository userRoleAssociationRepository;
    
    private static Integer ACTIVE = 10;
    private static Integer APPROVED = 15;
    private static Integer RETURN = 16;
    private static Integer PENDING = 14;
	@Override
	public Map getMetricsName(String projectId, String reportedMonth) {
		// Logic to get previous Month data
		Date reportedMonthDate = null;
		Map<String,Object> responseMap = new HashMap<String,Object>();
		try {
			reportedMonthDate = new SimpleDateFormat("MMM yyyy").parse(reportedMonth);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		Calendar calender = Calendar.getInstance();
		calender.setTime(reportedMonthDate);
		calender.add(Calendar.MONTH, -1);
		Date resultedMonth = calender.getTime();
		SimpleDateFormat sopDateFormatter = new SimpleDateFormat("MMMM yyyy");
		String previousReportingMonth = sopDateFormatter.format(resultedMonth);
		Integer id = Integer.parseInt(projectId);
		// Check Record present or not
		List<Map>mappedDefaultList = new ArrayList<>();
		List<Metrics> defaultList = metricsRepository.findAll();
		List<ProjectMetricsDetail> resultList = projectMetricsDetailRepository.findByprojectId(id);
		if (resultList.size() == 0) {
			//metricsRepository.getMetrics();
			
			for(Metrics singleMetric: defaultList) {
				Map<String, Object> singleMap 
	            = new HashMap<String, Object>();
				singleMap.put("id", singleMetric.getId());
				singleMap.put("name", singleMetric.getName());
				singleMap.put("description", singleMetric.getDescription());
				singleMap.put("id", singleMetric.getId());
				singleMap.put("commnet",null);
				singleMap.put("statusId", null);
				mappedDefaultList.add(singleMap);
				//Map singleMap = new HashMap<String,object>;
			}
			responseMap.put("metricDetails",mappedDefaultList);
			responseMap.put("isPreviousMonthData",Boolean.FALSE);
			return responseMap;
		}
		//List<Map> renposeForCurrentMonth = metricsRepository.getMetricsNameByCurrentReportingMonth(id, reportedMonth);
		//Get data by current reporting month and project id
		List<ProjectMetricsDetail> dataList = projectMetricsDetailRepository.findAllByProjectIdAndReportedMonth(id, reportedMonth);
		if(dataList.size() > 0) {
		HashMap<Integer, ProjectMetricsDetail> custom = prepareMatricsDetailMap(dataList);
		ProjectMetricsDetail projectMetricsDetail = null;
		for (Metrics singleMetric : defaultList) {
			Map<String, Object> metricObject = new HashMap<String, Object>();
			metricObject.put("id", singleMetric.getId());
			metricObject.put("name", singleMetric.getName());
			metricObject.put("description", singleMetric.getDescription());
			metricObject.put("id", singleMetric.getId());
			projectMetricsDetail = custom.get(singleMetric.getId());
			// Handle null pointer
			if (projectMetricsDetail != null) {
				metricObject.put("comment", projectMetricsDetail.getComment());
				metricObject.put("statusId", projectMetricsDetail.getStatusId());
				metricObject.put("projectMetricsTableId", projectMetricsDetail.getId());
				
			} else {
				metricObject.put("comment", null);
				metricObject.put("statusId",null);
				metricObject.put("projectMetricsTableId", null);
				
			}
			mappedDefaultList.add(metricObject);
		}
		responseMap.put("metricDetails",mappedDefaultList);
		responseMap.put("isPreviousMonthData",Boolean.FALSE);
		return responseMap;
		} else {
			//get previous month data.
			List<ProjectApprover> previousMonthApproveData= projectApproverRepository.lastMonthApprovedStatus(id, previousReportingMonth.split(" ")[0].substring(0,3), previousReportingMonth.split(" ")[1]);
			List<ProjectMetricsDetail> previousMonthDataList=new ArrayList<ProjectMetricsDetail>();
			if(previousMonthApproveData.size()>0) {
				previousMonthDataList= projectMetricsDetailRepository.findAllByProjectIdAndPreviousMonth(id,previousReportingMonth);
			}
			   //List<Map> rensponseList = metricsRepository.getMetricsNameByPreviousReportingMonth(id,previousReportingMonth);
			HashMap<Integer, ProjectMetricsDetail> custom = prepareMatricsDetailMap(previousMonthDataList);
			ProjectMetricsDetail projectMetricsDetail = null;
			Map<String, Object> metricObject = null;
			
			for (Metrics singleMetric : defaultList) {
				metricObject = new HashMap<String, Object>();
				metricObject.put("id", singleMetric.getId());
				metricObject.put("name", singleMetric.getName());
				metricObject.put("description", singleMetric.getDescription());
				metricObject.put("id", singleMetric.getId());
				projectMetricsDetail = custom.get(singleMetric.getId());
				// Handle null pointer
				if (projectMetricsDetail != null) {
					metricObject.put("comment", projectMetricsDetail.getComment());
					metricObject.put("statusId", projectMetricsDetail.getStatusId());
					metricObject.put("projectMetricsTableId", null);
					
				} else {
					metricObject.put("comment", null);
					metricObject.put("statusId",null);
					metricObject.put("projectMetricsTableId", null);
					
				}
				mappedDefaultList.add(metricObject);     
		     }
			responseMap.put("metricDetails",mappedDefaultList);
			responseMap.put("isPreviousMonthData",Boolean.TRUE);
			return responseMap;
		}

	}
	private HashMap<Integer, ProjectMetricsDetail> prepareMatricsDetailMap(List<ProjectMetricsDetail> resultList) {
		HashMap<Integer, ProjectMetricsDetail> metricDataMap = new HashMap<Integer, ProjectMetricsDetail>();
		for (ProjectMetricsDetail projectMetricsDetail : resultList) {
			if (projectMetricsDetail != null) {
				metricDataMap.put(projectMetricsDetail.getMetricsId(), projectMetricsDetail);
			}
		}
		return metricDataMap;
	}

	// To save metrics comments.
	public String saveMetrics(List<ProjectWithMetricsDTO> myList) {
		// TODO Auto-generated method stub
		ProjectMetricsDetail projectMetricObject = null;
		for (ProjectWithMetricsDTO projectMetricsData : myList) {
			// Check data is already present in Table or Not.
			projectMetricObject = new ProjectMetricsDetail();
			if (projectMetricsData.getProjectMetricsTableId() != null) {
				Optional<ProjectMetricsDetail> projectMetricExistingObject = projectMetricsDetailRepository
						.findById(projectMetricsData.getProjectMetricsTableId());
				if (projectMetricExistingObject.isPresent()) {
					projectMetricObject = projectMetricExistingObject.get();
				}
			}
			else {
				List<ProjectMetricsDetail> data =	projectMetricsDetailRepository.getProjectMetricsDetailExistingdata
				(projectMetricsData.getId(),projectMetricsData.getReportedMonth(),projectMetricsData.getProjectId());
				if(data.size()>0)
					projectMetricObject=data.get(0);
			}
			projectMetricObject.setProjectId(projectMetricsData.getProjectId());
			projectMetricObject.setComment(projectMetricsData.getComment());
			projectMetricObject.setReportedMonth(projectMetricsData.getReportedMonth());
			projectMetricObject.setStatusId(projectMetricsData.getStatusId());
			projectMetricObject.setMetricsId(projectMetricsData.getId());
			projectMetricsDetailRepository.save(projectMetricObject);
		}
		return "test";

	}
	//get currency list
	@Override
	public List<Currency> getCurrencyList() {
		List<Currency> currencyList = currencyRepository.findByRecordStatusId(10);
		return currencyList;
	}

	// get monthlyExchangeRate by reportingPeriod sourceCurrency and targetCurrency
	@Override
	public MonthlyExchangeRate getMonthlyExchangeRate(String reportingPeriod, Integer sourceCurrencyId,
			Integer targetCurrencyId) {
		return monthlyExchangeRateRepository.findByReportingPeriodAndSourceCurrerncyIdAndTargetCurrenyId(
				reportingPeriod, sourceCurrencyId, targetCurrencyId);

	}
	
	
//	@Override
	public BudgetPlanExchangeRate getYearlyExchangeRate(String reportingPeriod, Integer sourceCurrencyId) {
		return budgetPlanExchangeRateRepository.findByReportingPeriodAndSourceCurrerncyId(
				reportingPeriod, sourceCurrencyId);

	}
	//getPreviewScreen
	@Override
	public PreviewsScreen getPreviewScreen(Integer projectId, String reportedMonth) {
		PreviewsScreen previewsScreen = previewsScreenRepository.findByProjectIdAndReportedMonth(projectId,
				reportedMonth);
		return previewsScreen;
	}
	
	//create preview screen
	@Override
	public void createPreviewScreen(Integer projectId, String reportedMonth) {
		PreviewsScreen previewsScreen = new PreviewsScreen();
		previewsScreen=previewsScreenRepository.findByProjectIdAndReportedMonth(projectId,reportedMonth);
		if(previewsScreen==null)
		previewsScreen = new PreviewsScreen();
		previewsScreen.setProjectId(projectId);
		previewsScreen.setReportedMonth(reportedMonth);
		previewsScreen.setIsFreezed(1);
		previewsScreenRepository.save(previewsScreen);
	}

	/* Get project approvers list */
	@Override
	public Object getProjectApproversList(Integer projectId, String reportingPeriod, Integer currentUserId) {
		String month = reportingPeriod.substring(0, 3);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String year = reportingPeriod.substring(reportingPeriod.indexOf(" ") + 1);
		/* get project submission data */
		ProjectSubmissionStatus projectSubmissionStatus = projectSubmissionStatusRepository
				.findByProjectIdAndMonthAndYear(projectId, month, year);

		/* submission status 15 = submitted, submission status 16 = returned */
		Integer projectControllerPK = projectProcessAssociationRepository.findAllIdByProjectId(projectId);
		Integer projectManagerPK = projectProcessAssociationRepository.findProjectManagerByProjectId(projectId);
		List<Integer> submitterPKId = new ArrayList<Integer>();
		submitterPKId.add(projectControllerPK);
		submitterPKId.add(projectManagerPK);

		// get user details by ids.
		List<Integer> submitterDetails = userRoleAssociationRepository.findUserIdByIdIn(submitterPKId);
		boolean isCurrentUserIdPresent = submitterDetails.contains(currentUserId.intValue());

		/* set submission flag */
		if (Objects.isNull(projectSubmissionStatus)) {

			/* get project controller/project manager id */

			if (isCurrentUserIdPresent) {
				responseMap.put("submissionFlag", Boolean.FALSE);
			} else {
				responseMap.put("submissionFlag", Boolean.TRUE);
			}
			responseMap.put("currentApproverFlag", "No");
			responseMap.put("finalApproverFlag", "No");
		} else {

			if (projectSubmissionStatus.getSubmissionFlag() == RETURN && isCurrentUserIdPresent) {
				responseMap.put("submissionFlag", Boolean.FALSE);
				responseMap.put("currentApproverFlag", "No");
				responseMap.put("finalApproverFlag", "No");
			} else {
				if (isCurrentUserIdPresent && projectSubmissionStatus.getSubmissionFlag() == APPROVED) {
					responseMap.put("submissionFlag", Boolean.TRUE);
					/* set current approver flag and role */
					List<ProjectApprover> projectApproverList = projectApproverRepository
							.findAllByProjectIdAndMonthAndYearAndStatusIdAndApproveStatus(projectId, month, year, ACTIVE,
									PENDING);
					if (projectApproverList.size() > 0) {
						ProjectApprover projectApprover = projectApproverList.get(0);
						if (projectApprover.getApprovedBy() == currentUserId.intValue()) {
							responseMap.put("currentApproverFlag", "Yes");
							responseMap.put("finalApproverFlag", "No");
							responseMap.put("currentApproverRoleId", projectApprover.getRoleId());
						} else {
							responseMap.put("currentApproverFlag", "No");
							responseMap.put("finalApproverFlag", "No");
						}
					} else {
						responseMap.put("currentApproverFlag", "No");
						responseMap.put("finalApproverFlag", "No");
					}
				}else {
					List<ProjectApprover> projectApproverList = projectApproverRepository
							.findAllByProjectIdAndMonthAndYearAndStatusIdAndApproveStatus(projectId, month, year, ACTIVE,
									PENDING);
					responseMap.put("submissionFlag", Boolean.TRUE);
					if (projectApproverList.size() > 0) {
						
						ProjectApprover projectApprover = projectApproverList.get(0);
						if (projectApprover.getApprovedBy() == currentUserId.intValue()) {
							
							responseMap.put("currentApproverFlag", "Yes");
							responseMap.put("finalApproverFlag", "No");
							responseMap.put("currentApproverRoleId", projectApprover.getRoleId());
						} else {
							responseMap.put("currentApproverFlag", "No");
							responseMap.put("finalApproverFlag", "No");
						}
					} else {
						responseMap.put("currentApproverFlag", "No");
						responseMap.put("finalApproverFlag", "No");
					}
						
						
					
				}
			}
			
		}
		return responseMap;
	}
	@Override
	public Map getMetricsDetails(String projecctId, String reportedMonth) {
		Integer id = Integer.parseInt(projecctId);
		List<Map> mappedDefaultList = new ArrayList<>();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		List<ProjectMetricsDetail> metricsDataList = projectMetricsDetailRepository
				.findAllByProjectIdAndReportedMonth(id, reportedMonth);
		if (metricsDataList.size() > 0) {
			List<Metrics> defaultList = metricsRepository.findAll();
			HashMap<Integer, ProjectMetricsDetail> custom = prepareMatricsDetailMap(metricsDataList);
			ProjectMetricsDetail projectMetricsDetail = null;
			for (Metrics singleMetric : defaultList) {
				Map<String, Object> metricObject = new HashMap<String, Object>();
				metricObject.put("id", singleMetric.getId());
				metricObject.put("name", singleMetric.getName());
				metricObject.put("description", singleMetric.getDescription());
				metricObject.put("id", singleMetric.getId());
				projectMetricsDetail = custom.get(singleMetric.getId());
				// Handle null pointer
				if (projectMetricsDetail != null) {
					metricObject.put("comment", projectMetricsDetail.getComment());
					metricObject.put("statusId", projectMetricsDetail.getStatusId());
					metricObject.put("projectMetricsTableId", projectMetricsDetail.getId());

				} else {
					metricObject.put("comment", null);
					metricObject.put("statusId", null);
					metricObject.put("projectMetricsTableId", null);

				}
				mappedDefaultList.add(metricObject);
			}
			responseMap.put("metricDetails", mappedDefaultList);
		} 
		return responseMap;
	}

}
