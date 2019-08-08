package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 *<p>
 * The product has number of entities. Name, product Id, SKU,
 * price, quantity, MRP, date created, date modified. All the 
 * entities except Id,SKU and created date can be modified
 *</p>
 *
 * @author   karthik  created on 21 June 2019
 *
 */

@Entity
@Table(name = "PRODUCTS")
public class Product {
  private String name;
  private int Id;
  private String SKU;
  private String description;
  private int price;
  private int maxPrice;
  private LocalDateTime created;
  private LocalDateTime modified;
  private Boolean status;
  private User user;

/**
 * Getter and setter for all the product information
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
  @Column(name = "PRODUCT_ID")
  public int getId() {
    return this.Id;
  }
  public void setId(int Id) {
    this.Id = Id;
  }

  @Column(name = "SKU")
  public String getSKU() {
    return this.SKU;
  }
  public void setSKU(String SKU) {
    this.SKU = SKU;
  }

  @Column(name = "DESCRIPTION")
  public String getDescription() {
    return this.description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
 
  @Column(name = "PRICE")
  public int getPrice() {
    return this.price;
  }
  public void setPrice(int price) {
    this.price = price;
  }

  @Column(name = "MAX_PRICE")
  public int getMaxPrice() {
    return this.maxPrice;
  }
  public void setMaxPrice(int maxPrice) {
    this.maxPrice = maxPrice;
  }

  @Column(name = "CREATED_DATE")
  public LocalDateTime getCreated() {
    return this.created;
  }
  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  @Column(name = "MODIFIED_DATE")
  public LocalDateTime getModified() {
    return this.modified;
  }
  public void setModified(LocalDateTime modified) {
    this.modified = modified;
  }

  @Column(name = "IS_AVAILABLE")
  public boolean getStatus() {
    return this.status;
  }
  public void setStatus(Boolean status) {
    this.status = status;
  }
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "CREATOR_ID")
  public User getUser() {
    return this.user;
  }
  public void setUser(User user) {
    this.user = user;
  }
  

/**
 * It converts the product fields into string format and returns it
 *
 * @return      String       The string representation of product information
 *
 */ 

  public String toString() {
    return name + Id +  SKU + description + price +  maxPrice  +  created +  modified + status ;
  } 
}
