package controller;

import model.Product;

import service.ProductService;
import service.UserService;

import common.DateUtil;
import common.InputUtil;

import constants.Constants;

import java.util.ArrayList;
import java.util.List;

import common.utils.userException.DatabaseConnectionException;
import common.utils.userException.NoRecordFoundException;
import common.utils.userException.ProductException;


/**
 *<p>
 * It controls the overall operations performed on a product
 * New product can be created, given various specification, 
 * modified and even removed. A product can be searched based on 
 * the product Id
 *</p>
 *
 * @author   karthik   created on 21 June 2019
 *
 */ 
public class ProductController {
 

    public ProductService productService = new ProductService();
    public UserService userService = new UserService();
  
/**
 * It shows the user various opertaions that can be performed 
 * for a product
 *
 * @param      userId           The user Id of the person added the products
 *
 */ 
    public void productMenu(int userId) {
      System.out.println(Constants.DECOR+"WELCOME TO PRODUCT MENU"+Constants.DECOR_END);
        int choice = InputUtil.getInt(Constants.PRODUCT_MENU);
        while(4 >= choice) {
            switch(choice) {
                case 1:
                    getProductDetails(userId);
                    showProducts();
                    break;
 
                case 2:
                    modifyProduct();
                    break;

                case 3:
                    searchProductById();
                    break;

                case 4:
                    showProducts();
                    break;

            }
            choice = InputUtil.getInt(Constants.PRODUCT_MENU);
            if(5 == choice) {
                break;
            }
        }
    }

/**
 * It gets information about the product from the user 
 * 
 * @param      userId       The user id of creater
 *
 */
    public void getProductDetails(int userId) {
        try {
            Product product = new Product();
            String name = InputUtil.getString("Enter product name : ");
            product.setName(name);    
            product.setDescription(InputUtil.getString("Enter product description : "));
            product.setPrice(InputUtil.getInt("Enter product price : "));
            product.setUserId(userId);
            product.setSKU(productService.generateSKU(6));
            product.setStatus(Boolean.TRUE);
            product.setCreated(DateUtil.getCurrentDateTime());
            product.setModified(DateUtil.getCurrentDateTime());
            productService.validateProduct(name);
            product.setUser(userService.getUser(userId));
            productService.add(product);
            System.out.println("\n"+product.getName().toUpperCase()+" added successfully");
        } catch(ProductException ex) {
            System.out.println(ex);
        } catch (NoRecordFoundException ex) {
              System.out.println(ex);
        } catch (DatabaseConnectionException ex) {
              System.out.println(ex);
        }
    }


/**
 * It shows all the product details 
 *
 */
    public void showProducts() {
        try { 
            List<Product> products = productService.getProducts();
            System.out.println("************************************** STORE"  
                                +"*************************************************\n");
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
 * It searches for an item based on product Id. If found, the 
 * product details will be shown
 *
 */
    public void searchProductById() {
        try {
            int prodId = InputUtil.getInt("Enter product ID : ");
            productService.getProductById(prodId);
            showProduct(productService.getProductById(prodId));
        } catch (ProductException e) {
              System.out.println(e);
        }
    }

/**
 * It shows details of a particular product
 *
 * @param   product     The product to be displayed
 *
 */
    public void showProduct(Product product) {
        System.out.println(Constants.PRODUCT_HEADER);
        System.out.println((product.getName()).concat("\t\t").concat(product.getDescription()).concat("\t\t")
                        .concat(String.valueOf(product.getId()))
                        .concat("\t\t").concat(product.getSKU()).concat("\t").concat(String.valueOf(product.getPrice()))
                        .concat("\t").concat(String.valueOf(product.getMaxPrice())).concat("\t")
                        .concat(String.valueOf(product.getStatus()))
                        .concat("\t").concat(product.getCreated()).concat("\t\t").concat(product.getModified())
                        .concat("\t\t").concat(String.valueOf(product.getUser().getUserId())));
    }
  
  


/**
 * It modifies the details of the product. The ID,SKU and created
 * date cannot be modified
 *
 */
    public void modifyProduct() {
        try {
            int prodId = InputUtil.getInt("Enter product ID : ");
            Product product = productService.getProductById(prodId);
            String info = "Choose the following options of the product to modify\n"
                    .concat("\t1 -> Name").concat("\t2 -> Description").concat("\t3 -> Price")
                    .concat("\t4 -> Delete product").concat("\t5 -> Exit modification menu\n");

            int option = InputUtil.getInt(info);
            while(5 >= option) {
                switch(option) {
                    case 1:
                        product.setName(InputUtil.getString("Enter new name : "));
                        System.out.println("\n\nName updated for product Id : "+String.valueOf(prodId));
                        break;
                    case 2:
                        product.setDescription(InputUtil.getString("Enter new description : "));
                        System.out.println("\n\nDescription updated for : "+product.getName());
                        break;
                    case 3:
                        product.setPrice(InputUtil.getInt("Enter new price : "));
                        System.out.println("\n\nPrice updated for: "+product.getName());
                        break;
                    case 4:
                        product.setStatus(Boolean.FALSE);
                        System.out.println("\n\n"+product.getName()+"made unavailable");
                        break;
                    default :
                        System.out.println("Enter valid option..\n");
                        break;
                }
                option = InputUtil.getInt(info);
                if (5 == option) 
                    break;
            }
            product.setModified(DateUtil.getCurrentDateTime());
            productService.modify(product);
        } catch (ProductException e) {
              System.out.println(e);
        }
    }

/**
 * It returns a product for user's cart based on product id
 *
 * @param        prodId       The product Id
 *
 * @return       Product      The Product object
 *
 */
    public Product getProductForCart(int prodId) throws ProductException {
        return productService.getProductById(prodId);
    }
}




    

