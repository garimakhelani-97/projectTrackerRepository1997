package com.smrc.gpor.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.gpor.model.Gate;
import com.smrc.gpor.model.PhasesResponseModel;
import com.smrc.gpor.model.PreviewsScreen;
import com.smrc.gpor.model.ProjectApprover;
import com.smrc.gpor.model.ProjectGateDetail;
import com.smrc.gpor.repository.ProjectApproverRepository;
import com.smrc.gpor.response.ObjectResponse;
import com.smrc.gpor.service.GateDataServiceImpl;
import com.smrc.gpor.service.PhasesDataServiceImpl;
import com.smrc.gpor.serviceImpl.OnePagerServiceImpl;

@RestController
public class PhasesController {

	@Autowired
	private PhasesDataServiceImpl phasesDataServiceImpl;
	@Autowired
	private GateDataServiceImpl gateDataServiceImpl;
	@Autowired
	private OnePagerServiceImpl onePagerServiceImpl;
	@Autowired
	private ProjectApproverRepository projectApproverRepository;

	@PostMapping("/addPhasesData/save")
	public ResponseEntity<PhasesResponseModel<Object>> savePhasesdata(@RequestBody Map parameter)
			throws ParseException {
		phasesDataServiceImpl.savePhasesdata(parameter);
		return null;

	}

	@GetMapping("/getGateHeaders/get")
	public List<Gate> getAllGateHeaders() {
		List<Gate> gateData = gateDataServiceImpl.getAllGateData();
		return gateData;

	}

	@GetMapping("/getGateDetails/get/{projectid}/{rmonth}/{month}/{year}/{pmonth}")
	public ResponseEntity<ObjectResponse<Object>> getPipActionData(@PathVariable Integer projectid, @PathVariable String rmonth,@PathVariable String month,
			@PathVariable String year,@PathVariable String pmonth) {

		/*
		 * Integer projectid = Integer.parseInt(params.get("projectid").toString());
		 * String month = params.get("month").toString();
		 */
		final ObjectResponse<Object> endPointResponse = new ObjectResponse<>();
		Map resmap = new HashMap();
		List<ProjectGateDetail> gatesData= phasesDataServiceImpl.getGateData(projectid, rmonth);
		resmap.put("gateDetails",gatesData);
		resmap.put("previousMonthData","false");

		if(gatesData.size()==0) {
			List<ProjectApprover> previousMonthApproveData= projectApproverRepository.lastMonthApprovedStatus(projectid, pmonth.split(" ")[0].substring(0,3), pmonth.split(" ")[1]);
			if(previousMonthApproveData.size()>0) {
			resmap.put("gateDetails", phasesDataServiceImpl.getGateData(projectid, pmonth));
			resmap.put("previousMonthData","true");
			}
		}
		PreviewsScreen FreezedStatus=onePagerServiceImpl.getPreviewScreen(projectid,month+"-"+year.substring(2,4));
		resmap.put("gateIsFreezed",FreezedStatus==null?0: FreezedStatus.getIsFreezed());
		endPointResponse.setResponse(resmap);
		endPointResponse.setStatus("success");
		return ResponseEntity.status(HttpStatus.OK).body(endPointResponse);

	}


}
