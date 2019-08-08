package com.ideas2it.OnlineShopping;

import model.Role;
import model.User;
import dao.UserDao;

import service.RoleService;
import service.UserService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import common.DateUtil;
import common.utils.userException.UserException;
import common.utils.userException.RoleExceptionn;
import common.utils.StringHasher;

import org.apache.log4j.Logger;

/**
 *<p>
 * It controls the overall operations performed on a user
 * New user can be created, given various roles, 
 * modified and even removed. A user can be searched based on 
 * the user Id
 *</p>
 *
 * @author   karthik   created on 21 June 2019
 *
 */ 

public class UserController extends HttpServlet {

    public RoleService roleService = RoleService.getInstance();
    static final Logger logger = Logger.getLogger(UserController.class); 
    public UserService userService = UserService.getInstance();
  

/**
 * The doPost method is used to create new user and update user details
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
                case "/userDetails" :
                    addUser(request);
                    response.sendRedirect("viewUsers");
                    break;        

                case "/userUpdate" :
                    updateUser(request,response);
                    break;

                case "/userUpdatePage" :
                    updateDetails(request);
                    response.sendRedirect("viewUsers");
                    break;
        
                case "/userDelete" :
                    deleteUser(request);
                    response.sendRedirect("viewUsers");
                    break;

                case "/user" :
                    getUserDetails(request,response);
                    break;
            }
        } catch (RoleExceptionn ex) {
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
 * The doGet method is used to display user details
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                     
        try {
            String alias = request.getServletPath();
            switch(alias) {
                case "/user" :
                    doPost(request,response);
                    break;

                case "/viewUsers" :
                    displayUsers(request,response);
                    break;
            }
        } catch (UserException ex) {
            logger.error(ex);
            request.setAttribute("error",ex);
            RequestDispatcher view = request.getRequestDispatcher("jsp/Error.jsp");
            view.forward(request,response);
        }
    } 

/**
 * It is used to get details from the user
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */
    private void getUserDetails(HttpServletRequest request, HttpServletResponse response) throws 
                                            ServletException,RoleExceptionn,IOException {
                                                                                   
        List<Role> roles = roleService.getRoles();
        request.setAttribute("roleList",roles); 
        RequestDispatcher view = request.getRequestDispatcher("jsp/user.jsp");
        view.forward(request,response);
    }
        

/**
 * It fetches all the details provided by the user, adds them to an user object and the
 * adds the user into database
 *
 * @param        request        The object containing client request
 *
 */
    private void addUser(HttpServletRequest request) 
                                           throws UserException,RoleExceptionn,ServletException,IOException {

        User user = new User();
        addRolesToUser(request,user);
        String name = request.getParameter("name");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordHash = StringHasher.getSHA(password);
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        long phone = Long.parseLong(request.getParameter("phone"));
        user.setName(name);
        user.setUserName(userName);
        user.setPassword(passwordHash);
        user.setAddress(address);
        user.setEmail(email);
        user.setPhone(phone);
        userService.addUser(user);
        logger.info("user added successfully");
    }

/**
 * It adds roles to the user based on their selection
 *
 * @param        request        The object containing client request with role details
 * @param        user           The user to which the roles are to be added
 *
 */
    private void addRolesToUser(HttpServletRequest request,User user) throws UserException,    
                                                                        RoleExceptionn,ServletException,IOException {
        if(null == request.getParameterValues("roleId"))
            throw new RoleExceptionn("Select atleast one role");
        String roleIds[] = request.getParameterValues("roleId");
        userService.setRolesToUser(user,roleIds);
        logger.info("Roles added successfully to user");

    }

/**
 * It fetches all the details provided by the user, adds them to an user object and the
 * adds the user into database
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */
    private void displayUsers(HttpServletRequest request, HttpServletResponse response) 
                                           throws UserException,ServletException,IOException {

        List<User> users = userService.getUsers();
        request.setAttribute("userList",users);
        RequestDispatcher view = request.getRequestDispatcher("jsp/displayUser.jsp");
        view.forward(request,response);
    }

                    
/**
 * It changes the active status of a user
 *
 * @param        request        The object containing client request
 *
 */

    private void deleteUser(HttpServletRequest request) throws UserException,
                                                            ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userService.getUserById(userId);
        userService.deleteUser(user);
        logger.info(user.getName()+" User deleted successfully");
    }

/**
 * It gets the user object to be updated and forwards it to a JSP where
 * the user gives the new details
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */
    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws RoleExceptionn,
                                      ServletException, IOException,UserException {

        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userService.getUserById(userId);
        request.setAttribute("update",user); 
        RequestDispatcher view = request.getRequestDispatcher("jsp/updateUser.jsp");
        view.forward(request,response);
    }

/**
 * It gets the updated details from the user and sets them to the particular user
 * and then makes an update
 *
 * @param        request        The object containing client request
 * @param        response       The object to which the server attaches the response
 *
 */
    private void updateDetails(HttpServletRequest request) throws
                                                            ServletException, IOException, UserException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        long phone = Long.parseLong(request.getParameter("phone"));
        Boolean status = Boolean.parseBoolean(request.getParameter("status"));
        LocalDateTime date = DateUtil.convertStringToDate(request.getParameter("created"));
        String passwordHash = StringHasher.getSHA(request.getParameter("password"));
        User user = new User();
        user.setUserId(userId);
        user.setName(request.getParameter("name"));
        user.setUserName(request.getParameter("username"));
        user.setPassword(passwordHash);
        user.setEmail(request.getParameter("email"));
        user.setPhone(phone);
        user.setAddress(request.getParameter("address"));
        user.setIsActive(status);
        user.setCreated(date);
        userService.updateUser(user,userId);
        logger.info("Details updated for user Id : "+String.valueOf(userId));
    }

}



