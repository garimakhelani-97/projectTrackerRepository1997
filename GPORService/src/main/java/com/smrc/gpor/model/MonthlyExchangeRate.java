package com.smrc.gpor.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name ="monthly_exchange_rate")
@Data
public class MonthlyExchangeRate implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String reportingPeriod;
	private Integer sourceCurrerncyId;
	private Integer targetCurrenyId;
	private Integer averageRate;
	private Integer closingRate;

}
