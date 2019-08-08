package service;

import model.Cart;
import model.Product;
import model.User;

import service.ProductService;
import service.UserService;

import dao.CartDao;

import java.util.ArrayList;
import java.util.List;

import common.DateUtil;
import common.utils.userException.CartException;
import common.utils.userException.ProductException;
import common.utils.userException.UserException;

import org.apache.log4j.Logger;

/**
 *<p>
 * It can perform business logics on items. It provides SKU number 
 * for every product. It calculates the total value of the cart
 *</p>
 *
 * @author   karthik   created on 19 June 2019
 *
 */
public class CartService {

    public CartDao dao = CartDao.getInstance();
    public ProductService productService = ProductService.getInstance();
    public UserService userService = UserService.getInstance();
    private static CartService instance = null;
    static final Logger logger = Logger.getLogger(CartService.class); 

/**
 * Private constructor
 */
    private CartService() {
      logger.info("Singleton of CartService");
    }

/**
 * It returns an instance of CartService class
 *
 */
    public static CartService getInstance() {
        if(null == instance) {
            logger.info("new CartService");
            instance = new CartService();
            return instance;
         }
      logger.info("old CartService");
      return instance;
    }

/**
 * It passes the Cart object to add to database
 *
 * @param    cart     The cart object.
 * @param    user     The user Object
 * @param    prodId   The product Id
 *
 */
    public void addCart(Cart cart,User user, int prodId) throws CartException,ProductException {
        Product product = productService.getProductById(prodId);
        checkProductInCart(user,prodId);
        cart.setProduct(product);
        cart.setUser(user);
        cart.setCreated(DateUtil.getDate());
        cart.setModified(DateUtil.getDate());
        dao.addCart(cart);
    }

/**
 * It returns a list of user carts
 *
 * @param       userId         The user Id
 *
 * @return      List<Cart>     The list of user carts
 *
 */
    public List<Cart> getUserCarts(int userId) throws CartException {
        List<Cart> allCarts = dao.getCarts();
        List<Cart> carts = new ArrayList<Cart>();
        allCarts.forEach(cart -> {
            if(userId == cart.getUser().getUserId())
                carts.add(cart);             
             });
         return carts;  
    }


/**
 * It makes a particular product and cart unavailable in user's carts
 *
 * @param        cartId       The cart Id 
 *
 */
    public void removeCart(int cartId) throws CartException {
        dao.removeCart(cartId);
    }

/**
 * It changes the quantity of a product in user's cart
 *
 * @param    cart          The cart object
 * @param    userId        The user Id
 * @param    prodId        The product Id of product in cart
 *
 */
    public void updateCart(Cart cart,int userId,int prodId) throws CartException,ProductException,UserException {
        User user = userService.getUserById(userId);
        Product product = productService.getProductById(prodId);
        cart.setUser(user);
        cart.setProduct(product);
        cart.setModified(DateUtil.getDate());
        dao.updateCart(cart);
    }

/**
 * It verifies if a product is already inside any of user carts 
 *
 * @param           user           The  user Id
 * @param           prodId         The product Id to be checked
 *
 *   
 */
    private void checkProductInCart(User user, int prodId) throws CartException,ProductException {
        List<Cart> carts = getUserCarts(user.getUserId());
        if(0 == carts.size())
            return;
        for(Cart cart : carts) {
            if(cart.getProduct().getId() == prodId && cart.getUser().getUserId() == user.getUserId()) {
                throw new ProductException("Product already in one of your cart");
            }
        }  
    }

/**
 * It returns a particular cart based on  Id
 *
 * @param      cartId        The cartId
 *
 * @return     Cart          The particular cart
 *
 */
    public Cart getCartById(int cartId) throws CartException {
        Cart cart = dao.getCartById(cartId);
        if(cart == null) 
            throw new CartException("Cart not found for Id : "+String.valueOf(cartId));
        return cart;
    }
}

