package constants;

/**
 *<p>
 * It contains all the constant string used in the online shopping module
 *</p>
 *
 * @author          Karthik  created on 10 July 2019
 *
 */ 

public class Constants {

    public static final String DECOR = "\n\t\t###############################   ";
    public static final String DECOR_END = "   ##########################\n";

    public static final String ROLE_HEADER = "ROLE ID\t\tROLE NAME\t\tDESCRIPTION\t\tIS ACTIVE\t\tDATE CREATED\t\tDATE MODIFIED";
    public static final String USER_HEADER = "NAME\t\tUSER ID\t\tUSER NAME\t\tEMAIL ID\t\tPHONE\t\tADDRESS\t\tIS ACTIVE\t\tDATE" 
                                                                    +"CREATED\t\tDATE MODIFIED\n";
    
    public static final String PRODUCT_HEADER = "PRODUCT NAME\tDESCRIPTION\tPRODUCT ID\tSKU\tPRICE\tMRP\tIS AVAILABLE\tDATE" 
                                                                            +"CREATED\tDATE MODIFIED\t\tCREATER ID\n";
                             
  
    public static final String CART_HEADER = "USER ID\t\tCART ID\t\tPRODUCT ID\t\tQUANTITY\t\tCREATED\t\tMODIFIED\n";
    public static final String ORDER_HEADER = "ORDER ID \t USER ID \t TOTAL AMOUNT \t STATUS\n";
    public static final String ORDER_DETAILS_HEADER = "ID\t\tPRODUCT NAME\t\tPRODUCT ID\t\tPRICE\t\tQUANTITY\tORDER ID\n";


    public static final String ROLE_MENU = "\n\t\tSelect your preffered choice by pressing:\n"
                    .concat("\t\t 1 -> Add roles\n")
                    .concat("\t\t 2 -> View all roles\n")
                    .concat("\t\t 3 -> Modify role\n")
                    .concat("\t\t 4 -> Search role by Id\n")
                    .concat("\t\t 5 -> Exit role menu\n");

    public static final String USER_MENU = "\n\t\tChoose your prefered option by pressing the corresponding number \n"
                    .concat("\n\t\t 1 -> Create new user\n")
                    .concat("\t\t 2 -> View all users \n")
                    .concat("\t\t 3 -> Modify user details\n")
                    .concat("\t\t 4 -> search an user by userId\n")
                    .concat("\t\t 5 -> View all active users \n") 
                    .concat("\t\t 6 -> View all inactive users \n")
                    .concat("\t\t 7 -> Exit \n");

    public static final String PRODUCT_MENU = "\n\t\t Choose your prefered option:"
                    .concat("\n\t\t 1 -> Add a product\n")
                    .concat("\t\t 2 -> Modify a product\n")
                    .concat("\t\t 3 -> Search a product\n")
                    .concat("\t\t 4 -> view all products \n")
                    .concat("\t\t 5 -> Exit\n");

    public static final String CART_MENU = "Enter your prefered choice : "
                    .concat("\n\t\t\t 1 -> Add items\n\t\t\t 2 -> Remove items\n\t\t\t 3 -> Modify item\n\t\t\t")
                    .concat(" 4 -> Show cart\n\t\t\t 5 -> finalize the cart \n");
    
    public static final String ORDER_MENU = " \n\t Enter your preferred choice \n" + "\t 1 -> Show carts\n"
                      +"\t 2 -> Create new order" 
                      +"\n\t 3 -> Cancel order"
                      +"\n\t 4 -> Show all orders"
                      +"\n\t 5 -> Show order details"
                      +"\n\t 6 -> Exit\n";


}
