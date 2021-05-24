package oct.soft.servlet.listener;

import java.beans.PropertyVetoException;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Application Lifecycle Listener implementation class MyServletListener
 *
 */
@WebListener
public class MyServletListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MyServletListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  {
    	System.out.println("Deinitializare DS");
    	ServletContext application = sce.getServletContext();
    	ComboPooledDataSource dataSource = (ComboPooledDataSource) application.getAttribute("dataSource");
    	
    	try {
    		Connection conn = dataSource.getConnection();
    		if(conn!=null) {
    			conn.close();
    		}
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  {
    
    	System.out.println("Initializare DS");
			try {
				ServletContext application = sce.getServletContext();

				ComboPooledDataSource datasource = new ComboPooledDataSource();
				datasource.setDriverClass("com.mysql.cj.jdbc.Driver");
				datasource.setJdbcUrl("jdbc:mysql://localhost/telefoane?useSSL=false");
				datasource.setUser("root");
				datasource.setPassword("root");

				// Optional Settings
				
				datasource.setMinPoolSize(10);			
				datasource.setMaxPoolSize(20);
				datasource.setAcquireIncrement(1);
				datasource.setMaxStatements(50);
				datasource.setIdleConnectionTestPeriod(3000);
				datasource.setPreferredTestQuery("select version()");

				application.setAttribute("dataSource", datasource);
			} catch (PropertyVetoException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
	
    	
		
    }
	
}
