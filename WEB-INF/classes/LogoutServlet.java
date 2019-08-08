package com.ideas2it.OnlineShopping;

import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
import javax.servlet.*;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {  

/**
 * It destroys a session when the user clicks logout
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException { 
 
        HttpSession session=request.getSession(false); 
        if(null != session) {
            session.invalidate();
            System.out.println("ffff");
            response.sendRedirect("index.jsp");
        }
        
    }
}
            
