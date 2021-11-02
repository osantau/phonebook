package oct.soft.dao;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	    
	    public Map<Office, List<Office>> getBranchOfficeChilds(int idperson)
	    {	    		    	
	    	Map<Office, List<Office>> data = new LinkedHashMap<Office, List<Office>>();	    	
	    	BranchDAO branchDAO = new BranchDAO(queryRunner.getDataSource());
	    	List<Office> branches =branchDAO.branchList();	    		   	    	
	    	List<Office> personOffices = getOfficesByPerson(idperson);
	    	branches.forEach(branch ->{	    	    		
	    		List<Office> tempList = branchDAO.getOfficesByBranch(branch.getIdoffice());
	    		tempList.removeAll(personOffices);
	    		data.put(branch,tempList);
	    	});
	    	return data;
	    }
	    
	    public List<Office> getOfficesByPerson(int idperson) 
	    {
	    	sql = "select * from office where idoffice in (select idoffice from offices_person where idperson=?) ORDER BY name";	    	
	    	List<Office> data = new LinkedList<>();
	    	BeanListHandler<Office> beanListHandler = new BeanListHandler<>(Office.class);
	    	try {
	    		data =  queryRunner.query(sql, beanListHandler,idperson); 
	    		BranchDAO branchDAO = new BranchDAO(queryRunner.getDataSource());
	    		data.forEach(o ->{
	    			o.setBranch(branchDAO.getBranch(o.getParent()));
	    		});
	    		
	    	}catch (Exception e) {
	    		e.printStackTrace();
			}
	    	
	    	return data;
	    }
}
