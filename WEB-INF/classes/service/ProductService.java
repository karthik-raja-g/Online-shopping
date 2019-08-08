package service;

import model.Product;
import model.User;

import service.UserService;

import dao.ProductDao;

import java.lang.StringBuilder;
import java.util.List;

import common.DateUtil;
import common.utils.userException.ProductException;
import common.utils.userException.UserException;

import org.apache.log4j.Logger;


/**
 *<p>
 * The services provide business operations to the product.
 * It generates product Id and SKU for every product
 *</p>
 *
 * @author   karthik  created on 21 June 2019
 *
 */

public class ProductService {

    public ProductDao dao = ProductDao.getInstance();
    public UserService userService = UserService.getInstance();
    static final Logger logger = Logger.getLogger(ProductService.class); 
    private static ProductService instance = null;

/**
 * Private constructor
 */
    private ProductService() {
      logger.info("Singleton of ProductService");
    }

/**
 * It returns an instance of ProductService class
 *
 */
    public static ProductService getInstance() {
        if(null == instance) {
            logger.info("new ProductService");
            instance = new ProductService();
            return instance;
         }
      logger.info("old ProductService");
      return instance;
    }

/**
 * It generates a unique alphaprodIderic SKU for every product
 *
 * @return     String      The generated SKU
 *
 */
    private String generateSKU(int count) {
        String ALPHA_prodIdERIC_STRING = "A1B4C0D4E3F7G1H0I4J2K0L5M1N5O3P8Q8R4S0T5U3V7W1X4Y4Z";
        StringBuilder sku = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_prodIdERIC_STRING.length());
            sku.append(ALPHA_prodIdERIC_STRING.charAt(character));
            logger.info("New SKU created");
        }
        return sku.toString();
    }

/**
 * It passes the Product object to add to database
 *
 * @param    product    The product object
 * @param    user       The ccreator User 
 *
 */
    public void addProduct(Product product, User user) throws ProductException {
        product.setUser(user);
        product.setStatus(Boolean.TRUE);
        product.setSKU(generateSKU(6));
        product.setCreated(DateUtil.getDate());
        product.setModified(DateUtil.getDate());
        dao.addItems(product);
        logger.info("Product passed service");
    }

/**
 * It returns a product based on Id 
 *
 * @param      prodId       The product Id
 *
 * @return     product      The product
 *
 */
    public Product getProductById(int prodId) throws ProductException {
        if(null == dao.getProductById(prodId)) {
            logger.error("Product not found");
            throw new ProductException("Product not found for Id :"+String.valueOf(prodId));
        }
        return dao.getProductById(prodId);
    }

/**
 * It returns all the products available in a list
 *
 * @return     List<Product>      The list with all products
 *
 */
    public List<Product> getProducts() throws ProductException {
        return dao.getProducts();
    }

/**
 * It modifies the product information
 *
 * @param       product       The modifeid product
 * @param       userId        The creator Id
 *
 */
    public void updateProduct(Product product, int userId) throws ProductException,UserException {
        User user = userService.getUserById(userId);
        product.setUser(user);
        product.setModified(DateUtil.getDate());
        dao.updateProduct(product);
        logger.info("Updated Product passed service");
    }   

/**
 * It deletes a product by changing the availability
 *
 * @param       product       The modifeid product
 * 
 */
    public void deleteProduct(Product product) throws ProductException {
        product.setStatus(Boolean.FALSE);
        product.setModified(DateUtil.getDate());
        dao.updateProduct(product);
        logger.info("Product passed service for deletion");
    } 
}


