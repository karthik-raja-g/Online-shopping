package service;

import common.DateUtil;

import model.Role;
import dao.RoleDao;

import java.util.List;

import org.apache.log4j.Logger;
import common.utils.userException.RoleExceptionn;
import common.utils.userException.DatabaseConnectionException;

/**
 *<p>
 * All the business operations of a role is performed in
 * the role service 
 *</p>
 *
 * @author  karthik   created on 22 June 2019
 */

public class RoleService {

    private RoleDao dao = RoleDao.getInstance();
    static final Logger logger = Logger.getLogger(RoleService.class);
    private static RoleService instance = null;

/**
 * Private constructor
 */
    private RoleService() {
        logger.info("Singleton of RoleService");
    }

/**
 * It returns an instance of RoleService class
 *
 */
    public static RoleService getInstance() {
        if(null == instance) {
            logger.info("new RoleService");
            instance = new RoleService();
            return instance;
         }
      logger.info("old RoleService");
      return instance;
    }

/**
 *It adds a role to the database
 *
 * @param    role     The role object
 *
 */
    public  void addRole(Role role) throws RoleExceptionn {
        role.setCreatedDate(DateUtil.getDate());
        role.setModifiedDate(DateUtil.getDate());
        role.setIsActive(Boolean.TRUE);
        dao.addRole(role);
    }
  

/**
 * It returns a role from  the db based on the role Id.
 *
 * @param    roleId      The role Id 
 *
 * @return   Role        The role object   
 *
 */
    public  Role getRoleById(int roleId) throws  RoleExceptionn  {
        if(null == dao.getRoleById(roleId)) {
            logger.error("Role Id not found");
            throw new RoleExceptionn("Role Id does not exist");
        }
        return dao.getRoleById(roleId);
    }


/**
 * 
 * It checks if the same role is being added once again
 *
 * @param       name         The role name
 *
 */
    public  void checkRoleName(String name) throws RoleExceptionn {
        List<Role> roles = dao.getRoles();
        if(0 == roles.size())
            return;
        for(Role role : roles) { 
            if(name.equalsIgnoreCase(role.getRoleName())) {
                logger.error("Role already added");
                throw new RoleExceptionn("Role exists already");
            }
        }
     }
  
/**
 * It returns all roles created in a list
 *
 * @return       List<Role>      The list with all roles
 *
 */
    public  List<Role> getRoles() throws RoleExceptionn {
        return dao.getRoles();
    }

/**
 * It deletes a role
 *
 * @param        role        THe modified role object
 *
 */
    public void deleteRole(Role role) throws RoleExceptionn {
        role.setIsActive(Boolean.FALSE);
        role.setModifiedDate(DateUtil.getDate());
        dao.updateRole(role);
    }

/**
 * It updates a role details
 *
 * @param        role        THe modified role oblect
 *
 */
    public  void updateRole(Role role) throws RoleExceptionn {
        role.setModifiedDate(DateUtil.getDate());
        dao.updateRole(role);
    }
}


