package com.smrc.mdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smrc.mdm.model.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer>{
	
	List<Currency> findByRecordStatusId(int recordStatusId); 

}
