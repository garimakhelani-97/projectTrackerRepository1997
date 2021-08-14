package com.smrc.gpor.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountGateDetailsMapper implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer accountGateDtlId;
	private String commitGate;
	private String gateValue;
	private String comment;
	private Integer isFreezed;
	private Integer mappingVaraible;

}
