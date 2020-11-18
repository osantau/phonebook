/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oct.soft.db.util;

import java.beans.PropertyVetoException;

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
				datasource.setDriverClass("com.mysql.cj.jdbc.Driver");
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			datasource.setJdbcUrl("jdbc:mysql://localhost/telefoane?useSSL=false");
			datasource.setUser("root");
			datasource.setPassword("root");

			// Optional Settings
			datasource.setInitialPoolSize(5);
			datasource.setMinPoolSize(5);
			datasource.setAcquireIncrement(5);
			datasource.setMaxPoolSize(20);
			datasource.setMaxStatements(100);
			datasource.setPreferredTestQuery("SELECT 1");					
		}

		return datasource;
	}
}
