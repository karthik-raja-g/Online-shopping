package com.ideas2it.OnlineShopping;

import model.Cart;
import model.Product;
import model.User;

import service.CartService;
import service.ProductService;
import service.UserService;

import common.DateUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import common.utils.userException.CartException;
import common.utils.userException.UserException;
import common.utils.userException.ProductException;

import org.apache.log4j.Logger; 

/**
 *<p>
 * It can add new items to cart, remove them, modify
 * them and show total value of the items added
 *</p>
 *
 * @author karthik  created on 18 June 2019
 *
 */

public class CartController extends HttpServlet {



    public CartService cartService = CartService.getInstance();
    public ProductService productService = ProductService.getInstance();
    static final Logger logger = Logger.getLogger(CartController.class); 

/**
 * The doPost method is used to create new cart and update cart details
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
                case "/cartDetails" :
                    addCart(request);
                    response.sendRedirect("viewCarts");
                    break;        

                case "/cartUpdate" :
                    updateCart(request,response);
                    break;

                case "/cartUpdatePage" :
                    updateDetails(request);
                    response.sendRedirect("viewCarts");
                    break;
        
                case "/cartDelete" :
                    deleteCart(request);
                    response.sendRedirect("viewCarts");
                    break;

                case "/addToCart" :
                    getProductForCart(request,response);
                    break;

            }
        } catch (ProductException ex) {
            logger.error("Error while adding product");
            request.setAttribute("error",ex);
            RequestDispatcher view = request.getRequestDispatcher("jsp/Error.jsp");
            view.forward(request,response);
        } catch (CartException ex) {
            logger.error("Error while adding cart");
            request.setAttribute("error",ex);
            RequestDispatcher view = request.getRequestDispatcher("jsp/Error.jsp");
            view.forward(request,response);
        } catch (UserException ex) {
            logger.error("Error while adding cart");
            request.setAttribute("error",ex);
            RequestDispatcher view = request.getRequestDispatcher("jsp/Error.jsp");
            view.forward(request,response);
        }
    }

/**
 * The doGet method is used to display carts
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String alias = request.getServletPath();
            switch(alias) {

                case "/viewCarts" :
                    displayCarts(request,response);
                    break;
            }
            
        } catch (CartException ex) {
            logger.error(ex);
            request.setAttribute("error",ex);
            RequestDispatcher view = request.getRequestDispatcher("jsp/Error.jsp");
            view.forward(request,response);
            
        } catch (ProductException ex) {
            logger.error("Error while adding product");
            request.setAttribute("error",ex);
            RequestDispatcher view = request.getRequestDispatcher("jsp/Error.jsp");
            view.forward(request,response);
        }
    }

/**
 * It is used to get cart details from the user in a sepearte jsp page
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */   

    public void getProductForCart (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
                                                     ProductException {

        int productId = Integer.parseInt(request.getParameter("prodId"));
        Product product = productService.getProductById(productId);
        request.setAttribute("product",product);
        RequestDispatcher view = request.getRequestDispatcher("jsp/cartDetails.jsp");
        view.forward(request,response);
    }

        
/**
 * It is used to get cart details from the user in a sepearte jsp page
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */   

    public void displayCarts (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
                                                     CartException,ProductException {
        User user = getUserFromSession(request);
        List<Cart> carts = cartService.getUserCarts(user.getUserId());
        request.setAttribute("userCarts",carts);
        RequestDispatcher view = request.getRequestDispatcher("jsp/cartDisplay.jsp");
        view.forward(request,response);
    }
             
/**
 * It gets the cart details from the user, sets them to a cart and adds the the cart to database
 *
 * @param        request        The object containing client request
 *
 */  
    public void addCart(HttpServletRequest request) throws ServletException, IOException,
                                                ProductException, CartException {
       
        User user = getUserFromSession(request);
        int productId = Integer.parseInt(request.getParameter("prodId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Cart cart = new Cart();
        cart.setQuantity(quantity);
        cartService.addCart(cart,user,productId);
    }
    

/**
 * It removes a particular cart based on its Id
 *
 * @param        request        The object containing client request
 *
 */
    public void deleteCart(HttpServletRequest request) throws CartException,ServletException, 
                                                                                IOException {

        int cartId = Integer.parseInt(request.getParameter("cartId"));
        Cart cart = cartService.getCartById(cartId);
        cartService.removeCart(cartId);

    }

/**
 * It gets new product quantity of a cart in seperate jsp page and updates the detail
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */  

    public void updateCart(HttpServletRequest request, HttpServletResponse response) throws CartException,ServletException, 
                                                                                IOException {

        int cartId = Integer.parseInt(request.getParameter("cartId"));
        Cart cart = cartService.getCartById(cartId);
        request.setAttribute("update",cart); 
        RequestDispatcher view = request.getRequestDispatcher("jsp/updateCart.jsp");
        view.forward(request,response);
    }

/**
 * It gets the updated information about the cart and then sets it to a new cart 
 *
 * @param        request        The object containing client request
 *
 */  

    public void updateDetails(HttpServletRequest request) throws ServletException, IOException, 
                                                                                     CartException,UserException,ProductException {

        int cartId = Integer.parseInt(request.getParameter("cartId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        int prodId = Integer.parseInt(request.getParameter("prodId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        LocalDateTime date = DateUtil.convertStringToDate(request.getParameter("created"));
        Cart cart = new Cart();
        cart.setCartId(cartId);
        cart.setQuantity(quantity);
        cart.setCreated(date);
        cartService.updateCart(cart,userId,prodId);
        logger.info("Details updated for cart Id : "+String.valueOf(cartId));

    }

/**
 * It gets the current logged in user object from session
 *
 * @param           request     The object containing client reques
 *
 * @return          user        The user object
 *
 */
    public User getUserFromSession(HttpServletRequest request) throws ServletException, IOException, CartException {
        HttpSession session=request.getSession(false);
        if(null == (User)session.getAttribute("user"))
            throw new CartException("The user has logged out");
        User user = (User)session.getAttribute("user");
        return user;
    }
}



