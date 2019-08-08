package dao;

import db.DatabaseConnection;

import model.User;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException; 
import org.hibernate.Query;

import common.utils.userException.UserException;
import org.apache.log4j.Logger;

/**
 *<p>
 * It handles all the database related operations of a user. New users
 * can be added,deleted,modified,exixting users can be retrived
 *</p>
 *
 * @author         karthik  created on 22 June 2019
 *
 */

public class UserDao {

    public Session session = null;
    public Query query;
    private static UserDao instance = null; 
    static final Logger logger = Logger.getLogger(UserDao.class);

/**
 * Private constructor
 */
    private UserDao() {
        logger.info("Singleton of UserDao");
    }

/**
 * It returns an instance of UserDao class
 *
 */
    public static UserDao getInstance() {
        if(null == instance) {
            logger.info("new UserDao");
            instance = new UserDao();
            return instance;
         }
      logger.info("old UserDao");
      return instance;
    }


/**
 * It adds a user information to the List
 *
 * @param        user        The user object
 *
 */
    public void addUser(User user) throws UserException {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
	        session = databaseConnection.getSession(); 
	        Transaction transaction = session.beginTransaction();
            session.save(user);
    		transaction.commit();
        } catch (HibernateException ex) {
              throw new UserException("Database Connection Creation Failed during adding user "+user.getName()+": "+ex.getMessage());
        } finally { 
              session.close();
        }
    }


/**
 * It creates a list of all users or a particular user in the database and returns the list
 *
 * @param              userId             The user Id of user
 * @return             users              The list of all users
 *
 */
    public List<User> getUsers() throws UserException {
        List<User> users = new ArrayList<User>();
        try{  
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
            session = databaseConnection.getSession();  
            query = session.createQuery("FROM User");
            users = query.list();
            return users;
        } catch (HibernateException ex) {
              throw new UserException("Database Connection Creation Failed when tried to fetch user details "+ex.getMessage()); 
        } finally { 
              session.close();
        }
    }

/**
 * It returns a User object based on userId
 * 
 * @param      userId       The userId
 *
 * @return     User         The user Object
 *
 */

    public User getUserById(int userId) throws UserException {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
	        session = databaseConnection.getSession();
            User user = (User) session.get(User.class,userId);
            return user;
        } catch (HibernateException ex) {
              throw new UserException("Database Connection Creation Failed while getting user : " 
                                                                                    +String.valueOf(userId)+ex.getMessage());
        } finally { 
              session.close();
        }
    }


/**
 * It modifies the user information
 *
 *  @param      user      The user oblect to modify
 */
    public void updateUser(User user) throws UserException {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
	        session = databaseConnection.getSession();
	        Transaction transaction = session.beginTransaction();
            session.update(user);
    		transaction.commit();
        } catch (HibernateException ex) {
              throw new UserException("Database Connection Creation Failed during updating user "+ex.getMessage()); 
        } finally { 
              session.close();
        }
    }

/* It returns a User object based on username
 * 
 * @param      userId       The userId
 *
 * @return     User         The user Object
 *
 */

    public User getUserByUsername(String username) throws UserException {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
	        session = databaseConnection.getSession();
            Criteria criteria = session.createCriteria(User.class);
            User user =(User)criteria.add(Restrictions.eq("userName",username)).uniqueResult();
            return user;
        } catch (HibernateException ex) {
              throw new UserException("Database Connection Creation Failed during updating users "+ex.getMessage()); 
        } finally { 
              session.close();
        }
    }
}


