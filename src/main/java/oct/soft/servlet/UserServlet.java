package oct.soft.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import oct.soft.dao.UserDAO;
import oct.soft.helpers.PagesHelper;
import oct.soft.model.User;
import oct.soft.validator.MyValidator;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(name = "UserServlet", urlPatterns = { "/user" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataSource dataSource = null;
	UserDAO userDAO = null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
	}

	@Override
	public void init() throws ServletException {
		dataSource = (DataSource) getServletContext().getAttribute("dataSource");
		userDAO = new UserDAO(dataSource);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();		
//		String path = request.getServletPath();
		
		if (method.equals("GET")) {
			String action = request.getParameter("action");
			if(action == null) 
			{
				request.setAttribute("users", userDAO.all());
				request.getRequestDispatcher(PagesHelper.USER_LIST).forward(request, response);
			} 
			else if(action.equals("add")) 
			{
				User user = new User();
				request.setAttribute("user", user);
				request.getRequestDispatcher(PagesHelper.USER_FORM).forward(request, response);
			}
			else if(action.equals("update") || action.equals("delete"))
			{
				int iduser = Integer.valueOf(request.getParameter("id"));
				
				if(action.equals("update"))
				{
					User user = userDAO.getById(iduser);				
					request.setAttribute("user", user);					
					request.getRequestDispatcher(PagesHelper.USER_FORM).forward(request, response);
				}else if(action.equals("delete"))
				{
					userDAO.delete(iduser);
					response.sendRedirect(request.getContextPath()+"/user");
					return;
				}
			} 
		} else if (method.equals("POST")) {
			 String id = request.getParameter("id");
			 User user = id ==null || id.isEmpty() ? new User() : userDAO.getById(Integer.valueOf(id));
			 user.setFname(request.getParameter("fname"));
			 user.setLname(request.getParameter("lname"));
			 user.setUsername(request.getParameter("username"));
			 user.setEmail(request.getParameter("email"));
			 user.setDecodedPassword(request.getParameter("decodedPassword"));			 
			 
			 String errors = MyValidator.validate(user);
			 
			  if(!errors.isEmpty())  
	            {
	            	request.setAttribute("errors", errors);	      		            	
	            	request.getRequestDispatcher(PagesHelper.USER_FORM).forward(request, response);
	            }
			  else {
				  userDAO.saveOrUpdate(user);
				  response.sendRedirect(request.getContextPath()+"/user");
				  return;
			  }
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
