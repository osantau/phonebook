/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oct.soft.dao;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import oct.soft.db.util.MyDataSource;
import oct.soft.model.Office;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 * @author osantau
 */
public class BranchDAO {
    private QueryRunner queryRunner;
    private String sql = "";
    public BranchDAO(DataSource dataSource) {
          queryRunner = new QueryRunner(dataSource);
    }
    
    public Office getBranch(int id) {
        Office office = null;
        try{
            BeanHandler<Office> beanHandler = new BeanHandler<>(Office.class);
            office = queryRunner.query("SELECT * FROM office where idoffice= ?", beanHandler,id);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return office;
    }
    public List<Office> branchList() {
        List<Office> branches = new LinkedList<>();
        try{
        BeanListHandler<Office> beanListHandler = new BeanListHandler<>(Office.class);
      
        branches = queryRunner.query("SELECT * FROM office where isbranch=? and parent=? ORDER BY name", beanListHandler,1,0);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return branches;
    }

    public Office saveOrUpdate(Office branch) {
        
        try{            
            if(branch.isNew()) {
                sql = "INSERT INTO office(isbranch, name, parent) VALUES (1,?,0);";
                BeanHandler<Office> beanHandler = new BeanHandler<>(Office.class);
            queryRunner.insert(sql, beanHandler,branch.getName());
            } else {
                //update data
            queryRunner.update("UPDATE office set name =? WHERE idoffice = ?;", branch.getName(), branch.getIdoffice());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return branch;
    }
       
}
