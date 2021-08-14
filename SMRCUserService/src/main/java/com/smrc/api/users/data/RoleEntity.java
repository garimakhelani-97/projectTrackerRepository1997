package com.smrc.api.users.data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = -4690804778713733469L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column
	String shortname;
	
	@Column
	String name;
	
	@Column
	int statusid;
	
	@Column
	String ipaddress;
	
	@Column
	int createdby;
	
	@Column
	Timestamp createdon;
	
	@Column
	int updatedby;
	
	@Column
	Timestamp updatedon;
	
	@Column
	String privilegeId;
	
	
	@ManyToMany(mappedBy="userRoles")
	private Set<UserInfoEntity> users;
	
		
	@ManyToMany
	@JoinTable(name ="rolemenuassociation", 
	joinColumns = {@JoinColumn(name="roleId", referencedColumnName = "id")},
	inverseJoinColumns = {@JoinColumn(name="menuId",referencedColumnName = "id")})
	Set<MenuEntity> userMenus;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatusid() {
		return statusid;
	}

	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public int getCreatedby() {
		return createdby;
	}

	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}

	public Timestamp getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
	}

	public int getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}

	public Timestamp getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Timestamp updatedon) {
		this.updatedon = updatedon;
	}

	public String getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
	}

	public Set<UserInfoEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserInfoEntity> users) {
		this.users = users;
	}

	public Set<MenuEntity> getUserMenus() {
		return userMenus;
	}

	public void setUserMenus(Set<MenuEntity> userMenus) {
		this.userMenus = userMenus;
	}

	
	
}
