package com.smrc.gpor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountMonthDetailMapper {
    private Integer accountMonthDtlId;
	private String monthDetailValue;
	private String monthDetailComment;
	private Integer mappingVaraible;

}
