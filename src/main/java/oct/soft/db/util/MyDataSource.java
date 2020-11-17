/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oct.soft.db.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author osantau
 */
public class MyDataSource {
    private static DataSource ds = null;
    
    public static DataSource getDataSource(){
        try {
            Context initialContext = new InitialContext();
            Context envContext = (Context)initialContext.lookup("java:/comp/env");
            if(ds==null) {
                ds = (DataSource) envContext.lookup("jdbc/telefoane");
            }            
           
        } catch (NamingException ex) {
            Logger.getLogger(MyDataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
         return ds;
    }
}
