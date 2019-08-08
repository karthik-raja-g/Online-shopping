package db;

import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException; 

public class DatabaseConnection {

    private static DatabaseConnection instance = null;
    private static SessionFactory sessionFactory;
    private static Session session;

    private DatabaseConnection() {
        try {
            sessionFactory = new Configuration().configure("/configuration/hibernate.cfg.xml").buildSessionFactory();
             
        } catch (HibernateException ex) {
              System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public static DatabaseConnection getInstance() throws HibernateException {
      if (instance == null) {
          instance = new DatabaseConnection();
      } else if (instance.getSessionFactory().isClosed()) {
          instance = new DatabaseConnection();
      }

      return instance;
    }
    

    public static SessionFactory getSessionFactory() throws HibernateException {
        return sessionFactory;
    }
  
    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
     
    }
}   
