package service;

import model.Cart;
import model.Product;
import model.Order;
import model.OrderDetails;
import model.User;

import service.CartService;
import service.ProductService;

import dao.OrderDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import common.DateUtil;
import common.utils.userException.CartException;
import common.utils.userException.OrderException;
import common.utils.userException.ProductException;

import org.apache.log4j.Logger;


/**
 *<p>
 * All business operations on an order is performed here
 *</p>
 *
 * @author   karthik  created on 22 June 2019
 */   

public class OrderService {

    public OrderDao dao = OrderDao.getInstance();
    public CartService cartService = CartService.getInstance();
    public ProductService productService = ProductService.getInstance();
    static final Logger logger = Logger.getLogger(OrderService.class);

/**
 * It adds all orders made into a list
 *
 * @param     user           The user object
 * @param     order          The order to be added
 * @param     orderdetail    The set with order details
 *
 */ 
    public void addOrder(User user,Order order,Set<OrderDetails> orderdetail) throws OrderException {
        order.setUser(user);
        order.setOrderDetails(orderdetail);
        order.setQuantity(getOrderSize(order.getOrderDetails()));
        order.setCost(getOrderCost(order.getOrderDetails()));
        order.setCreatedDate(DateUtil.getDate());
        order.setModifiedDate(DateUtil.getDate());
        order.setStatus(Boolean.TRUE);
        dao.addOrder(order);
        logger.info("Order created");
        
    }

/**
 * It adds order details inside an order based on carts selected by the user
 *
 * @param         order         The user Order
 * @param         cartIds       The string array containing cart Ids
 *
 * @return        orderDetails  The set of order details
 *
 */
    public Set<OrderDetails> setOrderDetails(Order order,String cartIds[]) throws CartException {
        Set <OrderDetails> orderDetails = new HashSet<OrderDetails>();
        for(String id : cartIds) {
            int cartId = Integer.parseInt(id);
            Cart cart = cartService.getCartById(cartId);
            OrderDetails orderItems = new OrderDetails();
            orderItems.setProduct(cart.getProduct());
            orderItems.setProdName(cart.getProduct().getName());
            orderItems.setPrice(cart.getProduct().getPrice());
            orderItems.setQuantity(cart.getQuantity());
            orderItems.setOrder(order);
            orderDetails.add(orderItems);
            logger.info("Cart Id : "+String.valueOf(cartId)+" added to order");
        }
        return orderDetails;
    }

/**
 * It returns a list of all orders made by an user
 *
 * @param      userId       The user Id of user
 *
 * @return     userOrders   The list of user orders
 *
 */
    public List<Order> getUserOrders(int userId) throws OrderException {
        List<Order> orders = dao.getOrders();
        List<Order> userOrders = new ArrayList<Order>();
        for (Order order : orders) {
            if(userId == order.getUser().getUserId())
                userOrders.add(order);
        }
        if(0 == userOrders.size())
            throw new OrderException("No orders created by user Id : "+userId);
        return userOrders;
    }

/**
 * It returns a particular order of an user
 *
 * @param     orderId      The order Id
 *
 * @return    Order        The particular order
 *
 */
    public Order getOrderById(int orderId) throws OrderException {
        Order order = dao.getOrderById(orderId);
        if (null == order)
            throw new OrderException("No order found for Id: " + orderId);
        return order;
    }

/**
 * It cancels a particular order
 *
 * @param     order       The order 
 *
 */
    public void cancelOrder(Order order) throws OrderException {
        dao.deleteOrder(order);
    }

/**
 * It returns the total number of products in each order
 *
 * @param    orderDetails           The list with all order details of an order 
 *  
 * @return   totalSize              The total number of products
 *
 */ 
    private int getOrderSize(Set<OrderDetails> orderDetails) {
        int totalSize = 0;
        for(OrderDetails orderDetail : orderDetails) {
            totalSize += orderDetail.getQuantity();
        }
        return totalSize;
    }

/**
 * It returns the total cost of  each order
 *
 * @param    orderDetails           The list with all order details of an order   
 *
 * @return   totalCost              The total cost of products 
 *  
 */ 
    private int getOrderCost(Set<OrderDetails> orderDetails)  {
        int totalCost = 0;
        for(OrderDetails orderDetail : orderDetails) {
            totalCost += orderDetail.getQuantity() * orderDetail.getProduct().getPrice();
        }   
        return totalCost;
    }
}
