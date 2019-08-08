package model;

import model.Role;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.LocalDate;


/**
 *<p>
 * A user will have a name, user Id, user name, password, email Id
 * phone number, address, Active status, account created date
 * and the modified date. A user can be created only when a role
 * is available to be assigned.
 *</p>
 *
 * @author    karthik  created on 21 June 2019
 *
 */

@Entity
@Table(name = "USER")
public class User {
  public String name;
  private Set<Role> roles;
  private int userId;
  public String userName;
  public String password;
  private String email;
  private long phone;
  private String address;
  private Boolean isActive;
  private LocalDateTime created;
  private LocalDateTime modified;

/**
 * The getter and setter functions of all fields
 *
 */

  @Column(name = "NAME")
  public String getName() {
    return this.name;
  }
  public void setName(String name) {
     this.name = name;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  public int getUserId() {
    return this.userId;
  }
  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Column(name = "USERNAME")
  public String getUserName() {
    return this.userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Column(name = "PASSWORD")
  public String getPassword() {
    return this.password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "EMAIL_ID")  
  public String getEmail() {
    return this.email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "PHONE")
  public long getPhone() {
    return this.phone;
  }
  public void setPhone(long phone) {
    this.phone = phone;
  }

  @Column(name = "ADDRESS")
  public String getAddress() {
    return this.address;
  }
  public void setAddress(String address) {
    this.address = address;
  }

  @Column(name = "IS_ACTIVE")
  public Boolean getIsActive() {
    return this.isActive;
  }
  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  @Column(name = "CREATED")
  public LocalDateTime getCreated() {
    return this.created;
  }
  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  @Column(name = "MODIFIED")
  public LocalDateTime getModified() {
    return this.modified;
  }
  public void setModified(LocalDateTime modified) {
    this.modified = modified;
  }


  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "USER_ROLES" ,joinColumns = {@JoinColumn(name = "USER_ID") },inverseJoinColumns = {@JoinColumn(name = 
                                                                                                            "ROLE_ID")})
  public Set<Role> getRoles() {
    return this.roles;
  }
  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

/**
 * It returns a string representation of all fields
 *
 * @return      String     The string format of all fields
 *
 */

  public String toString() {
    return name + roles + userId + userName + password + email +  phone +  address  + isActive + created +  modified ;
  } 
}
