package oct.soft.dao;

import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import oct.soft.model.Office;

public class OfficeDAO {
	  private QueryRunner queryRunner;
	    private String sql = "";
	    
	    public OfficeDAO(DataSource dataSource) {
	    	queryRunner = new QueryRunner(dataSource);
		}
	    
	    public Office getOffice(int id) {
	        Office office = null;
	        try{
	            BeanHandler<Office> beanHandler = new BeanHandler<>(Office.class);
	            office = queryRunner.query("SELECT * FROM office where idoffice= ? AND isbranch = ?", beanHandler,id,0);
	            BranchDAO branchDAO = new  BranchDAO(queryRunner.getDataSource());
	            office.setBranch(branchDAO.getBranch(office.getParent()));
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        }
	        return office;
	    }
	    
	    public List<Office> officeList() {
	        List<Office> offices = new LinkedList<>();
	        try{
	        BeanListHandler<Office> beanListHandler = new BeanListHandler<>(Office.class);
	      
	        offices = queryRunner.query("SELECT * FROM office where isbranch=? ORDER BY NAME", beanListHandler,0);
	        BranchDAO branchDAO = new  BranchDAO(queryRunner.getDataSource());
	        offices.forEach(office ->{
	        	office.setBranch(branchDAO.getBranch(office.getParent()));
	        });
	        }catch(Exception ex) {
	            ex.printStackTrace();
	        }
	        return offices;
	    }
	    
	    public Office saveOrUpdate(Office office) {
	        
	        try{            
	            if(office.isNew()) {
	                sql = "INSERT INTO office(isbranch, name, parent) VALUES (0,?,?);";
	                BeanHandler<Office> beanHandler = new BeanHandler<>(Office.class);
	            queryRunner.insert(sql, beanHandler,office.getName(),office.getParent());
	            } else {
	                //update data
	            queryRunner.update("UPDATE office set name =?, parent=? WHERE idoffice = ?;", office.getName(), office.getParent(),office.getIdoffice());
	            }
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	        return office;
	    }
}
