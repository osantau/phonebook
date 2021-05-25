package oct.soft.test;

import javax.sql.DataSource;

import oct.soft.dao.BranchDAO;
import oct.soft.dao.OfficeDAO;
import oct.soft.dao.PersonDAO;
import oct.soft.db.util.MyDataSource;

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
		bTest.testPersonWithOfficeAndBranch();
	
	}
	
	public void getOffices() {
		System.out.println(officeDAO.officeList().get(0).getBranch());
	}
	
	public void testPersonWithOfficeAndBranch() {
	System.out.println(personDAO.getAll());
	}
	
}
