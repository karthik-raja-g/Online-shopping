package dao;

import db.DatabaseConnection;

import model.Role;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException; 
import org.hibernate.Query;

import common.utils.userException.DatabaseConnectionException;
import common.utils.userException.RoleExceptionn;

import org.apache.log4j.Logger;

/**
 *<p>
 * It handles all the database related operations of a role. New roles
 * can be added,deleted,modified,exixting roles can be retrived
 *</p>
 *
 * @author   karthik   created on 21 June 2019 
 */

public class RoleDao {

    public Session session = null;
    public Query query; 
    static final Logger logger = Logger.getLogger(RoleDao.class);
    private static RoleDao instance = null; 

/**
 * Private constructor
 */
    private RoleDao() {
        logger.info("Singleton of RoleDao");
    }

/**
 * It returns an instance of RoleService class
 *
 */
    public static RoleDao getInstance() {
        if(null == instance) {
            logger.info("new RoleDao");
            instance = new RoleDao();
            return instance;
         }
      logger.info("old RoleDao");
      return instance;
    }

/**
 *
 * It adds roles inside the online shopping database
 *
 * @param     role        The role object containig details
 *
 */ 
    public void addRole(Role role) throws RoleExceptionn {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
	        session = databaseConnection.getSession();
	        Transaction transaction = session.beginTransaction();
            session.save(role);
    		transaction.commit();
            
        } catch (HibernateException ex) {
              logger.error("Connection Failed while adding "+role.getRoleName()+" into db");
              throw new RoleExceptionn("Database Connection Creation Failed while adding role "+ ex.getMessage());
        } finally { 
              session.close();
        }
    }
  
/**
 * It returns a particular role based on Id
 *
 * @param     roleId         The role Id
 *
 */
    public Role getRoleById(int roleId) throws RoleExceptionn {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
	        session = databaseConnection.getSession();
            Role role = (Role) session.get(Role.class,roleId);
            return role;
        } catch (HibernateException ex) {
              logger.error("Db connection failed while getting role: "+String.valueOf(roleId));
              throw new RoleExceptionn("Database Connection Creation Failed while fetching role "+ex.getMessage());
        } finally { 
            session.close();
        }
    }


/**
 * 
 * It gets data from the database and adds it inside a list for other operations
 * 
 * @return     roles        The list containg role objects
 *
 */
    public List<Role> getRoles() throws RoleExceptionn {
        List<Role> roles = new ArrayList<Role>();
        try{  
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
            session = databaseConnection.getSession();
            query = session.createQuery("FROM Role");
            roles = query.list();
            return roles;
        } catch (HibernateException ex) {
              logger.error("Filed to fetch data from db");
              throw new RoleExceptionn("Database Connection Creation Failed while fetching roles "+ex.getMessage());
        } finally { 
              session.close();
        }
    }
        
/**
 * It updates a particular role from the database
 *
 * @param      role      THe role object to be updated
 *
 */
    public void updateRole (Role role) throws RoleExceptionn {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
	        session = databaseConnection.getSession();
	        Transaction transaction = session.beginTransaction();
            session.update(role);
    		transaction.commit();
            session.close();
        } catch (HibernateException ex) {
              logger.error("Connection Failed while updating "+role.getRoleName());
              throw new RoleExceptionn("Database Connection Creation Failed updating role"+ex.getMessage());
        } finally { 
              session.close();
        }
    }
}






