package dao;

import db.DatabaseConnection;

import model.Cart;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException; 
import org.hibernate.Query;

import common.utils.userException.DatabaseConnectionException;
import common.utils.userException.CartException;

import org.apache.log4j.Logger;

/**
 *<p>
 * It adds the information of each product in the cart inside
 * a list. It can also remove a particular item.
 *</p>
 *
 * @author  karthik   created on 19 June 2019
 *
 */ 
public class CartDao {

    public Session session = null;
    static Query query;
    static final Logger logger = Logger.getLogger(CartDao.class);
    private static CartDao instance = null; 

/**
 * Private constructor
 */
    private CartDao() {
        logger.info("Singleton of CartDao");
    }

/**
 * It returns an instance of CartDao class
 *
 */
    public static CartDao getInstance() {
        if(null == instance) {
            logger.info("new CartDao");
            instance = new CartDao();
            return instance;
         }
      logger.info("old CartDao");
      return instance;
    }

/**
 * It adds a cart object to database 
 *
 * @param   cart      The object containig product information
 *
 */
  public void addCart(Cart cart) throws CartException {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
	        session = databaseConnection.getSession();
	        Transaction transaction = session.beginTransaction();
            session.save(cart);
    		transaction.commit();
        } catch (HibernateException ex) {
              logger.error(ex);
              throw new CartException("Database Connection Creation Failed during adding products inside cart"+ex.getMessage()); 
        } finally { 
              session.close();
        }
    }
/**
 * It returns a list of carts of a user based on user Id
 *
 * @param      userId        The user Id
 *
 * @return     List<Cart>    The list with user carts
 */
    public List<Cart> getCarts() throws CartException {
        List<Cart> carts = new ArrayList<Cart>();
        try{  
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
            session = databaseConnection.getSession(); 
            query = session.createQuery("FROM Cart");
            carts = query.list();
            return carts;
        } catch (HibernateException ex) {
              logger.error(ex);
              throw new CartException("Database Connection Creation Failed when tried to fetch cart(s) details"+ex.getMessage()); 
                                                                                                
        } finally { 
              session.close();
        }
    }

/**
 * It removes a cart
 *
 * @param         cartId         The cart Id
 *
 */
  public void removeCart(int cartId) throws CartException {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
	        session = databaseConnection.getSession();
	        Transaction transaction = session.beginTransaction();
            Cart cart = (Cart) session.get(Cart.class,cartId);
            session.delete(cart);
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
              logger.error(ex);
              throw new CartException("Database Connection Creation Failed while deleting cart : " 
                                                                +String.valueOf(cartId)+ex.getMessage());
        } finally { 
              session.close();
        }
    }

/**
 * It returns a cart based on Id
 *
 * @param         cartId        The cart Id
 *
 * @return        Cart          The cart object
 *
 */
  public Cart getCartById(int cartId) throws CartException {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
	        session = databaseConnection.getSession();
            Cart cart = (Cart) session.get(Cart.class,cartId);
            return cart;
        } catch (HibernateException ex) {
              logger.error(ex);
              throw new CartException("Database Connection Creation Failed while getting cart : " 
                                                        +String.valueOf(cartId)+ex.getMessage());
        } finally { 
              session.close();
        }
    }

/**
 * It modifies the quantity of a product in user's cart
 *
 * @param        cart         The cart to be updated
 */
    public void updateCart(Cart cart) throws CartException {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
	        session = databaseConnection.getSession();
	        Transaction transaction = session.beginTransaction();
            session.update(cart);
    		transaction.commit();
        } catch (HibernateException ex) {
              logger.error(ex);
              throw new CartException("Database Connection Creation Failed during modifying cart: "+ex.getMessage());
        } finally { 
              session.close();
        }
    }
}

