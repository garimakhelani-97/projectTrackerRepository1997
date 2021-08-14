package com.smrc.gateone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smrc.gateone.dto.ReportAccountsMappingMapper;
import com.smrc.gateone.model.ReportAccountsMapping;

//import com.smrc.gpor.model.ReportAccountsMapping;
//import com.smrc.gpor.model.ReportAccountsMappingMapper;

@Repository
public interface ReportAccountsMappingRepository extends JpaRepository<ReportAccountsMapping,Integer> {

	@Query("select new com.smrc.gateone.dto.ReportAccountsMappingMapper(ram.misAccountCode,ram.shortHeadName,ram.mappingVariable,ram.units) "
			+ "from ReportAccountsMapping ram where ram.accountType='GPOR_INPUT' ORDER BY orderId ASC")
	List<ReportAccountsMappingMapper> findAllCustomReportAccountMapping();

}
