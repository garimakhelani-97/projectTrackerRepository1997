package com.smrc.api.users.data;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.smrc.api.users.converter.JSONObjectConverter;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -4690804778713733469L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false, length = 50)
	private String firstName;

	@Column(nullable = false, length = 50)
	private String LastName;

	@Column(nullable = false, length = 150, unique = true)
	private String email;

	@Column(nullable = false, unique = true)
	private String userId;

	@Column(nullable = false)
	private String encryptedPassword;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(referencedColumnName = "id") }, inverseJoinColumns = {
			@JoinColumn(referencedColumnName = "id") })
	private Set<RoleEntity> roles;

	/*
	 * @NonNull
	 * 
	 * @Column(columnDefinition = "json")
	 * 
	 * @Convert(converter= JSONObjectConverter.class) private JSONObject jsonData;
	 */

	@Convert(converter = JSONObjectConverter.class)
	private Map<String, String> jsonData;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public Map<String, String> getJsonData() {
		return jsonData;
	}

	public void setJsonData(Map<String, String> jsonData) {
		this.jsonData = jsonData;
	}

}
