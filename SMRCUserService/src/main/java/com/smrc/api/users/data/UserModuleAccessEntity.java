/*
 * package com.smrc.api.users.data;
 * 
 * import java.io.Serializable;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.GeneratedValue; import javax.persistence.Id; import
 * javax.persistence.JoinColumn; import javax.persistence.ManyToOne; import
 * javax.persistence.Table;
 * 
 * @Entity
 * 
 * @Table(name="usermoduleaccess") public class UserModuleAccessEntity
 * implements Serializable {
 * 
 * 
 * private static final long serialVersionUID = 7427166961612698287L;
 * 
 * @Id
 * 
 * @GeneratedValue
 * 
 * @Column(name = "USER_MODULE_ACCESS_ID") private int userModuleAccessId;
 * 
 * 
 * @Column(name = "MODULE_ID") private int moduleId;
 * 
 * 
 * @Column(name = "USER_INFO_ID") private int userInfoId;
 * 
 * @Column(name = "IP_ADDRESS") private String ipAddress;
 * 
 * @Column(name = "C_USER_ID") private int cUserId;
 * 
 * @Column(name = "C_DATE") private String cDate;
 * 
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name="moduleId") private ModuleEntity moduleEntity;
 * 
 * 
 * public int getUserModuleAccessId() { return userModuleAccessId; }
 * 
 * public void setUserModuleAccessId(int userModuleAccessId) {
 * this.userModuleAccessId = userModuleAccessId; }
 * 
 * 
 * public int getModuleId() { return moduleId; }
 * 
 * public void setModuleId(int moduleId) { this.moduleId = moduleId; }
 * 
 * 
 * public int getUserInfoId() { return userInfoId; }
 * 
 * public void setUserInfoId(int userInfoId) { this.userInfoId = userInfoId; }
 * 
 * public String getIpAddress() { return ipAddress; }
 * 
 * public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
 * 
 * public int getcUserId() { return cUserId; }
 * 
 * public void setcUserId(int cUserId) { this.cUserId = cUserId; }
 * 
 * public String getcDate() { return cDate; }
 * 
 * public void setcDate(String cDate) { this.cDate = cDate; }
 * 
 * public ModuleEntity getModuleEntity() { return moduleEntity; }
 * 
 * public void setModuleEntity(ModuleEntity moduleEntity) { this.moduleEntity =
 * moduleEntity; }
 * 
 * 
 * }
 */