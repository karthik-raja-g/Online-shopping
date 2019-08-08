package controller;

import model.Role;

import service.RoleService;

import common.InputUtil;
import common.DateUtil;

import constants.Constants;

import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuffer;

import common.utils.userException.RoleException;


/**
 *<p>
 * The Role controller adds new roles to the online shopping. Any one can
 * add specific roles. Every role has unique Id and name
 * 
 *</p>
 * 
 * @author   karthik   created on 22 June 2019
 *
 */
public class RoleController {

    public RoleService roleService = new RoleService();  

/**
 * It shows the various operations that can be performed for a role
 *
 */
    public void roleMenu()  {
        System.out.println(Constants.DECOR+"WELCOME TO ROLE MENU"+Constants.DECOR_END);
        int choice = InputUtil.getInt(Constants.ROLE_MENU);

        while(4 >= choice) {
            switch(choice) {
                case 1:
                    getRole();
                    break;
                case 2:
                    showRoles();
                    break;
                case 3:
                    modify();
                    break;
                case 4:
                    getRoleById();
                    break;
            }
            choice = InputUtil.getInt(Constants.ROLE_MENU);
            if(5 == choice) 
                break;
        }
    }
    
  
/**
 * It gets information about the role from the user. For each role,
 * a new object is created
 *
 */
    public void getRole()  {
        try {
            Role role = new Role();
            String roleName = InputUtil.getString("Enter role name : ");      
            role.setDescription(InputUtil.getString("Enter role description : "));
            role.setCreatedDate(DateUtil.getCurrentDateTime());
            role.setModifiedDate(DateUtil.getCurrentDateTime());
            roleService.checkRoleName(roleName);
            role.setRoleName(roleName);
            role.setIsActive(Boolean.TRUE);
            roleService.addRole(role);
            System.out.println("\n"+roleName+" Role added successfully");
        } catch (RoleException e) {
              System.out.println(e);
        }
    }

/** 
 * It displays all role name and corresponding details
 * 
 */
    public void showRoles() {
        List<Role> roles = new ArrayList<Role>();
        try {
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
        } catch (RoleException ex) {
          System.out.println(ex);
        }
    }
    

/**
 * It modifies role details
 *
 */
    public void modify()  {
        int roleId = InputUtil.getInt("Enter role Id to modify : ");
        try {
            Role role = roleService.getRoleById(roleId);
            String info = "Enter 1 to change role name. 2 to change description. 3 to delete . 4 to exit : ";
            int option = InputUtil.getInt(info);
            String newName;
            String newDescription;
            while(4 >= option) {
                switch(option) {
                    case 1:
                        newName = InputUtil.getString("Enter new name : ");
                        role.setRoleName(newName);
                        role.setModifiedDate(DateUtil.getCurrentDateTime());
                        System.out.println("\n\nNAME UPDATED FOR ROLE ID :"+String.valueOf(roleId));
                        break;

                    case 2:
                        newDescription = InputUtil.getString("Enter new description : ");
                        role.setDescription(newDescription);
                        role.setModifiedDate(DateUtil.getCurrentDateTime());
                        System.out.println("\n\nDESCRIPTION UPDATED FOR ROLE : "+role.getRoleName());
                        break;

                    case 3:
                        role.setIsActive(Boolean.FALSE);
                        role.setModifiedDate(DateUtil.getCurrentDateTime());
                        System.out.println("\n\n"+role.getRoleName()+" DEACTIVATED");
                        break;

                    default:
                        System.out.println("Enter valid choice..");
                        break;
                }
                option = InputUtil.getInt(info);
                if(4 == option)
                    break;
                }
                roleService.updateRole(role);
        } catch (RoleException exception) {
              System.out.println(exception);
        }
    }

/**
 * It displays a particular role
 *
 */
    public void getRoleById() {

        try {
            int roleId = InputUtil.getInt("Enter role Id : ");
            Role role = roleService.getRoleById(roleId);
            System.out.println(Constants.ROLE_HEADER);
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

        } catch (RoleException exception) {
              System.out.println(exception);
        }
    }
                
}





             

