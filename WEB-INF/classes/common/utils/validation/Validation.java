package common.utils.validation;

import common.utils.userException.CartException;
import common.utils.userException.EmailException;
import common.utils.userException.NameException;
import common.utils.userException.PhoneException;
import common.utils.userException.ProductException;
import common.utils.userException.RoleException;

import model.Cart;
import model.Product;
import model.Role;
import java.util.List;

import java.util.regex.Matcher; 
import java.util.regex.Pattern; 

/**
 *<p>
 * It consists of number of validations required for the
 * online shopping module
 *</p>
 *
 * @author     karthik     created on 1 July 2019
 *
 */
public class Validation {


/**
 * It checks if an email Id is valid or not
 *
 * @param        email          The email Id to be validated
 *
 */
  public static String emailValidation(String email) throws EmailException {

    String emailFormat = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
    Pattern pattern = Pattern.compile(emailFormat); 
      if(!pattern.matcher(email).matches())
        throw new EmailException("******Invalid Email Id *******\n");
      return email;
    
  }
/**
 * It checks if a phone number is valid or not
 *
 * @param        phone          The number to be validated
 *
 */
  public static long phoneValidation(long phone) throws PhoneException {

    String phoneFormat = Long.toString(phone);
    Pattern pattern = Pattern.compile("(0/91)?[7-9][0-9]{9}");   
    Matcher matcher = pattern.matcher(phoneFormat);
    if(matcher.find() && matcher.group().equals(phoneFormat))
      return phone;
    else
      throw new PhoneException("*******Invalid Phone number*******\n");
  }   

/**
 * It checks if a name is valid or not
 *
 * @param         name           The name to be verified
 *
 */
  public static String nameValidation(String name) throws NameException {
    String nameFormat = "^[a-zA-Z]*$";
    if(name!= null && (!name.equals("") && name.matches(nameFormat)))
      return name;
    else
      throw new NameException("*******Invalid name*******\n");
       
  }

/**
 * It checks if a product is being created again
 *
 * @param      products         The list with all products
 * @param      name             The product name
 *
 */
  public static String productValidation(List<Product> products, String prodName) throws ProductException {
    if(0 == products.size())
      return prodName;
    else {
      for(Product product : products) {
        if(prodName.equalsIgnoreCase(product.getName()))
          throw new ProductException("Product added already valid");
      }
    }
    return prodName;
  }

/**
 * It checks if a product is already inside user's carts
 *
 * @param        carts            The list with all carts
 * @param        prodId           Id of product to be checked
 * @param        userId           The Id of user
 *
 */
  public static void productValidationInCart(List<Cart> carts, int prodId, int userId) throws CartException {
    if(0 == carts.size())
      return;
    else {
      for(Cart cart : carts) {
        if(cart.getUserId() == userId && cart.getProduct().getId() == prodId)
          throw new CartException("Product added already");
      }
    }
  } 
}
    

  
