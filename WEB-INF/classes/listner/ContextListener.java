package com.ideas2it.OnlineShopping;

import java.io.File;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.xml.DOMConfigurator;
  
public class ContextListener implements ServletContextListener {
 
/**
 * Initialize log4j when the application is being started
 */
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
        DOMConfigurator.configure(fullPath);
         
    }

}
