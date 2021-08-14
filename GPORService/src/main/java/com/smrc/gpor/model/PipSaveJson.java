package com.smrc.gpor.model;

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
	private List<MappingVariablesForPIPAction> accountMonthDetail;// this.dataSource2,
	private String month;//": "Mar",
	private String year;//": "2021",
	private String createdBy;//": "1"
}
