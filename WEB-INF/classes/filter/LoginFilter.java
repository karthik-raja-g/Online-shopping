package com.ideas2it.OnlineShopping;

import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.*; 
import javax.servlet.http.*; 
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

import model.Role;
import model.User;
import service.UserService;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;  

import common.utils.userException.UserException;
      
public class LoginFilter implements Filter {  

    public UserService userService = UserService.getInstance();
    static final Logger logger = Logger.getLogger(LoginFilter.class); 
      
    public void init(FilterConfig arg0) throws ServletException {}  

/**
 * The doFilter method filters the client request based on the role of the user
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 * @param        chain          The Filterchain object linking the next fiter or servlet
 *
 */          
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
                                                            throws IOException, ServletException {
  
        try {                    
            String username  = request.getParameter("username");  
            String password = request.getParameter("password");
            User user = userService.getUserByUsername(username);
            userService.checkPassword(password,user);
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            HttpSession session = httpRequest.getSession(true);  
            session.setAttribute("user",user);
            if(!userService.isAdmin(user)) {
                session.setAttribute("role","user");
                chain.doFilter(request,response); 
                return;                           
            }
            session.setAttribute("role","admin");
            chain.doFilter(request,response);
        } catch (UserException e) {
            logger.error(e);
            request.setAttribute("error",e); 
            RequestDispatcher view = request.getRequestDispatcher("jsp/Error.jsp");
            view.forward(request,response);
        }
    }  


/**
 * It destroys the filter
 *
 */
    public void destroy() {} 
      
}  
