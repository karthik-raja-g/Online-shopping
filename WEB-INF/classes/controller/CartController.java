package controller;

import model.Cart;
import model.Product;

import service.CartService;
import service.ProductService;
import service.UserService;

import common.DateUtil;
import common.InputUtil;

import constants.Constants;

import java.util.ArrayList;
import java.util.List;

import common.utils.userException.CartException;
import common.utils.userException.DatabaseConnectionException;
import common.utils.userException.NoRecordFoundException;
import common.utils.userException.ProductException;

/**
 *<p>
 * It can add new items to cart, remove them, modify
 * them and show total value of the items added
 *</p>
 *
 * @author karthik  created on 18 June 2019
 *
 */
public class CartController {



    public CartService cartService = new CartService();
    public ProductService productService = new ProductService();
    public UserService userService = new UserService();

/**
 * It provides various opertions for the user to perform. These include
 * adding new items, removing items and modifying the items
 * 
 * @param     userId       The user Id of cart owner
 *
 */
    public void cartMenu(int userId) {
      System.out.println(Constants.DECOR+"WELCOME TO CART MENU"+Constants.DECOR_END);
        int choice = InputUtil.getInt(Constants.CART_MENU);
        while(4 >= choice) {
            switch(choice) {
                case 1:
                    showAllProducts();
                    getCartProducts(userId);
                    showCart(userId);
                    break;

                case 2:
                    removeCart(userId);
                    showCart(userId);
                    break;
        
                case 3:
                    modifyCart(userId);
                    showCart(userId);
                    break;
            
                case 4:
                    showCart(userId);
                    break;

            }
            choice = InputUtil.getInt(Constants.CART_MENU);
            if(5 == choice) {
                showCart(userId);
                break;
            }
        }    
    }

/**
 * It adds products from the store into user's cart
 * 
 * @param       userId       The Id of user 
 *
 */
    public void getCartProducts(int userId) {
        try {
            int flag = 1;
            while(1 == flag) {
                int prodId = InputUtil.getInt("Enter product Id : ");
                Product product = cartService.getProductFromStore(prodId);
                if(!cartService.validateProduct(userId,prodId)) {
                    System.out.println("Product added already in cart\n");  
                    break;
                }
                Cart cart = new Cart();
		        cart.setProduct(product); 
                cart.setUser(userService.getUser(userId));
		        int quantity = InputUtil.getInt("Enter product quantity : ");           
                cart.setQuantity(quantity);
                cart.setCreated(DateUtil.getCurrentDateTime());
                cart.setModified(DateUtil.getCurrentDateTime());
                cartService.add(cart);
                System.out.println("\n"+product.getName().toUpperCase()+" added to cart ");
                flag = InputUtil.getInt("Enter 1 to add products. 2 to exit : ");
            }
        } catch (ProductException e) {
	          System.out.println(e);
        } catch (DatabaseConnectionException e) {
	          System.out.println(e);
	    } catch (NoRecordFoundException ex) {
              System.out.println(ex);
        } catch (CartException ex) {
              System.out.println(ex);
        } 
    }
    

/**
 * It shows the items added in the cart with details
 *
 * @param     userId      The Id of a uaer
 *
 */
    public void showCart(int userId) {
        try {
            List<Cart> carts = cartService.getUserCarts(userId);
            System.out.println("\n"+Constants.DECOR+"YOUR CART"+Constants.DECOR_END);
            System.out.println(Constants.CART_HEADER);
            for(Cart cart : carts) {
                System.out.println(String.valueOf(userId)
                           +"\t\t"+String.valueOf(cart.getCartId())+("\t\t"+String.valueOf(cart.getProduct().getId())
                           +"\t\t"+String.valueOf(cart.getQuantity())+"\t\t"+cart.getCreated()+"\t\t"+cart.getModified()));
            }
        } catch (CartException ex) {
              System.out.println(ex);
        } 
    }


/**
 * It removes a particular cart based on its Id
 *
 */
    public void removeCart(int userId) {
        try {
            int cartId = InputUtil.getInt("Enter cart Id : ");
            Cart cart = cartService.getCart(cartId);
            cartService.checkUserCart(cart,userId);
            cartService.removeCart(cartId);
            System.out.println(Constants.DECOR+"CART REMOVED : "+String.valueOf(cartId)+Constants.DECOR_END);
        } catch (CartException ex) {
              System.out.println(ex);
        } 
    }

/**
 * It modifies an item inside the cart.
 *
 * @param      userId        The userId of user
 * 
 */
    public void modifyCart(int userId) {
        try {
            int cartId = InputUtil.getInt("Enter cart Id : ");
            int newQuantity = InputUtil.getInt("Enter new quantity: ");
            Cart cart = cartService.getCart(cartId);
            cartService.checkUserCart(cart,userId);
            cartService.modifyItem(cart,userId,newQuantity);
            System.out.println("\nProduct quantity changed for cart Id  : "+String.valueOf(cartId));
        } catch (CartException ex) {
              System.out.println(ex);
        }    
    }


/**
 * It shows all the available products in store 
 *
 */
    public void showAllProducts() {
        try {
            System.out.println(Constants.DECOR+"ALL PRODUCTS IN STORE"+Constants.DECOR_END);
            List<Product> products = productService.getProducts();
            System.out.println(Constants.PRODUCT_HEADER);
            for (Product product : products) {                               
                System.out.println((product.getName()).concat("\t\t").concat(product.getDescription()).concat("\t\t")
                           .concat(String.valueOf(product.getId()))
                           .concat("\t\t").concat(product.getSKU()).concat("\t").concat(String.valueOf(product.getPrice()))
                           .concat("\t").concat(String.valueOf(product.getMaxPrice())).concat("\t")
                           .concat(String.valueOf(product.getStatus()))
                           .concat("\t").concat(product.getCreated()).concat("\t\t").concat(product.getModified())
                           .concat("\t\t").concat(String.valueOf(product.getUser().getUserId())));
            }
        } catch (ProductException ex) {
              System.out.println(ex);
        }
    }

/**
 * It checks if a user has active carts or not
 *
 * @param      userId      The user Id of user
 *
 */
    public void checkCarts(int userId) throws CartException {
        cartService.getUserCarts(userId);   
    }
}



