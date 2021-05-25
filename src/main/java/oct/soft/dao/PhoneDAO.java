package oct.soft.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import oct.soft.model.Phone;

public class PhoneDAO {
	private QueryRunner queryRunner;
	private String sql = "";

	public PhoneDAO(DataSource dataSource) {
		queryRunner = new QueryRunner(dataSource);
	}

	public void addOfficePhone(int idOffice, int number, String type) {
		Phone phone = new Phone();
		phone.setIdoffice(idOffice);
		phone.setNumber(number);
		switch (type) {
		case "interior":
			phone.setInterior(1);
			break;
		case "mobil":
			phone.setMobil(1);
			break;
		case "fix":
			phone.setFix(1);
		}

		sql = "INSERT INTO phone(idoffice,number,interior,mobil,fix) VALUES (?,?,?,?,?)";
		  BeanHandler<Phone> beanHandler = new BeanHandler<>(Phone.class);
          try {        	  
			queryRunner.insert(sql, beanHandler,phone.getIdoffice(), phone.getNumber(), phone.getInterior(), phone.getMobil(), phone.getFix());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Phone> getOfficePhones(int idOffice)
	{
		 List<Phone> officePhones = new ArrayList<>();
		 
	        try{
	        BeanListHandler<Phone> beanListHandler = new BeanListHandler<>(Phone.class);
	        sql = "SELECT * FROM phone WHERE idoffice = ?";
	        officePhones = queryRunner.query(sql, beanListHandler, idOffice);	        	       
	        }catch(Exception ex) {
	            ex.printStackTrace();
	        }
		 return officePhones;
	}
	
	public void deleteOfficePhone(int idOffice,int number)
	{
		sql = "DELETE FROM phone WHERE idoffice = ? AND number=?";
		try {
			queryRunner.execute(sql, idOffice,number);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
