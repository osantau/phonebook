package oct.soft.test;

import java.security.MessageDigest;

import javax.sql.DataSource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;

import oct.soft.dao.BranchDAO;
import oct.soft.dao.OfficeDAO;
import oct.soft.dao.PersonDAO;
import oct.soft.db.util.MyDataSource;
import oct.soft.model.Person;

public class BranchOfficeTest {

	private DataSource dataSource = null;
	private BranchDAO branchDAO = null;
	private OfficeDAO officeDAO = null;
	private PersonDAO personDAO = null;
	
	public BranchOfficeTest() {
		dataSource = MyDataSource.getDataSource();
		branchDAO = new BranchDAO(dataSource);
		officeDAO = new OfficeDAO(dataSource);
		personDAO = new  PersonDAO(dataSource);
	}
	public static void main(String[] args) {
		BranchOfficeTest bTest = new BranchOfficeTest();
//		bTest.getOffices();
//		bTest.testPersonWithOfficeAndBranch();
//		bTest.testSavePersonAndReturnId();
//		bTest.testGetOfficeCombo();
//		bTest.officeByPersonId();
		System.out.println(Md5Crypt.md5Crypt("1qed".getBytes()));
		
	
	}
	
	public void getOffices() {
		System.out.println(officeDAO.officeList().get(0).getBranch());
	}
	
	public void testPersonWithOfficeAndBranch() {
	System.out.println(personDAO.getAll());
	}
	
	public void testSavePersonAndReturnId() {
		Person person = new Person();
		person.setFname("test");
		person.setLname("test");
		person.setNickname("testnick");
		System.out.println(personDAO.saveOrUpdate(person));
	}
	
	public void testGetOfficeCombo() {
		System.out.println(officeDAO.getBranchOfficeChilds(214));
	}
	
	public void officeByPersonId()
	{
		System.out.println(officeDAO.getOfficesByPerson(214));
	}
	
}
