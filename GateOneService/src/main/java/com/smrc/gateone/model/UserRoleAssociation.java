package com.smrc.gateone.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role_association")
public class UserRoleAssociation implements Serializable {
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer id;
		private Integer userId;
		private String userName;
		private Integer roleId;
		private String  roleShortName;
		private String roleName;
		private Integer statusId;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public Integer getRoleId() {
			return roleId;
		}
		public void setRoleId(Integer roleId) {
			this.roleId = roleId;
		}
		public String getRoleShortName() {
			return roleShortName;
		}
		public void setRoleShortName(String roleShortName) {
			this.roleShortName = roleShortName;
		}
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
		public Integer getStatusId() {
			return statusId;
		}
		public void setStatusId(Integer statusId) {
			this.statusId = statusId;
		}
		
		


}
