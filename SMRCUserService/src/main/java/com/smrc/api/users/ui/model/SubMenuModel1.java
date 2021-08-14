package com.smrc.api.users.ui.model;

import java.io.Serializable;

public class SubMenuModel1 implements Serializable {

	private static final long serialVersionUID = -8427317171306631494L;

	
	private String SubMenuName;

	private String uri;
		
	private String SubMenuType;
	
	private String orderId;
	
	

	public SubMenuModel1(String subMenuName, String uri, String subMenuType, String orderId) {
		super();
		SubMenuName = subMenuName;
		this.uri = uri;
		SubMenuType = subMenuType;
		this.orderId = orderId;
	}

	public String getSubMenuName() {
		return SubMenuName;
	}

	public void setSubMenuName(String subMenuName) {
		SubMenuName = subMenuName;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getSubMenuType() {
		return SubMenuType;
	}

	public void setSubMenuType(String subMenuType) {
		SubMenuType = subMenuType;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((SubMenuName == null) ? 0 : SubMenuName.hashCode());
		result = prime * result + ((SubMenuType == null) ? 0 : SubMenuType.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubMenuModel1 other = (SubMenuModel1) obj;
		if (SubMenuName == null) {
			if (other.SubMenuName != null)
				return false;
		} else if (!SubMenuName.equals(other.SubMenuName))
			return false;
		if (SubMenuType == null) {
			if (other.SubMenuType != null)
				return false;
		} else if (!SubMenuType.equals(other.SubMenuType))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}
	
    
		
	
}
