package model;

import model.Product;
import model.Cart;

import java.util.List;

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
 * The Cart class has details about a product that has
 * been added into the cart.
 *</p>
 *
 * @author   karthik   created on 19 June 2019
 *
 */

@Entity
@Table(name = "CART")
public class Cart {

  private int cartId;
  private int quantity;
  private LocalDateTime created;
  private LocalDateTime modified;
  private Product product;
  private User user;

/**
 * Getter and setter for various fields of cart
 *
 */

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  public int getCartId() {
    return this.cartId;
  }
  public void setCartId(int cartId) {
    this.cartId = cartId;
  }

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "USER_ID")
  public User getUser() {
    return this.user;
  }
  public void setUser(User user) {
    this.user = user;
  }

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "PRODUCT_ID")
  public Product getProduct() {
    return this.product;
  }
  public void setProduct(Product product) {
    this.product = product;
  }


  @Column(name = "QUANTITY")  
  public int getQuantity() {
    return this.quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
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



 
/**
 * It converts the cart fields into string format and returns it
 *
 * @return      String       The string representation of cart information
 *
 */ 
  public String toString() {
    return cartId  +  quantity + " "+  created +  modified + product ;
  } 
}
