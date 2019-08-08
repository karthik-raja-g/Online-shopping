package com.ideas2it.OnlineShopping;

import model.Role;

import service.RoleService;

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

import common.DateUtil;
import common.utils.userException.RoleExceptionn;

import org.apache.log4j.Logger;


/**
 *<p>
 * The Role controller adds new roles to the online shopping. Any one can
 * add specific roles. Every role has unique Id and name
 * 
 *</p>
 * 
 * @author   karthik   created on 22 June 2019
 *
 */

public class RoleController extends HttpServlet {

    public RoleService roleService = RoleService.getInstance();
    static final Logger logger = Logger.getLogger(RoleController.class); 
    
  
/**
 * The doPost method is used to create new role and update role details
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
                case "/role" :
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/role.jsp");
                    requestDispatcher.forward(request,response);
                    break;

                case "/addRole" :
                    addRole(request);
                    response.sendRedirect("viewRoles");
                    break;

                case "/delete" :
                    deleteRole(request);
                    response.sendRedirect("viewRoles");
                    break;

                case "/update" :
                    updateRole(request,response);
                    break;

                case "/updatePage" :
                    updateRoleDetails(request);
                    response.sendRedirect("viewRoles");
                    break;

            }
        } catch (RoleExceptionn ex) {
            logger.error(ex);
            request.setAttribute("error",ex);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/Error.jsp");
            requestDispatcher.forward(request,response);
        }
    }


/** 
 * It displays all role name and corresponding details
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
                case "/role" :
                    doPost(request,response);
                    break;

                case "/viewRoles" :
                    displayRoles(request,response);
                    break;
            }           
        } catch (RoleExceptionn ex) {
            logger.error(ex);
            request.setAttribute("error",ex);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/Error.jsp");
            requestDispatcher.forward(request,response);
        }
    } 

/**
 * It changes the active status of a role
 *
 * @param        request        The object containing client request
 *
 */
    public void addRole(HttpServletRequest request) 
                    throws ServletException, IOException,RoleExceptionn { 
        String name = request.getParameter("name");
        String description = request.getParameter("description");  
        Role role = new Role();   
        role.setDescription(description);
        roleService.checkRoleName(name);
        role.setRoleName(name);
        roleService.addRole(role);
        logger.info(name+" Role added successfully");
    }

/**
 * It changes the active status of a role
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */
    public void displayRoles(HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException,RoleExceptionn {
 
        List<Role> roles = roleService.getRoles();
        request.setAttribute("roleList",roles);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/display.jsp");
        requestDispatcher.forward(request,response);
    }
        

/**
 * It changes the active status of a role
 *
 * @param        request        The object containing client request
 *
 */
    public void deleteRole(HttpServletRequest request) throws RoleExceptionn,
                                                            ServletException, IOException {

        int roleId = Integer.parseInt(request.getParameter("roleId"));
        Role role = roleService.getRoleById(roleId);
        roleService.deleteRole(role);
        logger.info(role.getRoleName()+" Role deleted successfully");
    }

/**
 * It gets the role object to be updated and forwards it to a JSP where
 * the user gives the input
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */
    public void updateRole(HttpServletRequest request, HttpServletResponse response) throws RoleExceptionn,
                                                            ServletException, IOException {

        int roleId = Integer.parseInt(request.getParameter("roleId"));
        Role role = roleService.getRoleById(roleId);
        request.setAttribute("update",role); 
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/update.jsp");
        requestDispatcher.forward(request,response);
        logger.info(role.getRoleName()+" Role found in db");
    }

/**
 * It gets the updated information from the user and updates the details
 * of the particular role
 *
 * @param        request        The object containing client request
 *
 */
    public void updateRoleDetails(HttpServletRequest request) throws RoleExceptionn,
                                                            ServletException, IOException {
        
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        Boolean status = Boolean.parseBoolean(request.getParameter("status"));
        LocalDateTime date = DateUtil.convertStringToDate(request.getParameter("created"));
        roleService.checkRoleName(request.getParameter("name"));
        Role role = new Role();
        role.setRoleId(roleId);
        role.setRoleName(request.getParameter("name"));
        role.setDescription(request.getParameter("description"));
        role.setIsActive(status);
        role.setCreatedDate(date);
        roleService.updateRole(role);
        logger.info("Details updated for role Id : "+String.valueOf(roleId));
    }
}





             

