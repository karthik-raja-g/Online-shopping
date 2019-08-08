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
import java.util.Arrays;

import org.apache.log4j.Logger;  

import common.utils.userException.RoleExceptionn;
      
public class AccessFilter implements Filter {  

    static final Logger logger = Logger.getLogger(AccessFilter.class); 
	
    private List<String> excludedUrls;
	
/**
 * The init method initializes the filter
 *
 * @param        filterConfig        configures the filter
 *
 */
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludePages = filterConfig.getInitParameter("excludedUrls");
        excludedUrls = Arrays.asList(excludePages.split(","));
    }

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
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            HttpSession session = httpRequest.getSession(false);
            String path = ((HttpServletRequest) request).getServletPath();
            if(!path.equals("/index.jsp")) {
                if(null == httpRequest.getSession(false) || null == (String)session.getAttribute("role"))
                    throw new RoleExceptionn("Not logged In yet");
                String role = (String)session.getAttribute("role");
                if((!role.equals("admin")) && excludedUrls.contains(path))
                    throw new RoleExceptionn("You are not authorized to view this page");
                chain.doFilter(request,response);
            }
            else 
                chain.doFilter(request,response);
            if(path.equals("/index.jsp") && null != session.getAttribute("user")) {
                System.out.println("jjjjj");
              throw new RoleExceptionn("Already in a session. Please logout first");
            }
        } catch (RoleExceptionn e) {
            logger.error(e);
            request.setAttribute("error",e); 
            RequestDispatcher view = request.getRequestDispatcher("jsp/Error.jsp");
            view.forward(request,response);
        }
    }
}   
                
            


