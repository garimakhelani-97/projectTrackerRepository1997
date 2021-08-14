package com.smrc.gpor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smrc.gpor.model.Gate;
import com.smrc.gpor.repository.GateRepository;




@Service
public class GateDataServiceImpl implements GateDataService {

@Autowired
private GateRepository gateRepository;

	@Override
	public List<Gate> getAllGateData()   
	{  
	List<Gate> gate = new ArrayList<Gate>();  
	gateRepository.findAll().forEach(gates1 -> gate.add(gates1));  
	return gate;  
	} 

}
