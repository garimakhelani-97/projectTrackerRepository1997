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
 * @Entity //@Table(name="SubMenu") public class SubMenuEntity implements
 * Serializable {
 * 
 * private static final long serialVersionUID = -8427317171306631494L;
 * 
 * @Id
 * 
 * @GeneratedValue private long id;
 * 
 * @Column(nullable = false , length =50) private String SubMenuName;
 * 
 * @Column(nullable = false , length =50) private String uri;
 * 
 * @Column(nullable = false , length =50) private String SubMenuType;
 * 
 * @Column(nullable = false , length =50) private String orderId;
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name="menuId") private MenuEntity menu;
 * 
 * public long getId() { return id; }
 * 
 * public void setId(long id) { this.id = id; }
 * 
 * public String getSubMenuName() { return SubMenuName; }
 * 
 * public void setSubMenuName(String subMenuName) { SubMenuName = subMenuName; }
 * 
 * public String getUri() { return uri; }
 * 
 * public void setUri(String uri) { this.uri = uri; }
 * 
 * public String getSubMenuType() { return SubMenuType; }
 * 
 * public void setSubMenuType(String subMenuType) { SubMenuType = subMenuType; }
 * 
 * public String getOrderId() { return orderId; }
 * 
 * public void setOrderId(String orderId) { this.orderId = orderId; }
 * 
 * public MenuEntity getMenu() { return menu; }
 * 
 * public void setMenu(MenuEntity menu) { this.menu = menu; }
 * 
 * 
 * 
 * }
 */