package oct.soft.servlet.listener;

import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import oct.soft.db.util.MyDataSource;

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
				ServletContext application = sce.getServletContext();
				application.setAttribute("dataSource", MyDataSource.getDataSource());
    	
		
    }
	
}
