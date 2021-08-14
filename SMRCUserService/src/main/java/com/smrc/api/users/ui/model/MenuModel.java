package com.smrc.api.users.ui.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;



public class MenuModel implements Serializable, Comparable<MenuModel>{


	private static final long serialVersionUID = 2409275886265741625L;

	
	private String name;

	private String uri;
		
	private String type;
	
	private String menuKey;
	
	private int pid;
	
	private int order;
	
	private String description;
	
	private Set<SubMenuModel> subMenu = new HashSet<SubMenuModel>();
	
	
	public MenuModel(String name, String uri, String type, String menuKey, int pid, int order,String description, Set<SubMenuModel> subMenu) {
		super();
		this.name = name;
		this.uri = uri;
		this.type = type;
		this.menuKey = menuKey;
		this.pid = pid;
		this.order = order;
		this.description = description;
		this.subMenu = subMenu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<SubMenuModel> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(Set<SubMenuModel> subMenu) {
		this.subMenu = subMenu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((menuKey == null) ? 0 : menuKey.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + order;
		result = prime * result + pid;
		result = prime * result + ((subMenu == null) ? 0 : subMenu.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		MenuModel other = (MenuModel) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (menuKey == null) {
			if (other.menuKey != null)
				return false;
		} else if (!menuKey.equals(other.menuKey))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (order != other.order)
			return false;
		if (pid != other.pid)
			return false;
		if (subMenu == null) {
			if (other.subMenu != null)
				return false;
		} else if (!subMenu.equals(other.subMenu))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}

	@Override
	public int compareTo(MenuModel MenuModel) {
		int status = 0;
		if(this.getOrder() == MenuModel.getOrder()) {
			status = 0;
		} else if(this.getOrder() > MenuModel.getOrder()) {
			status = 1;
		} else {
			status = -1;
		}
		return status;
	}


	
	
}
