package oct.soft.dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import oct.soft.model.User;

public class UserDAO {
	private QueryRunner queryRunner;
	private String sql = "";
	
	public UserDAO(DataSource dataSource) {
		queryRunner = new QueryRunner(dataSource);
	}
	
	public User getById(int userid)
	{
		User user = new User();
		BeanHandler<User> beanHandler = new BeanHandler<User>(User.class);
		sql  = "SELECT * FROM users WHERE id = ?";
		try {
			user = queryRunner.query(sql, beanHandler, userid);
			user.setDecodedPassword(user.getDecodedPassword());
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return user;
	}
	public int saveOrUpdate(User user) {
		int iduser = 0;
		try{            
            if(user.isNew()) {
                sql = "INSERT INTO users(username,fname, lname, email,isadmin,password) VALUES (?,?,?,?,?,?);";
//                BeanHandler<Person> beanHandler = new BeanHandler<>(Person.class);
//                queryRunner.insert(sql, beanHandler,person.getFname(),person.getLname(), person.getNickname());
                ScalarHandler<Integer> scalarHandler = new ScalarHandler<>();
                iduser = queryRunner.insert(sql, scalarHandler, user.getUsername(), user.getFname(), user.getLname(), user.getEmail(),
                		user.getIsadmin(),user.getPassword()).intValue();             
            } else {
                //update data
                iduser = user.getId().intValue();
            	queryRunner.update("UPDATE users set username=?,fname=?, lname=?, email=?,isadmin=?,password=? WHERE id = ?;",
            			user.getUsername(), user.getFname(), user.getLname(), user.getEmail(), user.getIsadmin(), user.getPassword(),iduser);        
            }
        } catch(Exception e) {
            e.printStackTrace();
        }        
		return iduser;
	}
	
	public List<User> all()
	{
		sql = "SELECT * FROM users";
		List<User> data = new LinkedList<User>();
		BeanListHandler<User> beanListHandler = new BeanListHandler<>(User.class);
		try {
			data = queryRunner.query(sql, beanListHandler);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public void delete(int iduser)
	{
		try {
			queryRunner.execute("DELETE FROM users WHERE id = ?", iduser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
