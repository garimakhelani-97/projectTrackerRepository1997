package com.smrc.gateone.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smrc.gateone.model.BudgetPlanExchangeRate;
import com.smrc.gateone.model.Currency;
import com.smrc.gateone.model.PreviewsScreen;
import com.smrc.gateone.model.Project;
import com.smrc.gateone.repository.CurrencyRepository;
import com.smrc.gateone.repository.MonthlyExchangeRateRepository;
import com.smrc.gateone.repository.PreviewsScreenRepository;
import com.smrc.gateone.repository.ProjectRepository;
import com.smrc.gateone.service.OnePagerService;


@Service
public class OnePagerServiceImpl implements OnePagerService {


	@Autowired
	private CurrencyRepository currencyRepository;
	@Autowired
	private MonthlyExchangeRateRepository monthlyExchangeRateRepository;
	@Autowired
	private PreviewsScreenRepository previewsScreenRepository;
	@Autowired
	private ProjectRepository projectRepository;
	//get currency list
	@Override
	public List<Currency> getCurrencyList() {
		List<Currency> currencyList = currencyRepository.findByRecordStatusId(10);
		return currencyList;
	}

	// get monthlyExchangeRate by reportingPeriod sourceCurrency and targetCurrency
	@Override
	public BudgetPlanExchangeRate getMonthlyExchangeRate(String reportingYear, Integer sourceCurrencyId,
			Integer targetCurrencyId) {
		return monthlyExchangeRateRepository.findByReportingYearAndSourceCurrencyIdAndTargetCurrencyId(
				reportingYear, sourceCurrencyId, targetCurrencyId);

	}
	
	//getPreviewScreen
	@Override
	public PreviewsScreen getPreviewScreen(Integer projectId) {
		PreviewsScreen previewsScreen = previewsScreenRepository.findByProjectId(projectId);
		return previewsScreen;
	}
	
	//create preview screen
	@Override
	public void createPreviewScreen(Integer projectId) {
		PreviewsScreen previewsScreen = new PreviewsScreen();
		previewsScreen=previewsScreenRepository.findByProjectId(projectId);
		if(previewsScreen==null)
		previewsScreen = new PreviewsScreen();
		previewsScreen.setProjectId(projectId);
		previewsScreen.setIsFreezed(1);
		Optional<Project> project =projectRepository.findById(projectId);
		if(project!=null) {//update project update dat column for submitted date
		Project existingProject	=project.get();
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");
		formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String updatedDate = formatter.format(new Date());
		existingProject.setUpdatedDate(updatedDate);
		projectRepository.save(existingProject);
		}
		previewsScreenRepository.save(previewsScreen);
	}

}
