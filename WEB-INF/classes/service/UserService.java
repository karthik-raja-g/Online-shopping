package service;

import common.DateUtil;

import model.Role;
import model.User;

import service.RoleService;

import dao.UserDao;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import org.apache.log4j.Logger;
import common.utils.userException.UserException;
import common.utils.userException.RoleExceptionn;
import common.utils.StringHasher;

/**
 *<p>
 * The UserService class performs various business operations on users.  
 *</p>
 *
 * @author  karthik   created on 22 June 2019
 *
 */ 

public class UserService {

    public RoleService roleService = RoleService.getInstance();
    public UserDao dao = UserDao.getInstance();
    static final Logger logger = Logger.getLogger(UserService.class);
    private static UserService instance = null; 

/**
 * Private constructor
 */
    private UserService() {
      logger.info("Singleton of UserService");
    }

/**
 * It returns an instance of UserService class
 *
 */
    public static UserService getInstance() {
        if(null == instance) {
            logger.info("new userservice");
            instance = new UserService();
            return instance;
         }
      logger.info("old userService");
      return instance;
    }


/**
 * It passes the list and user object from controller to Dao
 * where user object is added inside the list
 *
 * @param    user        The user object
 *
 */
    public void addUser(User user) throws UserException {
        user.setIsActive(Boolean.TRUE);
        user.setCreated(DateUtil.getDate());
        user.setModified(DateUtil.getDate());
        dao.addUser(user);
    
    }

/**
 * It gets user information in a list
 * 
 * @return       List        List of users
 *
 */
    public  List<User> getUsers() throws UserException {
        return dao.getUsers();
    }

/**
 * It gets the user information of a single user
 *
 * @param      userId         The userId if the user
 *
 * @return     User           The user object
 *
 */
    public  User getUserById(int userId) throws UserException {
        User user = dao.getUserById(userId);
        if(null == user){
            logger.info("User not found in db");
            throw new UserException("User not found");
        }
        return user;
    }

/**
 * It checks if a user has Admin role or not
 *
 * @param       user         The user object
 * 
 * @return      Boolean      True or false 
 *
 */
    public  Boolean isAdmin(User user) { 
       for(Role role : user.getRoles()) {
            if(role.getRoleName().equals("ROLE_ADMIN")) 
                return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

/**
 * It modifies user information based on selected choice
 *
 * @param       user       The modified user object
 * @param       userId     The user Id of user
 *
 */
    public  void updateUser(User user,int userId) throws UserException {
        Set<Role> roles = getUserById   (userId).getRoles();
        user.setRoles(roles);
        user.setModified(DateUtil.getDate());
        dao.updateUser(user);
    }

/**
 * It deletes an user by setting active status to false
 *
 * @param       user       The modified user object
 *
 */
    public  void deleteUser(User user) throws UserException {
        user.setIsActive(Boolean.FALSE);
        user.setModified(DateUtil.getDate());
        dao.updateUser(user);
    }

/**
 * It gets the user information of a single user
 *
 * @param      userId         The userId if the user
 *
 * @return     User           The user object
 *
 */
    public  User getUserByUsername(String username) throws UserException {
        if(null == dao.getUserByUsername(username)) {
            logger.error("User not found for username");
            throw new UserException("User not found for username: "+username);
        }
        return dao.getUserByUsername(username);
    }

/**
 * It adds roles to a user
 *
 * @param       user        The user to which the roles are added
 * @param       roleId[]    The string array containig roleIds
 *
 */
    public  void setRolesToUser(User user, String roleIds[]) throws RoleExceptionn {
        Set roles = new HashSet<Role>();
        for(String id : roleIds) {
            int roleId = Integer.parseInt(id);
            Role role = roleService.getRoleById(roleId);
            roles.add(role);
        }
        user.setRoles(roles);
    }

/**
 * It checks the password hash matches the input password
 *
 * @param          password          The input password
 * @param          user              The user to check
 *
 */
    public void checkPassword(String password, User user) throws UserException {
        String checkSum = StringHasher.getSHA(password);
        if(!checkSum.equals(user.getPassword()))
            throw new UserException("Incorrect password");
    }
}
    





