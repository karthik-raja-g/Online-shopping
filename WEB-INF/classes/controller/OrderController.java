package controller;

import model.Cart;
import model.Order;
import model.OrderDetails;
import model.Product;
import model.User;

import service.CartService;
import service.OrderService;
import service.ProductService;
import service.UserService;

import common.DateUtil;
import common.InputUtil;

import constants.Constants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.utils.userException.CartException;
import common.utils.userException.DatabaseConnectionException;
import common.utils.userException.NoRecordFoundException;
import common.utils.userException.OrderException;
import common.utils.userException.ProductException;

/**
 *<p>
 * It allows the user to move his/her cart items into an order
 * They can finalize the order or cancel it
 *</p>
 *
 * @author  karthik   created on 22 June 2019
 *  
 */
public class OrderController {

  public OrderService orderService = new OrderService();
  public CartService cartService = new CartService();
  public ProductService productService = new ProductService();
  public UserService userService = new UserService();

/**
 * The menu from which various operations on orders can be
 * performed.
 *
 * @param        userId       The user Id of cart owner
 * 
 */
    public void orderMenu(int userId) {
        System.out.println(Constants.DECOR+"WELCOME TO ORDER MENU"+Constants.DECOR_END);
        int choice = InputUtil.getInt(Constants.ORDER_MENU);
        while(5 >= choice) {
            switch(choice) {
                case 1:
                    showUserCarts(userId); 
                    break;
 
                case 2:
                    setOrder(userId);
                    break;

                case 3:
                    cancelOrder(userId);
                    break;

                case 4:
                    showOrders(userId);
                    break;

                case 5:
                    showOrderDetails(userId);
                    break;

            }
            choice = InputUtil.getInt(Constants.ORDER_MENU);
            if (6 == choice) {
                System.out.println(Constants.DECOR+"THANK YOU"+Constants.DECOR_END);
                break;
            }
        }
    }
                          
/**
 * It shows a user's carts
 *
 * @param      userId            The user Id 
 *
 */
    public void showUserCarts(int userId) {
        try {
            List<Cart> carts = cartService.getUserCarts(userId);
            System.out.println(Constants.CART_HEADER); 
            for(Cart cart : carts) {
                System.out.println(String.valueOf(cart.getCartId())+"\t\t"+String.valueOf(userId)
                           +"\t\t"+String.valueOf(cart.getProduct().getId())
                           +"\t\t"+String.valueOf(cart.getQuantity())+"\t\t"+cart.getCreated()+"\t\t"+cart.getModified());
            }
        } catch (CartException ex) {
              System.out.println(ex);
        } 
    }

/**
 * It creates user's cart as an order
 *
 * @param     userId       The Id of user
 *
 */
    public void setOrder(int userId) {

        try {
            Order order = new Order();
            order.setUser(userService.getUser(userId));
            order.setStatus(Boolean.TRUE);
            Set<OrderDetails> orderdetail = getOrderDetails(order,userId);
            if(orderdetail.size() != 0) {
                order.setOrderDetails(orderdetail);
                order.setQuantity(orderService.getOrderSize(order.getOrderDetails()));
                order.setCost(orderService.getOrderCost(order.getOrderDetails()));
                order.setCreatedDate(DateUtil.getCurrentDateTime());
                order.setModifiedDate(DateUtil.getCurrentDateTime());
                orderService.addOrder(order);
            }
	    } catch (ProductException ex) {
              System.out.println(ex);
	    } catch (NoRecordFoundException e) {
              System.out.println(e);
        } catch (OrderException ex) {
              System.out.println(ex);
        } catch (CartException ex) {
              System.out.println(ex);
        } catch (DatabaseConnectionException ex) {
              System.out.println(ex);
        }
    }

/**
 * It adds product information i.e order details into  a order
 *
 * @param        order                The order inside which orderdetails are to be stored
 * @param        userId               The user Id
 *
 * @return       Set<OrderDetails>    The set of order details
 *
 */
    public Set<OrderDetails> getOrderDetails(Order order,int userId) 
                                    throws ProductException,OrderException,CartException {
        showUserCarts(userId);
        Set <OrderDetails> orderDetails = new HashSet<OrderDetails>();
        int flag = InputUtil.getInt("Enter 1 to add carts\n");
        while(1 == flag) {
            int cartId = (InputUtil.getInt("Enter cart Id to add : "));
            Cart cart = cartService.getCart(cartId);
            if(!orderService.checkUserCart(cart,userId)) {
                System.out.println("This cart dosen't belong to user Id : " +String.valueOf(userId));
                break;
            }         
            OrderDetails orderProduct = new OrderDetails();
            orderProduct.setOrder(order);
            orderProduct.setProduct(cart.getProduct());
            orderProduct.setProdName(orderProduct.getProduct().getName());
            orderProduct.setQuantity(cart.getQuantity());
            int price = productService.getProductById(cart.getProduct().getId()).getPrice();
            orderProduct.setPrice(price);
            orderDetails.add(orderProduct);
            System.out.println("\nCart : "+String.valueOf(cartId)+" added to order");
            flag = InputUtil.getInt("Enter 1 to add carts. 2 to exit : ");
        }
        return orderDetails;
    }

/**
 *It shows all orders of th user
 *
 * @param     userId      The user's Id
 *
 */
    public void showOrders(int userId) {
        try {
            List<Order> orders = orderService.getUserOrders(userId);
            System.out.println(Constants.ORDER_HEADER);
            for(Order order : orders) {
                System.out.println( order.getOrderId() + "\t\t" + order.getUser().getUserId() + "\t\t" + order.getCost() + "\t\t" 
                                                      +order.getStatus());
            }

        } catch (OrderException ex) {
             System.out.println(ex);
        }
    }
        
/**
 * It sets the order status as cancelled
 *
 * @param        userId       The user Id
 *
 */
    public void cancelOrder(int userId) {
        try {
            int orderId = InputUtil.getInt("Enter order Id to cancel : ");
            Order order = orderService.getOrder(orderId);
            orderService.checkUserOrder(order,userId);
            orderService.cancelOrder(orderId);
            System.out.println("\nOrder cancelled for order Id : "+String.valueOf(orderId));
        } catch (OrderException ex) {
             System.out.println(ex);
        }
    }

/**
 * It shows the order details of a particular order
 *
 * @param          userId           The user Id of user
 *
 */
    public void showOrderDetails(int userId) {
        try {
            int orderId = InputUtil.getInt("Enter order Id  : ");
            Order order = orderService.getOrder(orderId);
            orderService.checkUserOrder(order,userId);
            System.out.println(Constants.ORDER_DETAILS_HEADER);
            for(OrderDetails details : order.getOrderDetails()) {
                System.out.println(String.valueOf(details.getId()).concat("\t\t").concat(details.getProdName()).concat("\t\t\t")
                                  .concat(String.valueOf(details.getProduct().getId())).concat("\t\t\t")
                                  .concat(String.valueOf(details.getPrice())).concat("\t\t")
                                  .concat(String.valueOf(details.getQuantity())).concat("\t\t")
                                  .concat(String.valueOf(details.getOrder().getOrderId())));
            }
        } catch (OrderException ex) {
             System.out.println(ex);
        }
    }
}
