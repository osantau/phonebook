package oct.soft.test;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.sql.DataSource;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import oct.soft.dao.BranchDAO;
import oct.soft.dao.OfficeDAO;
import oct.soft.dao.PersonDAO;
import oct.soft.dao.ReportDAO;
import oct.soft.dao.UserDAO;
import oct.soft.dao.beans.BirouBean;
import oct.soft.dao.beans.PersonBean;
import oct.soft.db.util.MyDataSource;
import oct.soft.model.Person;

public class BranchOfficeTest {

	private DataSource dataSource = null;
	private BranchDAO branchDAO = null;
	private OfficeDAO officeDAO = null;
	private PersonDAO personDAO = null;
	private UserDAO userDAO = null;
	private ReportDAO reportDAO = null;

	public BranchOfficeTest() {
		dataSource = MyDataSource.getDataSource();
		branchDAO = new BranchDAO(dataSource);
		officeDAO = new OfficeDAO(dataSource);
		personDAO = new PersonDAO(dataSource);
		userDAO = new UserDAO(dataSource);
		reportDAO = new ReportDAO(dataSource);
	}

	public static void main(String[] args) throws Exception {
		BranchOfficeTest bTest = new BranchOfficeTest();
//		bTest.getOffices();
//		bTest.testPersonWithOfficeAndBranch();
//		bTest.testSavePersonAndReturnId();
//		bTest.testGetOfficeCombo();
//		bTest.officeByPersonId();
//		bTest.usersGetAll();			
//		bTest.testAuthentication();
//		bTest.reportBirouri();
//		bTest.searchBranch();
//		bTest.searchOffice();
		bTest.searchPerson();
	
	}

	public void searchPerson() {
		List<PersonBean> lst = reportDAO.searchPerson("sant");
	
	
		System.out.println(lst);
		
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

	public void officeByPersonId() {
		System.out.println(officeDAO.getOfficesByPerson(214));
	}

	public void usersGetAll() {
		System.out.println(userDAO.all());
	}

	public void testAuthentication() {
		System.out.println(userDAO.getAuthenticatedUser("admin", "admin").isAuthenticated());
	}

	public void searchBranch() {
		System.out.println(reportDAO.searchBranch("last"));
	}
		
	public void searchOffice() {
		System.out.println(reportDAO.searchOffice("infor"));
	}
	

		public void reportBirouri()
		{
			XSSFWorkbook wk = new XSSFWorkbook();
			XSSFSheet spreadsheet = wk.createSheet();
			XSSFRow row = spreadsheet.createRow(0);
			row.createCell(0).setCellValue("Birou");
			row.createCell(1).setCellValue("Numere");
			
			int idx = 1;
			for(BirouBean bb :reportDAO.interioareBirouri() )
			{
				row = spreadsheet.createRow(idx);
				row.createCell(0).setCellValue(bb.getBirou()); 
				row.createCell(1).setCellValue(bb.getNumere());				
				idx++;
			}
			spreadsheet.autoSizeColumn(0);
			spreadsheet.autoSizeColumn(1);
			
			FileOutputStream fos;
			try {
				fos = new FileOutputStream("e:/bb/test.xlsx");
				wk.write(fos);		
				fos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		

		}
}
