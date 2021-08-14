package com.smrc.gateone.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PipSaveJson {
	private String projectId;// "28",
	private List<PipAction> pipActions;// this.pipActions,
	private List<PipFinancials> accountMonthDetail;// this.dataSource2,
	private String createdBy;//": "1"
	
}
