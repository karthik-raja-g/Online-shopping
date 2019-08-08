package controller;

import model.Role;
import model.User;

import service.RoleService;
import service.UserService;

import common.DateUtil;
import common.InputUtil;

import constants.Constants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.utils.userException.DatabaseConnectionException;
import common.utils.userException.EmailException;
import common.utils.userException.NameException;
import common.utils.userException.NoRecordFoundException;
import common.utils.userException.PhoneException;
import common.utils.userException.RoleException;

import common.utils.validation.Validation;

/**
 *<p>
 * It controls the overall operations performed on a user
 * New user can be created, given various roles, 
 * modified and even removed. A user can be searched based on 
 * the user Id
 *</p>
 *
 * @author   karthik   created on 21 June 2019
 *
 */ 
public class UserController {
 
  
    public UserService userService = new UserService();
    public RoleService roleService = new RoleService();
  
/**
 * It shows the user various opertaions that can be performed 
 * for a user
 *
 */ 
  public void userMenu() {
      System.out.println(Constants.DECOR+"WELCOME TO USER MENU"+Constants.DECOR_END);
      int choice = InputUtil.getInt(Constants.USER_MENU);
      while(1 <= choice) {
          switch(choice) {
              case 1:
                  getUserDetails();
                  break;
 
              case 2:
                  showUsers();
                  break;

              case 3:
                  updateUser();
                  break;

              case 4:
                  searchById();
                  break;

              case 5:
                  showUserByStatus(Boolean.TRUE);
                  break;

              case 6:
                  showUserByStatus(Boolean.FALSE);
                  break;

              default :
                  System.out.println("\n\t\t******** PLEASE CHOOSE A CORRECT OPTION ********\n");

          }
          choice = InputUtil.getInt(Constants.USER_MENU);
          if(7 == choice) {
              System.out.println(Constants.DECOR+"THANK YOU"+Constants.DECOR_END);
              break;
          }
      }
  }

/**
 * It gets information about the  user
 *
 */
    public void getUserDetails() {
        try {
            Set<Role> roleSet= new HashSet<Role>();
            User user = new User();
            String name = InputUtil.getString("Enter name : ");
            String userName = InputUtil.getString("Enter user name : ");
            String password = InputUtil.getString("Enter password : ");
            String address = InputUtil.getString("Enter address : ");
            String email = InputUtil.getString("Enter email : ");
            long phone = InputUtil.getLong("Enter phone number : ");
            Validation.nameValidation(name);
            Validation.phoneValidation(phone);
            Validation.emailValidation(email);
            user.setName(name);
            user.setUserName(userName);
            user.setPassword(password);
            user.setAddress(address);
            user.setEmail(email);
            user.setPhone(phone);
            user.setIsActive(Boolean.TRUE);
            user.setCreated(DateUtil.getCurrentDateTime());
            user.setModified(DateUtil.getCurrentDateTime());
            addRoles(roleSet,name);
            if(roleSet.size() != 0) {
                user.setRoles(roleSet);
                userService.addUser(user);
                System.out.println("\n"+name+" Added successfully");
                return;
            }
            System.out.println("\nUser not added");
        } catch (DatabaseConnectionException ex) {
              System.out.println(ex);
        } catch(NameException ex) {
              System.out.println(ex);
        } catch(PhoneException ex) {
              System.out.println(ex);
        } catch(EmailException ex) {
              System.out.println(ex);
        } catch (RoleException e) {
            System.out.println(e);
        }
    }
/**
 * It adds roles to a user
 *
 * @param      roles       The set to add roles
 *
 */
    public void addRoles(Set<Role> roles,String name) throws DatabaseConnectionException, RoleException {
        System.out.println(Constants.DECOR + "AVAILABLE ROLES"+Constants.DECOR_END);
        showRoles();
        String info = "Enter 1 to add role(s). 2 to exit : ";
        int flag = InputUtil.getInt(info);
        try {
            while(1 == flag) {
                int roleId = InputUtil.getInt("Enter the role Id of the role you would like to have : ");
                roleService.checkRoleId(roleId);
                Role role = roleService.getRoleById(roleId);
                roles.add(role);
                System.out.println("\nRole : "+role.getRoleName()+" added to "+name);
                flag = InputUtil.getInt(info);
            }
        } catch (RoleException e) {
            System.out.println(e);
        }
    }
                 

/**
 * It shows all the user with details
 *
 */
    public void showUsers() {
        try {
            List<User> users = userService.getUsers();
            System.out.println("**************************************  USER  "
                                +"*************************************************\n");
            System.out.println(Constants.USER_HEADER);
            for (User user : users) {
                System.out.println((user.getName()).concat("\t\t").concat(String.valueOf(user.getUserId())).concat("\t\t    ")
                           .concat(user.getUserName())
                           .concat("\t\t").concat(user.getEmail()).concat("\t\t").concat(String.valueOf(user.getPhone()))
                           .concat("\t").concat(user.getAddress()).concat("\t\t").concat(String.valueOf(user.getIsActive()))
                           .concat("\t\t").concat(user.getCreated()).concat("\t").concat(user.getModified()).concat("\n"));
            }
            System.out.println(Constants.DECOR+"NEXT USER"+Constants.DECOR_END);
        } catch (DatabaseConnectionException ex) {
              System.out.println(ex);
        }
    }


/**
 * It shows details of a particular user
 *
 * @param   user     The user object
 *
 */
    public void showUser(User user) {

        System.out.println(Constants.USER_HEADER);
        System.out.println((user.getName()).concat("\t\t").concat(String.valueOf(user.getUserId())).concat("\t")
                           .concat(user.getUserName())
                           .concat("\t\t").concat(user.getEmail()).concat("\t\t").concat(String.valueOf(user.getPhone()))
                           .concat("\t").concat(user.getAddress()).concat("\t").concat(String.valueOf(user.getIsActive()))
                           .concat("\t\t").concat(user.getCreated()).concat("\t").concat(user.getModified()).concat("\n"));

    }
  
  

/**
 * It shows all active user with details
 *
 * @param    isActive     The active status of user
 *
 */
    public void showUserByStatus(Boolean isActive) {
        try {
            List<User> users = userService.getUsers();
            System.out.println("**************************************USERS*************************************************\n");
            System.out.println(Constants.USER_HEADER);
            for(User user : users) {
                if(isActive == user.getIsActive()) {
                    System.out.println((user.getName()).concat("\t\t").concat(String.valueOf(user.getUserId())).concat("\t")
                           .concat(user.getUserName())
                           .concat("\t\t").concat(user.getEmail()).concat("\t\t").concat(String.valueOf(user.getPhone()))
                           .concat("\t").concat(user.getAddress()).concat("\t").concat(String.valueOf(user.getIsActive()))
                           .concat("\t\t").concat(user.getCreated()).concat("\t").concat(user.getModified()).concat("\n"));
                }
            }
                
        } catch (DatabaseConnectionException ex) {
              System.out.println(ex);
        }
    }

/**
 * It searchByIdes for an user based on user Id. If found, the 
 * user details will be shown
 *
 */
    public void searchById() {
        try {
            int userId = InputUtil.getInt("Enter user ID : ");
            showUser(userService.getUser(userId));
        } catch (NoRecordFoundException ex) {
              System.out.println(ex);
        } catch (DatabaseConnectionException ex) {
              System.out.println(ex);
        }
    }
    

/** 
 * The showDetail method displays all roles created
 */
    public void showRoles() throws RoleException  {
        List<Role> roles = new ArrayList<Role>();
        roles = roleService.getRoles();
        System.out.println(Constants.ROLE_HEADER);
        for(Role role : roles) {
            StringBuffer str = new StringBuffer(String.valueOf(role.getRoleId()));
            str.append("\t\t");
            str.append(String.valueOf(role.getRoleName()));
            str.append("\t\t");
            str.append(role.getDescription());
            str.append("\t\t\t");
            str.append(role.getIsActive());
            str.append("\t\t\t");
            str.append(role.getCreatedDate());
            str.append("\t\t");
            str.append(role.getModifiedDate());
            System.out.println(str);
        }
    }

/**
 * It checks if an user is present in the database or not
 *
 * @param        userId        The user Id
 *
 */
    public void checkUserId(int userId) throws NoRecordFoundException {
        try {
            userService.getUser(userId);
        } catch (DatabaseConnectionException ex) {
             System.out.println(ex);
        }
    }

/**
 * It modifies the details of the user. The ID and created
 * date cannot be modified
 *
 */
    public void updateUser() {
        try {
            int userId = InputUtil.getInt("Enter user Id : ");
            User user = userService.getUser(userId);
            String info = "Choose the following options of the user to modify\n"
                     .concat("\t1 -> Name").concat("\t2 -> User name").concat("\t3 -> Password")
                     .concat("\n\t4 -> Email Id").concat("\t5 -> Phone").concat("\t6 -> Address")
                     .concat("\n\t7 -> Delete user").concat("\t8 -> Exit modification menu\n");

            int option = InputUtil.getInt(info);
            while(8 >= option) {
                switch(option) {
                    case 1:
                        user.setName(InputUtil.getString("Enter new name : "));
                        System.out.println("\n\nName updated for userId : "+String.valueOf(userId));
                        break;

                    case 2:
                        user.setUserName(InputUtil.getString("Enter new user name : "));
                        System.out.println("\n\nUsername updated for : "+user.getName());
                        break;

                    case 3:
                        user.setPassword(InputUtil.getString("Enter new password : ")); 
                        System.out.println("\n\nPassword updated for : "+user.getName());
                        break;
 
                    case 4:
                        user.setEmail(InputUtil.getString("Enter new Email Id : "));
                        System.out.println("\n\nEmail updated for : "+user.getName());
                        break;

                    case 5:
                        user.setPhone(InputUtil.getLong("Enter new phone number : ")); 
                        System.out.println("\n\nPhone number updated for : "+user.getName());
                        break;

                    case 6:
                        user.setAddress(InputUtil.getString("Enter new address : "));
                        System.out.println("\n\nAddress updated for : "+user.getName());
                        break;

                    case 7:
                        user.setIsActive(Boolean.FALSE);
                        System.out.println("\n\n"+user.getName()+" made inactive");
                        break;

                    default :
                        System.out.println("Enter valid option..\n");
                        break;

                }
                option = InputUtil.getInt(info);
                if (8 == option) 
                    break;
            }
            user.setModified(DateUtil.getCurrentDateTime());
            userService.updateUser(user);
        } catch (DatabaseConnectionException ex) {
              System.out.println(ex);
        } catch (NoRecordFoundException e) {
              System.out.println(e);
        }
    }

}








  

