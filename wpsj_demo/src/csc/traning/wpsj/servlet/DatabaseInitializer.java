package csc.traning.wpsj.servlet;

import javax.servlet.*;

import csc.traning.wpsj.dao.EmbeddedDbCreator;


/**
 * 
 * @author dangthehuynh
 *
 */
public class DatabaseInitializer implements ServletContextListener {
  public void contextInitialized(ServletContextEvent event) {
    try {
		new EmbeddedDbCreator().createDatabase();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  public void contextDestroyed(ServletContextEvent event) {}
}
