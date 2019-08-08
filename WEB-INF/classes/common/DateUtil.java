package common;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.Calendar;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;


public class DateUtil {

    static final DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

/**
 * It shows the current date and time;
 *
 * @return    String     current date and time
 *
 */
  public static String getCurrentDateTime() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String currentDateTime = dateFormat.format(date);
    return currentDateTime;
  }

/**
 * It returns current date and time object
 *
 * @return       dateTime        The current date time 
 *
 */     
  public static LocalDateTime getDate() {
    LocalDateTime dt = LocalDateTime.now();
    String date = dt.format(format); 
    LocalDateTime dateTime = LocalDateTime.parse(date,format);
    return dateTime;
  }

/**
 * It converts date in string format to Date format
 *
 * @param         dateString        The string with date
 *
 * @return        dateTime          The date object
 *
 */
  public static LocalDateTime convertStringToDate(String dateString) {
    String date = dateString.replace("T"," ");
    LocalDateTime dateTime = LocalDateTime.parse(date,format);
    return dateTime;
  }

}

