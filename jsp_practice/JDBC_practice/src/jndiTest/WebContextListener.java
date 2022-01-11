package jndiTest;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class WebContextListener implements ServletContextListener {
	private DataSource dataSource;
	
    public void contextDestroyed(ServletContextEvent sce)  { 
         
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext servletContext = sce.getServletContext();
    	
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/myJNDI");
		} catch (NamingException e) {
			e.printStackTrace();
		}
    	servletContext.setAttribute("datasource", dataSource);
    	System.out.println("DataSource Get ! DataSource = "+dataSource.toString());
    	
    }
}
