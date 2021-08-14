package com.smrc.api.users.data;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="menu")
public class MenuEntity implements Serializable {


	private static final long serialVersionUID = 2409275886265741625L;

	@Id
	@GeneratedValue
	private int Id;
	
	@Column
	private String name;

	@Column
	private String uri;
		
	@Column
	private String type;
	
	@Column
	private String menuKey;
	
	@Column
	private int pid;
	
	@Column
	private int orderId;
	
	@Column
	private String description;
   
	@ManyToMany(mappedBy="userMenus")
	private Set<RoleEntity> roles;
	
	
	
	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
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



	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public Set<RoleEntity> getRoles() {
		return roles;
	}


	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((menuKey == null) ? 0 : menuKey.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + orderId;
		result = prime * result + pid;
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
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
		MenuEntity other = (MenuEntity) obj;
		if (Id != other.Id)
			return false;
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
		if (orderId != other.orderId)
			return false;
		if (pid != other.pid)
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
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


	

	


	
	
}
