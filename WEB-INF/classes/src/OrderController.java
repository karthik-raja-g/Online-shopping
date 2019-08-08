package com.ideas2it.OnlineShopping;

import model.Cart;
import model.Order;
import model.OrderDetails;
import model.Product;
import model.User;

import service.CartService;
import service.OrderService;
import dao.OrderDao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.time.LocalDateTime;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import common.DateUtil;
import common.utils.userException.CartException;
import common.utils.userException.DatabaseConnectionException;
import common.utils.userException.UserException;
import common.utils.userException.OrderException;
import common.utils.userException.ProductException;

import org.apache.log4j.Logger;

/**
 *<p>
 * It allows the user to move his/her cart items into an order
 * They can finalize the order or cancel it
 *</p>
 *
 * @author  karthik   created on 22 June 2019
 *  
 */
public class OrderController extends HttpServlet {

    public OrderService orderService = new OrderService();
    public CartService cartService = CartService.getInstance();
    static final Logger logger = Logger.getLogger(OrderController.class); 


/**
 * The doPost method is used to create new order and update order details
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException { 
        try {
            String alias = request.getServletPath();
            switch(alias) {
                case "/selectCarts" :
                    addOrder(request,response);
                    response.sendRedirect("viewOrders");
                    break;

                case "/orderDelete" :
                    deleteOrder(request,response);
                    response.sendRedirect("viewOrders");
                    break;

                case "/order" :
                    getCartDetails(request,response);
                    break;
            }

        } catch (OrderException ex) {
            logger.error("Error while hitting the database for making changes");
            request.setAttribute("error",ex);
            RequestDispatcher view = request.getRequestDispatcher("jsp/Error.jsp");
            view.forward(request,response);
        } catch (CartException ex) {
            logger.error(ex);
            request.setAttribute("error",ex);
            RequestDispatcher view = request.getRequestDispatcher("jsp/Error.jsp");
            view.forward(request,response);
        } catch (ProductException ex) {
            logger.error(ex);
            request.setAttribute("error",ex);
            RequestDispatcher view = request.getRequestDispatcher("jsp/Error.jsp");
            view.forward(request,response);
        }
    }         

/**
 * The doPost method is used to show orders and order details
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException { 
        try {
            String alias = request.getServletPath();
            switch(alias) {
                case "/viewOrders" :
                    displayOrders(request,response);
                    break;

                case "/showOrderDetails" :
                    displayOrderDetails(request,response);
                    break;

                case "/order" :
                    doPost(request,response);
                    break;
            }
            
        } catch (OrderException ex) {
            logger.error(ex);
            request.setAttribute("error",ex);
            RequestDispatcher view = request.getRequestDispatcher("jsp/Error.jsp");
            view.forward(request,response);
        }
    }

/**
 * It  displays orders
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */
    public void displayOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
                                                                                    OrderException { 

        User user = getUserFromSession(request);
        List<Order> orders = orderService.getUserOrders(user.getUserId());
        request.setAttribute("orderList",orders);
        RequestDispatcher view = request.getRequestDispatcher("jsp/orderDisplay.jsp");
        view.forward(request,response);
    }

/**
 * It  displays order details
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */

    public void displayOrderDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
                                                                                    OrderException { 
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = orderService.getOrderById(orderId);
        Set<OrderDetails> orderItems = order.getOrderDetails();
        request.setAttribute("orderDetailList",orderItems);
        RequestDispatcher view = request.getRequestDispatcher("jsp/orderDetailsdisplay.jsp");
        view.forward(request,response);
    }
                     
/**
 * It asks the user to select carts to add to order
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */
    public void getCartDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException,   
                                                               IOException,CartException,OrderException { 
        User user = getUserFromSession(request);
        List<Cart> carts = cartService.getUserCarts(user.getUserId());
        request.setAttribute("userCarts",carts);
        RequestDispatcher view = request.getRequestDispatcher("jsp/order.jsp");
        view.forward(request,response);
            

    }
       
/**
 * It creates a new order
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */
    public void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
                                                                OrderException,ProductException,CartException { 

        User user = getUserFromSession(request);
        Order order = new Order();
        Set<OrderDetails> orderdetail = getOrderDetails(request,order);
        orderService.addOrder(user,order,orderdetail);
    }

/**
 * It adds product information i.e order details into  a order
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 * @param        order          The order to which the details are to be added
 *
 * @return       Set<OrderDetails>    The set of order details
 *
 */
    public Set<OrderDetails> getOrderDetails(HttpServletRequest request,Order order) 
                                    throws ProductException,OrderException,CartException,ServletException,IOException {

            if(null == request.getParameterValues("cartId"))
                throw new OrderException("Add atleast one cart to the order");
            String cartIds[] = request.getParameterValues("cartId");
            return orderService.setOrderDetails(order,cartIds);

    }

/**
 * It deletes an order
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */

    public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
                                                                OrderException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = orderService.getOrderById(orderId);
        order.setStatus(Boolean.FALSE);
        order.setModifiedDate(DateUtil.getDate());
        orderService.cancelOrder(order);
        logger.info("order deleted");
    }

/**
 * It gets the current logged in user object from session
 *
 * @param           request     The object containing client reques
 *
 * @return          user        The user object
 *
 */
    public User getUserFromSession(HttpServletRequest request) throws ServletException, IOException, OrderException {
        HttpSession session=request.getSession(false);
        if(null == (User)session.getAttribute("user"))
            throw new OrderException("The user has logged out");
        User user = (User)session.getAttribute("user");
        return user;
    }
}


