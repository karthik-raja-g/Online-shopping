package com.ideas2it.OnlineShopping;

import model.Product;
import model.User;

import service.ProductService;
import service.UserService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
  
import common.DateUtil;
import common.utils.userException.UserException;
import common.utils.userException.ProductException;

import org.apache.log4j.Logger;

/**
 *<p>
 * It controls the overall operations performed on a product
 * New product can be created, given various specification, 
 * modified and even removed. A product can be searched based on 
 * the product Id
 *</p>
 *
 * @author   karthik   created on 21 June 2019
 *
 */ 

public class ProductController extends HttpServlet {
 

    public ProductService productService = ProductService.getInstance();
    static final Logger logger = Logger.getLogger(ProductController.class);  

/**
 * The doPost method is used to create new product and update product details
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
                case "/productDetails" :
                    addProduct(request);
                    response.sendRedirect("viewProducts");
                    break;
        

                case "/productUpdate" :
                    updateProduct(request,response);
                    break;

                case "/productUpdatePage" :
                    updateProductDetails(request);
                    response.sendRedirect("viewProducts");
                    break;
        
                case "/productDelete" :
                    deleteProduct(request);
                    response.sendRedirect("viewProducts");
                    break;

                case "/product" :
                    RequestDispatcher view = request.getRequestDispatcher("jsp/product.jsp");
                    view.forward(request,response);
                    break;
            }
        } catch (ProductException ex) {
            logger.error(ex);
            request.setAttribute("error",ex);
            RequestDispatcher view = request.getRequestDispatcher("jsp/Error.jsp");
            view.forward(request,response);
        } catch (UserException ex) {
            logger.error(ex);
            request.setAttribute("error",ex);
            RequestDispatcher view = request.getRequestDispatcher("jsp/Error.jsp");
            view.forward(request,response);
        }
    }


/**
 * The doGet method is used to display products
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

                case "/viewProducts" :
                    displayProducts(request,response);
                    break;

                case "/productInfo" :
                    showProductInfo(request,response);
                    break;

            }            
        } catch (ProductException ex) {
            logger.error(ex);
            request.setAttribute("error",ex);
            RequestDispatcher view = request.getRequestDispatcher("jsp/Error.jsp");
            view.forward(request,response);
        }
    }

/**
 * It displays information about a product
 *
 * @param        request        The object   containing client request
 * @param        response       The object to which the server attaches the response
 *
 */
    public void showProductInfo(HttpServletRequest request, HttpServletResponse response) throws 
                                            ServletException,ProductException,IOException {

        int prodId = Integer.parseInt(request.getParameter("prodId"));
        Product product = productService.getProductById(prodId);
        request.setAttribute("product",product);
        RequestDispatcher view = request.getRequestDispatcher("jsp/productInfo.jsp");
        view.forward(request,response);
    }
        

/**
 * It displays all the products
 *
 * @param        request        The object   containing client request
 * @param        response       The object to which the server attaches the response
 *
 */
    public void displayProducts(HttpServletRequest request, HttpServletResponse response) throws 
                                            ServletException,ProductException,IOException {

        List<Product> products = productService.getProducts();
        request.setAttribute("productList",products);
        RequestDispatcher view = request.getRequestDispatcher("jsp/displayProducts.jsp");
        view.forward(request,response);
    }
 
/**
 * It adds products to the database
 *
 * @param        request        The object   containing client request
 *
 */
        
    public void addProduct(HttpServletRequest request) throws 
                                            ServletException,ProductException,IOException {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        Product product = new Product();
        User user = getUserFromSession(request);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        productService.addProduct(product,user);
        logger.info(name+" Added successfully");


    }

/**
 * It changes the available status of a product
 *
 * @param        request        The object   containing client request
 *
 */

    public void deleteProduct(HttpServletRequest request) throws 
                                                            ServletException, IOException,ProductException  {
        int prodId = Integer.parseInt(request.getParameter("prodId"));
        Product product = productService.getProductById(prodId);
        productService.deleteProduct(product);
        logger.info(product.getName()+" product deleted successfully");
    }

/**
 * It gets the product object to be updated and forwards it to a JSP where
 * the user gives the new details
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */
    public void updateProduct(HttpServletRequest request, HttpServletResponse response) throws 
                                      ServletException, IOException, ProductException {

        int prodId = Integer.parseInt(request.getParameter("prodId"));
        Product product = productService.getProductById(prodId);
        request.setAttribute("update",product); 
        RequestDispatcher view = request.getRequestDispatcher("jsp/updateProduct.jsp");
        view.forward(request,response);
    }

/**
 * It gets the updated details from the user and sets them to the particular product
 * and then makes an update
 *
 * @param        request        The object containing client request
 *
 */
    public void updateProductDetails(HttpServletRequest request) throws 
                                                            ServletException, IOException, ProductException,UserException {

        int prodId = Integer.parseInt(request.getParameter("prodId"));
        int price = Integer.parseInt(request.getParameter("price"));
        Boolean status = Boolean.parseBoolean(request.getParameter("status"));
        LocalDateTime date = DateUtil.convertStringToDate(request.getParameter("created"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        Product product = new Product();
        product.setId(prodId);
        product.setName(request.getParameter("name"));
        product.setDescription(request.getParameter("description"));
        product.setSKU(request.getParameter("SKU"));
        product.setPrice(price);
        product.setStatus(status);
        product.setCreated(date);
        productService.updateProduct(product,userId);
        logger.info("Details updated for product Id : "+String.valueOf(prodId));
    }

/**
 * It gets the current logged in user object from session
 *
 * @param           request     The object containing client reques
 *
 * @return          user        The user object
 *
 */
    public User getUserFromSession(HttpServletRequest request) throws ServletException, IOException, ProductException {
        HttpSession session=request.getSession(false);
        if(null == (User)session.getAttribute("user"))
            throw new ProductException("The user has logged out");
        User user = (User)session.getAttribute("user");
        return user;
    }
}




    

