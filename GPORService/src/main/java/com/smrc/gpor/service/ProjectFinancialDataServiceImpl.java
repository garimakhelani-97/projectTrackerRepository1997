package com.smrc.gpor.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smrc.gpor.model.AccountGateDetails;
import com.smrc.gpor.model.AccountGateDetailsMapper;
import com.smrc.gpor.model.AccountMonthDetail;
import com.smrc.gpor.model.AccountMonthDetailMapper;
import com.smrc.gpor.model.IQFVersion;
import com.smrc.gpor.model.Project;
import com.smrc.gpor.model.ProjectFinancialResDTO;
import com.smrc.gpor.model.ReportAccountsMappingMapper;
import com.smrc.gpor.repository.AccountGateDetailsRepository;
import com.smrc.gpor.repository.AccountMonthDetailRepository;
import com.smrc.gpor.repository.IQFVersionRepository;
import com.smrc.gpor.repository.ProjectRepository;
import com.smrc.gpor.repository.ReportAccountsMappingRepository;

@Service
public class ProjectFinancialDataServiceImpl implements ProjectFinancialDataService {

	@Autowired
	ReportAccountsMappingRepository reportAccountsMappingRepository;

	@Autowired
	AccountMonthDetailRepository accountMonthDetailRepository;

	@Autowired
	AccountGateDetailsRepository accountGateDetailsRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	IQFVersionRepository iQFVersionRepository;
	
	private static String PER_VEHICLE = "/veh";
	private static String CURRENCY_UNIT_THOUSAND = "Thousand";

