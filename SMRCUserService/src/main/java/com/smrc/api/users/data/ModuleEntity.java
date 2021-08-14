/*
 * package com.smrc.api.users.data;
 * 
 * import java.io.Serializable; import java.util.Set;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.GeneratedValue; import javax.persistence.Id; import
 * javax.persistence.OneToMany; import javax.persistence.Table;
 * 
 * @Entity //@Table(name="module") public class ModuleEntity implements
 * Serializable {
 * 
 * 
 * private static final long serialVersionUID = 7427166961612698287L;
 * 
 * 
 * @Id
 * 
 * @GeneratedValue
 * 
 * @Column(name = "MODULE_ID") private int moduleId;
 * 
 * @Column(name = "MODULE_CODE") private String moduleCode;
 * 
 * @Column(name = "MODULE_NAME") private String moduleName;
 * 
 * @Column(name = "MODULE_DESC") private String moduleDesc;
 * 
 * @Column(name = "STATUS_ID") private int statusId;
 * 
 * @Column(name = "IP_ADDRESS") private String ipAddress;
 * 
 * @Column(name = "C_USER_ID") private int cUserId;
 * 
 * @Column(name = "C_DATE") private String cDate;
 * 
 * @Column(name = "U_USER_ID") private int uUserId;
 * 
 * @Column(name = "U_DATE") private String uDate;
 * 
 * @OneToMany(mappedBy="moduleEntity") private Set<UserModuleAccessEntity>
 * userModuleAccess;
 * 
 * 
 * public int getModuleId() { return moduleId; }
 * 
 * public void setModuleId(int moduleId) { this.moduleId = moduleId; }
 * 
 * public String getModuleCode() { return moduleCode; }
 * 
 * public void setModuleCode(String moduleCode) { this.moduleCode = moduleCode;
 * }
 * 
 * public String getModuleName() { return moduleName; }
 * 
 * public void setModuleName(String moduleName) { this.moduleName = moduleName;
 * }
 * 
 * public String getModuleDesc() { return moduleDesc; }
 * 
 * public void setModuleDesc(String moduleDesc) { this.moduleDesc = moduleDesc;
 * }
 * 
 * public int getStatusId() { return statusId; }
 * 
 * public void setStatusId(int statusId) { this.statusId = statusId; }
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
 * public int getuUserId() { return uUserId; }
 * 
 * public void setuUserId(int uUserId) { this.uUserId = uUserId; }
 * 
 * public String getuDate() { return uDate; }
 * 
 * public void setuDate(String uDate) { this.uDate = uDate; }
 * 
 * public Set<UserModuleAccessEntity> getUserModuleAccess() { return
 * userModuleAccess; }
 * 
 * public void setUserModuleAccess(Set<UserModuleAccessEntity> userModuleAccess)
 * { this.userModuleAccess = userModuleAccess; }
 * 
 * 
 * }
 */