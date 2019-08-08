package dao;

import db.DatabaseConnection;

import model.Order;

import common.DateUtil;
import java.util.List;

import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException; 
import org.hibernate.Query;

import common.utils.userException.DatabaseConnectionException;
import common.utils.userException.OrderException;

import org.apache.log4j.Logger;
/**
 *<p>
 * It adds, removes and modifies order and products
 *</p>
 *
 * @author  karthik  created on June 2019
 *
 */

public class OrderDao {

    public Session session = null;
    public Query query;
    private static OrderDao instance = null;
    static final Logger logger = Logger.getLogger(OrderDao.class); 

/**
 * Private constructor
 */
    private OrderDao() {
        logger.info("Singleton of OrderDao");
    }

/**
 * It returns an instance of OrderDao class
 *
 */
    public static OrderDao getInstance() {
        if(null == instance) {
            logger.info("new OrderDao");
            instance = new OrderDao();
            return instance;
         }
      logger.info("old OrderDao");
      return instance;
    }

/**
 * It adds USER'S orders into a database
 *
 */
    public void addOrder(Order order) throws OrderException {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
	        session = databaseConnection.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(order);
    		transaction.commit();
        } catch (HibernateException ex) {
              throw new OrderException("Database Connection Creation Failed during adding orders "+ex.getMessage()); 
        } finally { 
              session.close();
        }
    }

/**
 * It retrives all user orders
 *
 * @return       List<Order>     The list of user orders
 *
 */
    public List<Order> getOrders() throws OrderException {
        List<Order> orders ;
        try{  
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
            session = databaseConnection.getSession();     
            query = session.createQuery("FROM Order");
            orders = query.list();
            return orders;
        } catch (HibernateException ex) {
              throw new OrderException("Database Connection Creation Failed when tried to fetch order: "+ex.getMessage()); 
        } finally { 
              session.close();
        }
    }

/**
 * It returns a particular order by its Id
 *
 * @param        orderId           The orderId
 *
 * @return       Order             The particular order
 *
 */
    public Order getOrderById(int orderId) throws OrderException {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
	        session = databaseConnection.getSession();
            Order order = (Order) session.get(Order.class,orderId);
            return order;
        } catch (HibernateException ex) {
              throw new OrderException("Database Connection Creation Failed while getting order : " 
                                                +String.valueOf(orderId)+ ex.getMessage());
        } finally { 
              session.close();
        }
    }

/**
 *It cancels a particular Order based on Id
 *
 * @param       orderId       The orlder Id to delete
 *
 */
    public void deleteOrder(Order order) throws OrderException {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
	        session = databaseConnection.getSession();
	        Transaction transaction = session.beginTransaction();
            session.update(order);
    		transaction.commit();
        } catch (HibernateException ex) {
              throw new OrderException("Database Connection Creation Failed during cancelling orders "+ex.getMessage()); 
        } finally { 
              session.close();
        }
    }
}
 	

