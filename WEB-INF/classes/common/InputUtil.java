package common;

import java.util.Scanner;

/**
 * It can get input from user and return them according
 * to their data type
 *
 */

public class InputUtil {

  public static Scanner scanner = new Scanner(System.in);

/**
 * It accepts integer input and returns the input
 * @param     message   The message to user
 * @return    int       The user input
 *
 */ 
  public static int getInt(String message) {
    System.out.print(message);
    while(true) {
      try {  
        return scanner.nextInt();
      }
      catch (java.util.InputMismatchException e) {
       System.out.println("****** Enter an integer ******\n");
       scanner.nextLine();
      }
    }
  }
        
  
/**
 * It accepts string input and returns the input
 * @param     messge       The messgae to user
 * @return    String       The user input
 *
 */ 
  public static String getString(String message) {
    System.out.print(message);
    return scanner.next();
  }

/**
 * It gets a long input from user and returns it
 * @param        message       The message for user
 * @return       long          The user input
 *
 */
  public static long getLong(String message) {
    System.out.print(message);
    while(true) {
      try {
        return scanner.nextLong();
      }
      catch (java.util.InputMismatchException e) {
       System.out.println("***** Enter an integer ******\n");
       scanner.nextLine();
      }
    }
  }
}
