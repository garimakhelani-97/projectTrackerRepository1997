package com.smrc.gpor.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import com.smrc.gateone.model.BudgetPlanExchangeRate;

import lombok.Data;

@Entity
@Table(name ="budget_plan_exchangerate_yearly")
@Data
public class BudgetPlanExchangeRate implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String reportingYear;
	private Integer sourceCurrencyId;
	private Integer targetCurrencyId;
	private String budgetRate;
	private Integer planRate;

}