	@Override
	public List<ProjectFinancialResDTO> getProjectFinancialData(String projectId, String reportedMonth) {

		List<ProjectFinancialResDTO> projectFinancialResDTOList = new ArrayList<ProjectFinancialResDTO>();

		List<ReportAccountsMappingMapper> reportAccountsMappingMapperList = reportAccountsMappingRepository
				.findAllCustomReportAccountMapping();
		
		SimpleDateFormat format = new SimpleDateFormat("MMMM yyyy");
		Date reportedMonth1 = null;
		try {
			reportedMonth1 = format.parse(reportedMonth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LocalDate localDate = reportedMonth1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		LocalDate date = localDate.minusMonths(1);
		DateTimeFormatter d1 = DateTimeFormatter.ofPattern("MMM YYYY");
		String previousMonthDate = date.format(d1);
		String[] previousDateList = previousMonthDate.split(" ");
		
		String currentMonthDate = localDate.format(d1);
		String[] currentMonthDateList = currentMonthDate.split(" ");

		Integer id = Integer.parseInt(projectId);
		
		//accountGateDetailList
		List<AccountGateDetailsMapper> accountGateDetailsList = accountGateDetailsRepository
				.findByProjectIdAndMonthAndYear(id, currentMonthDateList[0], currentMonthDateList[1]);
		Map<Integer, AccountGateDetailsMapper> accountGateDetailsMapObj = accountGateDetailsList.stream().collect(
				Collectors.toMap(AccountGateDetailsMapper::getMappingVaraible, accountGateDtl -> accountGateDtl));
        
		//accountMonthDetailList for currentMonth
		List<AccountMonthDetailMapper> accountMonthDetailList = accountMonthDetailRepository
				.findByProjectIdAndMonthAndYear(id, currentMonthDateList[0], currentMonthDateList[1]);

		Map<Integer, AccountMonthDetailMapper> accountMonthDetailMapperMapObj = accountMonthDetailList.stream()
				.collect(Collectors.toMap(AccountMonthDetailMapper::getMappingVaraible,
						accountMonthDetailMapper -> accountMonthDetailMapper));
		
		//accountMonthDetailList for previous month
		List<AccountMonthDetailMapper> accountMonthDetailListPrevMonth = accountMonthDetailRepository
				.findByProjectIdAndMonthAndYear(id, previousDateList[0], currentMonthDateList[1]);
		
		Map<Integer, AccountMonthDetailMapper> accountMonthDetailMapperMapObj2 = accountMonthDetailListPrevMonth.stream()
				.collect(Collectors.toMap(AccountMonthDetailMapper::getMappingVaraible,
						accountMonthDetailMapper -> accountMonthDetailMapper));
		

		if (reportAccountsMappingMapperList != null && !reportAccountsMappingMapperList.isEmpty()) {
			for (int i = 0; i < reportAccountsMappingMapperList.size(); i++) {
				
				ProjectFinancialResDTO projectFinancialResDTO = new ProjectFinancialResDTO();
				//reportAccountMapping
				projectFinancialResDTO.setReportAccountsMappingMapper(reportAccountsMappingMapperList.get(i));

							
				//AccountGateDetails
				if (accountGateDetailsMapObj != null && !accountGateDetailsMapObj.isEmpty()) {
					AccountGateDetailsMapper accountGateDetailsMapper = accountGateDetailsMapObj
							.get(reportAccountsMappingMapperList.get(i).getMappingVariable());
					if (accountGateDetailsMapper != null) {
						projectFinancialResDTO.setAccountGdtlId(accountGateDetailsMapper.getAccountGateDtlId());
						projectFinancialResDTO.setCommitGate(accountGateDetailsMapper.getCommitGate());
						projectFinancialResDTO.setCommitGateValue(accountGateDetailsMapper.getGateValue());
						projectFinancialResDTO.setCommitGateComment(accountGateDetailsMapper.getComment());
						projectFinancialResDTO.setIsFreezed(accountGateDetailsMapper.getIsFreezed());
					}
				}

				//accountMonthDetail for current Month
				if (accountMonthDetailMapperMapObj != null && !accountMonthDetailMapperMapObj.isEmpty()) {
					AccountMonthDetailMapper accountMonthDetailMapper = accountMonthDetailMapperMapObj
							.get(reportAccountsMappingMapperList.get(i).getMappingVariable());
					if (accountMonthDetailMapper != null) {
						projectFinancialResDTO.setMontGdtlId(accountMonthDetailMapper.getAccountMonthDtlId());
						projectFinancialResDTO.setCurrentValue(accountMonthDetailMapper.getMonthDetailValue());
						projectFinancialResDTO.setCurrentComment(accountMonthDetailMapper.getMonthDetailComment());
					}

				}
				//accountMonthDetail for previous Month
				if (accountMonthDetailMapperMapObj2 != null && !accountMonthDetailMapperMapObj2.isEmpty()) {
					AccountMonthDetailMapper accountMonthDetailMapper = accountMonthDetailMapperMapObj2
							.get(reportAccountsMappingMapperList.get(i).getMappingVariable());
					if (accountMonthDetailMapper != null) {
						projectFinancialResDTO.setPreviousValue(accountMonthDetailMapper.getMonthDetailValue());
					}

				}
				Optional<Project> optionalProject = projectRepository.findById(id);
				if(optionalProject.isPresent()) {
					Project project = optionalProject.get();
					String currencyName = project.getCurrencyName();
					projectFinancialResDTO.setCurrency(currencyName);
				}
				if(reportAccountsMappingMapperList.get(i).getMisAccountCode().equalsIgnoreCase("8003"))
				{
					projectFinancialResDTO.getReportAccountsMappingMapper().setUnits(projectFinancialResDTO.getCurrency() + PER_VEHICLE);
				}
				
				if(reportAccountsMappingMapperList.get(i).getMisAccountCode().equalsIgnoreCase("8004") || 
						reportAccountsMappingMapperList.get(i).getMisAccountCode().equalsIgnoreCase("8005") ||
						reportAccountsMappingMapperList.get(i).getMisAccountCode().equalsIgnoreCase("8011") || 
						reportAccountsMappingMapperList.get(i).getMisAccountCode().equalsIgnoreCase("8013") ||
						reportAccountsMappingMapperList.get(i).getMisAccountCode().equalsIgnoreCase("8014") ||
						reportAccountsMappingMapperList.get(i).getMisAccountCode().equalsIgnoreCase("8015") ||
						//reportAccountsMappingMapperList.get(i).getMisAccountCode().equalsIgnoreCase("8016") ||
						reportAccountsMappingMapperList.get(i).getMisAccountCode().equalsIgnoreCase("8017") ||
						reportAccountsMappingMapperList.get(i).getMisAccountCode().equalsIgnoreCase("8018") ||
						reportAccountsMappingMapperList.get(i).getMisAccountCode().equalsIgnoreCase("8019") ||
						reportAccountsMappingMapperList.get(i).getMisAccountCode().equalsIgnoreCase("8020") ||
						reportAccountsMappingMapperList.get(i).getMisAccountCode().equalsIgnoreCase("8021")
						)
				{
					projectFinancialResDTO.getReportAccountsMappingMapper().setUnits(CURRENCY_UNIT_THOUSAND+ "("+projectFinancialResDTO.getCurrency()+ ")");
				}
				
				projectFinancialResDTOList.add(projectFinancialResDTO);
			}
		}

		return projectFinancialResDTOList;

	}

	@Override
	public void createProjectFinancialData(String projectId, String reportedMonth,
			List<ProjectFinancialResDTO> projectFinancialResDTOList) {
		
		SimpleDateFormat format = new SimpleDateFormat("MMMM yyyy");
		Date reportedMonth1 = null;
		try {
			reportedMonth1 = format.parse(reportedMonth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LocalDate localDate = reportedMonth1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		LocalDate date = localDate.minusMonths(1);
		DateTimeFormatter d1 = DateTimeFormatter.ofPattern("MMM YYYY");
		String previousMonthDate = date.format(d1);
		String[] previousDateList = previousMonthDate.split(" ");

		String currentMonthDate = localDate.format(d1);
		String[] currentMonthDateList = currentMonthDate.split(" ");

		for (int i = 0; i < projectFinancialResDTOList.size(); i++) {

			ReportAccountsMappingMapper reportAccountsMappingMapper = projectFinancialResDTOList.get(i)
					.getReportAccountsMappingMapper();
			AccountGateDetails accountGateDetails = new AccountGateDetails();

			// create accountGateDetails
			if (projectFinancialResDTOList.get(i).getAccountGdtlId() == null) {
				
				accountGateDetails.setProjectId(Integer.parseInt(projectId));

				if (reportAccountsMappingMapper != null) {
					accountGateDetails.setAccountCode(reportAccountsMappingMapper.getMisAccountCode());
					accountGateDetails.setMappingVariable(reportAccountsMappingMapper.getMappingVariable());

				}
				accountGateDetails.setMonth(currentMonthDateList[0]);
				accountGateDetails.setYear(currentMonthDateList[1]);
				accountGateDetails.setCommitGate("Gate1");
				accountGateDetails.setValue(projectFinancialResDTOList.get(i).getCommitGateValue());
				accountGateDetails.setIsFreezed(1);
				accountGateDetails.setCreatedBy(null);
				accountGateDetails.setCreatedDate(new Date().toString());
				accountGateDetails.setIpAddress(null);
				
				/*
				 * AccountGateDetails data=new AccountGateDetails();
				 * data=accountGateDetailsRepository.findDataExistance(Integer.parseInt(
				 * projectId),currentMonthDateList[0].toString(),currentMonthDateList[1].
				 * toString()); if(data==null)
				 */
				  accountGateDetailsRepository.save(accountGateDetails);
			}

			// create accountMonthDetail
			AccountMonthDetail accountMonthDetail = new AccountMonthDetail();
			/*
			 * accountMonthDetail =
			 * accountMonthDetailRepository.findByProjectIdAccountCodeMappingVar(Integer.
			 * parseInt(projectId), reportAccountsMappingMapper.getMisAccountCode()
			 * ,currentMonthDateList[0].toString(),currentMonthDateList[1].toString(),
			 * reportAccountsMappingMapper.getMappingVariable()); if(accountMonthDetail==
			 * null) accountMonthDetail = new AccountMonthDetail();
			 */
			if(projectFinancialResDTOList.get(i) != null && projectFinancialResDTOList.get(i).getMontGdtlId() != null) {
				accountMonthDetail.setId(projectFinancialResDTOList.get(i).getMontGdtlId());
			}
			accountMonthDetail.setProjectId(Integer.parseInt(projectId));

			if (reportAccountsMappingMapper != null) {
				accountMonthDetail.setAccountCode(reportAccountsMappingMapper.getMisAccountCode());
				accountMonthDetail.setMappingVariable(reportAccountsMappingMapper.getMappingVariable());

			}
			accountMonthDetail.setMonth(currentMonthDateList[0]);
			accountMonthDetail.setYear(currentMonthDateList[1]);
			accountMonthDetail.setValue(projectFinancialResDTOList.get(i).getCurrentValue());
			accountMonthDetail.setComment(projectFinancialResDTOList.get(i).getCurrentComment());
			
			if (projectFinancialResDTOList.get(i).getMontGdtlId() == null) {
				accountMonthDetail.setCreatedBy(null);
				accountMonthDetail.setCreatedDate(new Date().toString());
			}
			if (projectFinancialResDTOList.get(i).getMontGdtlId() != null) {
				accountMonthDetail.setUpdatedBy(null);
				accountMonthDetail.setUpdatedDate(new Date().toString());
			}
			accountMonthDetail.setIpAddress(null);
			accountMonthDetailRepository.save(accountMonthDetail);

		}
	}

	@Override
	public List<IQFVersion> getIQFVersionList() {
		List<IQFVersion> iQFVersionList = iQFVersionRepository.findAllByOrderByIdDesc();
		return iQFVersionList;
	}

}
