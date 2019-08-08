package dao;

import db.DatabaseConnection;

import model.Product;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException; 
import org.hibernate.Query;

import common.utils.userException.ProductException;

import org.apache.log4j.Logger;
/**
 *<p>
 * It handles all the database related operations of a product. New products
 * can be added,deleted,modified,exixting products can be retrived
 *</p>
 *
 * @author         karthik  created on 22 June 2019
 *
 */

public class ProductDao {

    public Session session = null;
    public Query query;
    static final Logger logger = Logger.getLogger(ProductDao.class);
    private static ProductDao instance = null; 

/**
 * Private constructor
 */
    private ProductDao() {
        logger.info("Singleton of ProductDao");
    }

/**
 * It returns an instance of ProductDao class
 *
 */
    public static ProductDao getInstance() {
        if(null == instance) {
            logger.info("new ProductDao");
            instance = new ProductDao();
            return instance;
         }
      logger.info("old ProductDao");
      return instance;
    }
 

/**
 * It adds a product information to the List
 *
 * @param   Product       The poduct to be added added
 *
 */
    public void addItems(Product product) throws ProductException {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
	        session = databaseConnection.getSession(); 
	        Transaction transaction = session.beginTransaction();
            session.save(product);
    		transaction.commit();
        } catch (HibernateException ex) {
              logger.error(ex);
              throw new ProductException("Database Connection Creation Failed during adding products"+ex.getMessage()); 
        } finally { 
              session.close();
        }
    }


/**
 *
 * It creates a list of all users or a particular user in the database and returns the list
 *
 * @return             List<product>         The list of all users
 *
 */

    public List<Product> getProducts() throws ProductException {
        List<Product> products = new ArrayList<Product>();
        try{  
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
            session = databaseConnection.getSession();    
            query = session.createQuery("FROM Product");
            products = query.list();
            return products;
        } catch (HibernateException ex) {
              logger.error(ex);
              throw new ProductException("Database Connection Creation Failed when tried to fetch product details"+ex.getMessage());
        } finally { 
              session.close();
        }
    }

/**
 * It returns a particular prouct based on Id 
 *
 * @param       prodId        The product Id
 * 
 */
    public Product getProductById(int prodId) throws ProductException {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
	        session = databaseConnection.getSession();
            Product product = (Product) session.get(Product.class,prodId);
            return product;
        } catch (HibernateException ex) {
              logger.error(ex);
              throw new ProductException("Database Connection Creation Failed while getting product : "      
                                        +String.valueOf(prodId) +ex.getMessage());
        } finally { 
              session.close();
        }
    }


/**
 * It modifies the product information
 *
 * @param       product       The product to be modifeid
 *
 */
    public void updateProduct(Product product) throws ProductException {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
	        session = databaseConnection.getSession();
	        Transaction transaction = session.beginTransaction();
            session.update(product);
    		transaction.commit();
        } catch (HibernateException ex) {
              logger.error(ex);
              throw new ProductException("Database Connection Creation Failed during modifying products"+ex.getMessage()); 
        } finally { 
              session.close();
        }
    }
}


