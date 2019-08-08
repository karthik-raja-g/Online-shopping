package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 *<p>
 * A role has a name, Id and description of the role. Each role has 
 * different accessibility to the online store. A user will be assigned
 * a role only after a role has been createdDate 
 *</p>
 *
 * @author  karthik   createdDate on 22 June 2019
 *
 */

@Entity
@Table(name = "ROLES")
public class Role {

  private String roleName;
  private int roleId;
  private String description;
  private boolean isActive;
  private LocalDateTime createdDate;
  private LocalDateTime modifiedDate;


/**
 * getter and setter methods of the role fields
 * 
 */
  
  @Column(name = "NAME")
  public String getRoleName() {
    return this.roleName;
  }
  public void setRoleName(String roleName) {
     this.roleName = roleName;
  }

  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ROLE_ID")
  public int getRoleId() {
    return this.roleId;
  }
  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }

  @Column(name = "DESCRIPTION")
  public String getDescription() {
    return this.description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "CREATED")
  public LocalDateTime getCreatedDate() {
    return this.createdDate;
  }
  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  @Column(name = "MODIFIED")
  public LocalDateTime getModifiedDate() {
    return this.modifiedDate;
  }
  public void setModifiedDate(LocalDateTime modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  @Column(name = "IS_ACTIVE")
  public Boolean getIsActive() {
    return this.isActive;
  }
  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }


/**
 * It returns the role information in string format
 *
 * @return    String    The string containing role details
 *
 */
  public String toString() {
    return roleName + roleId + description + createdDate +  modifiedDate + isActive ;
  } 

}
