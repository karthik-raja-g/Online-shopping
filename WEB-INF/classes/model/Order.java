package model;

import model.OrderDetails;
import model.User;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;
/**
 *<p>
 * It creates an order with list of OrderDetailss and their details
 * It has a unique order Id 
 *</p>
 *
 * @author   karthik    created on 22 June 2019
 *
 */

@Entity
@Table(name = "USER_ORDER",uniqueConstraints = {
@UniqueConstraint(columnNames = "ID")})
public class Order {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID",unique = true, nullable = false)
  private int orderId;

  @Column(name = "QUANTITY")
  private int quantity;

  @Column(name = "COST")
  private int cost;

  @Column(name = "STATUS")
  private Boolean status;

  @Column(name = "CREATED")
  private LocalDateTime createdDate;

  @Column(name = "MODIFIED")
  private LocalDateTime modifiedDate;

  @ManyToOne(cascade = CascadeType.ALL)
  private User user;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "ORDER_ID")
  private Set<OrderDetails> orderDetail;

/**
 * getter and setter methods of various fields
 */ 


  public int getOrderId() {
    return this.orderId;
  }
  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }


  public Set<OrderDetails> getOrderDetails() {
    return this.orderDetail;
  }
  public void setOrderDetails(Set<OrderDetails> orderDetail) {
    this.orderDetail = orderDetail;
  }


  public int getCost() {
    return this.cost;
  }
  public void setCost(int cost) {
    this.cost = cost;
  }


  public int getQuantity() {
    return this.quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }


  public Boolean getStatus() {
    return this.status;
  }
  public void setStatus(Boolean status) {
    this.status = status;
  }


  public LocalDateTime getCreatedDate() {
    return this.createdDate;
  }
  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }


  public LocalDateTime getModifiedDate() {
    return this.modifiedDate;
  }
  public void setModifiedDate(LocalDateTime modifiedDate) {
    this.modifiedDate = modifiedDate;
  }


  public User getUser() {
    return this.user;
  }
  public void setUser(User user) {
    this.user = user;
  }



/**
 * The string representation of order Details
 *
 * @return   String    The string containing order details
 *
 */
  public String toString() {
    return orderId + quantity + cost + " "+createdDate +  modifiedDate + orderDetail;
  }
}
