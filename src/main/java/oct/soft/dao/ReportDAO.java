package oct.soft.dao;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import oct.soft.dao.beans.BirouBean;
import oct.soft.dao.beans.PersonBean;

public class ReportDAO {
	private QueryRunner queryRunner;
	private String sql = "";

	public ReportDAO(DataSource dataSource) {
		queryRunner = new QueryRunner(dataSource);
	}

	public List<BirouBean> interioareBirouri() {
		sql = "SELECT o.name as birou, group_concat(p.number ORDER BY p.number ASC SEPARATOR ', ') as numere FROM office o \n"
				+ "                    JOIN phone p on (o.idoffice=p.idoffice and p.interior=1) where o.isbranch=0 group by name";

		List<BirouBean> data = new LinkedList<>();
		BeanListHandler<BirouBean> beanListHandler = new BeanListHandler<>(BirouBean.class);
		try {
			data = queryRunner.query(sql, beanListHandler);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}
	
	public List<PersonBean> interioarePersonal()
	{
		sql ="select person.fname nume,person.lname prenume, group_concat(phone.number ORDER BY phone.number ASC SEPARATOR ', ') as numere\n"
				+ "                    ,(SELECT number from phone where idphone = person.telint_id) as  numar_asociat\n"
				+ "                    from person \n"
				+ "                    join offices_person on (person.idperson = offices_person.idperson)\n"
				+ "                    join office on(office.idoffice = offices_person.idoffice)\n"
				+ "                    join phone on(phone.idoffice = office.idoffice and phone.interior=1) \n"
				+ "                    group by fname,lname";
		List<PersonBean> data = new LinkedList<>();

		BeanListHandler<PersonBean> beanListHandler = new BeanListHandler<>(PersonBean.class);
		try {
			data = queryRunner.query(sql, beanListHandler);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public List<BirouBean> interioareFiliale() {
		sql ="SELECT o.name as birou, group_concat(t.number ORDER BY t.number DESC SEPARATOR ', ') as numere " 
                + " from office o inner join phone t on(o.idoffice=t.idoffice) WHERE o.isbranch=1 group by o.name";

		List<BirouBean> data = new LinkedList<>();
		BeanListHandler<BirouBean> beanListHandler = new BeanListHandler<>(BirouBean.class);
		try {
			data = queryRunner.query(sql, beanListHandler);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}
	
	public ByteArrayOutputStream reportBirouri()
	{
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet spreadsheet = wb.createSheet();
		XSSFRow row = spreadsheet.createRow(0);
		row.createCell(0).setCellValue("Birou");
		row.createCell(1).setCellValue("Numere");
		
		int idx = 1;
		for(BirouBean bb : interioareBirouri() )
		{
			row = spreadsheet.createRow(idx);
			row.createCell(0).setCellValue(bb.getBirou()); 
			row.createCell(1).setCellValue(bb.getNumere());				
			idx++;
		}
		spreadsheet.autoSizeColumn(0);
		spreadsheet.autoSizeColumn(1);
				
		try {
			wb.write(outByteStream);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outByteStream;
	}
	
	public ByteArrayOutputStream reportFiliale()
	{
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet spreadsheet = wb.createSheet();
		XSSFRow row = spreadsheet.createRow(0);
		row.createCell(0).setCellValue("Filiala");
		row.createCell(1).setCellValue("Numere");
		
		int idx = 1;
		for(BirouBean bb : interioareFiliale())
		{
			row = spreadsheet.createRow(idx);
			row.createCell(0).setCellValue(bb.getBirou()); 
			row.createCell(1).setCellValue(bb.getNumere());				
			idx++;
		}
		spreadsheet.autoSizeColumn(0);
		spreadsheet.autoSizeColumn(1);
				
		try {
			wb.write(outByteStream);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outByteStream;
	}
	
	public ByteArrayOutputStream reportPersonal()
	{
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet spreadsheet = wb.createSheet();
		XSSFRow row = spreadsheet.createRow(0);
		row.createCell(0).setCellValue("Nume");
		row.createCell(1).setCellValue("Prenume");
		row.createCell(2).setCellValue("Numere");
		row.createCell(3).setCellValue("Numar asociat");
		
		int idx = 1;
		for(PersonBean pb: interioarePersonal())
		{
			row = spreadsheet.createRow(idx);
			row.createCell(0).setCellValue(pb.getNume());
			row.createCell(1).setCellValue(pb.getPrenume());
			row.createCell(2).setCellValue(pb.getNumere());
			row.createCell(3).setCellValue(pb.getNumar_asociat());				
			idx++;
		}
		spreadsheet.autoSizeColumn(0);
		spreadsheet.autoSizeColumn(1);
		spreadsheet.autoSizeColumn(2);
		spreadsheet.autoSizeColumn(3);
				
		try {
			wb.write(outByteStream);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outByteStream;
	}
}

