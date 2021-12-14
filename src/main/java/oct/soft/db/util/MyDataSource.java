/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oct.soft.db.util;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 *
 * @author osantau
 */
public class MyDataSource {
	private static ComboPooledDataSource datasource = null;

	public static DataSource getDataSource() {
		if (datasource == null) {
			datasource = new ComboPooledDataSource();
			try {
				datasource.setDriverClass("com.mysql.jdbc.Driver");
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			datasource.setJdbcUrl("jdbc:mysql://localhost/phonebook");
			datasource.setUser("root");
			datasource.setPassword("root");

			// Optional Settings
			
			datasource.setMinPoolSize(10);			
			datasource.setMaxPoolSize(20);
			datasource.setAcquireIncrement(1);
			datasource.setMaxStatements(50);
			datasource.setIdleConnectionTestPeriod(3000);
			datasource.setPreferredTestQuery("select version()");			
		}

		return datasource;
	}
	
	public static DataSource getDataSource(Properties dbConfig) {
		if (datasource == null) {
			datasource = new ComboPooledDataSource();
			try {
				datasource.setDriverClass(dbConfig.getProperty("JDBC_DRIVER"));
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			datasource.setJdbcUrl(dbConfig.getProperty("JDBC_URL"));
			datasource.setUser(dbConfig.getProperty("JDBC_USER"));
			datasource.setPassword(dbConfig.getProperty("JDBC_PASSWORD"));

			// Optional Settings
			
			datasource.setMinPoolSize(10);			
			datasource.setMaxPoolSize(20);
			datasource.setAcquireIncrement(1);
			datasource.setMaxStatements(50);
			datasource.setIdleConnectionTestPeriod(3000);
			datasource.setPreferredTestQuery("select version()");			
		}

		return datasource;
	}
}
