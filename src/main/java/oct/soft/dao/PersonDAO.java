package oct.soft.dao;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import oct.soft.model.Office;
import oct.soft.model.Person;

public class PersonDAO {
	private QueryRunner queryRunner;
	private String sql = "";
	
	public PersonDAO(DataSource dataSource) {
		queryRunner = new QueryRunner(dataSource);
	}
	
	public List<Person> personsByOffice(int officeId)
	{
		sql = "select * from person where idperson in (select idperson from offices_person where idoffice=?) ORDER BY fname";
		List<Person> officePersons = new LinkedList<>();
		BeanListHandler<Person> beanListHandler = new BeanListHandler<>(Person.class);
		try {
			officePersons = queryRunner.query(sql, beanListHandler,officeId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return officePersons;
	}	
	
	public List<Person> getAll() {
		String sql = "select p.*,o.name as birou, b.name as filiala from person p \n"
				+ "left join offices_person op on op.idperson = p.idperson \n"
				+ "LEFT join office o on o.idoffice = op.idoffice\n"
				+ "LEFT join office b on b.idoffice = o.parent order by p.fname";
		List<Person> persons= new LinkedList<>();
		BeanListHandler<Person> beanListHandler = new BeanListHandler<>(Person.class);
		try {
			persons = queryRunner.query(sql, beanListHandler);			
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return persons;
	}
	
	public void delete(int idperson) 
	{	
		try {
			queryRunner.execute("DELETE FROM offices_person where idperson = ?",idperson);
			queryRunner.execute("DELETE FROM person where idperson = ?",idperson);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Person getById(int idperson) {
		sql = "SELECT * FROM person where idperson =?";
		Person person = null;
		try {
			BeanHandler<Person> beanHandler = new BeanHandler<>(Person.class);
			person = queryRunner.query(sql, beanHandler, idperson);
		} catch (Exception e) {
				e.printStackTrace();
		}
		return person;
	}

	public int saveOrUpdate(Person person) {
		int idperson = 0;
		try{            
            if(person.isNew()) {
                sql = "INSERT INTO person(fname, lname, nickname) VALUES (?,?,?);";
//                BeanHandler<Person> beanHandler = new BeanHandler<>(Person.class);
//                queryRunner.insert(sql, beanHandler,person.getFname(),person.getLname(), person.getNickname());
                ScalarHandler<BigInteger> scalarHandler = new ScalarHandler<>();
                idperson = (queryRunner.insert(sql, scalarHandler,person.getFname(),person.getLname(), person.getNickname())).intValue();             
            } else {
                //update data
                idperson = person.getIdperson().intValue();
            	queryRunner.update("UPDATE person set fname =?,lname=?,nickname=? WHERE idperson = ?;", person.getFname(), person.getLname(), person.getNickname(),
            			idperson);
        
            }
        } catch(Exception e) {
            e.printStackTrace();
        }        
		return idperson;
	}
	
	public void addOfficeToPerson(int idperson, int idoffice) {
		
		sql = "INSERT INTO offices_person(idperson, idoffice) VALUES(?,?)";
		try {
			queryRunner.update(sql, idperson, idoffice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removePersonFromOffice(int idperson, int idoffice) 
	{
		sql = "DELETE FROM offices_person where idperson=? and idoffice=?";
		try {
			queryRunner.update(sql, idperson, idoffice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removePhoneFromPerson(int idperson, int number) {
		sql = "DELETE FROM phone WHERE idperson = ? AND number=?";
		try {
			queryRunner.execute(sql, idperson,number);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
