package model;
import model.Order;
import model.Product;

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
import javax.persistence.UniqueConstraint;


/**
 *<p>
 * It acts like a cart. The user adds on different products
 * and those products have unique Id, which is used to 
 * identify them
 *</p>
 *
 * @author    karthik   created on 22 June 2019
 */

@Entity
@Table(name = "ORDER_DETAILS" ,uniqueConstraints = {
@UniqueConstraint(columnNames = "ID")})
public class OrderDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private int Id;

  @Column(name = "PRODUCT_NAME")
  private String prodName;

  @Column(name = "QUANTITY") 
  private int quantity;

  @Column(name = "PRICE")
  private int price;

  @ManyToOne
  private Order order;

  @ManyToOne
  private Product product;

 

  public int getId() {
    return this.Id;
  }
  public void setId(int Id) {
    this.Id = Id;
  }


  public String getProdName() {
    return this.prodName;
  }
  public void setProdName(String prodName) {
     this.prodName = prodName;
  }

  
  public int getQuantity() {
    return this.quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }


  public int getPrice() {
    return this.price;
  }
  public void setPrice(int price) {
    this.price = price;
  }


  public Order getOrder() {
    return this.order;
  }
  public void setOrder(Order order) {
    this.order = order;
  }


  public Product getProduct() {
    return this.product;
  }
  public void setProduct(Product product) {
    this.product = product;
  }


  
  public String toString() {
    return  prodName + quantity + price  + order + product ;
  } 
}
