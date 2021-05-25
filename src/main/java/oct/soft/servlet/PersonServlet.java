package oct.soft.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import oct.soft.dao.PersonDAO;
import oct.soft.helpers.PagesHelper;
import oct.soft.model.Person;
import oct.soft.validator.MyValidator;

/**
 * Servlet implementation class PersonServlet
 */
@WebServlet(name = "PersonServlet", urlPatterns = { "/person" })
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataSource dataSource = null;
	PersonDAO personDAO = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PersonServlet() {

	}

	@Override
	public void init() throws ServletException {
		dataSource = (DataSource) getServletContext().getAttribute("dataSource");
		personDAO = new PersonDAO(dataSource);

	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		String path = request.getServletPath();
		if (method.equals("GET")) {
			if (path.equals("/person")) {
				String action = request.getParameter("action");
				if(action==null) {
				request.setAttribute("personList", personDAO.getAll());
				request.getRequestDispatcher(PagesHelper.PERSON_LIST).forward(request, response);
				} else {
					switch (action) {
					case "add":
					{
						Person person = new Person();
						request.setAttribute("person", person);
						request.setAttribute("isEdit", false);
						request.getRequestDispatcher(PagesHelper.PERSON_FORM).forward(request, response);
					}
						break;
					case "edit":
					{
						request.setAttribute("isEdit", true);
						request.getRequestDispatcher(PagesHelper.PERSON_FORM);
						request.getRequestDispatcher(PagesHelper.PERSON_FORM).forward(request, response);
					}
						break;
					case "delete":
					{
						int idperson = Integer.valueOf(request.getParameter("idperson"));
						personDAO.delete(idperson);
						response.sendRedirect(request.getContextPath()+"/person");
					}
						break;
					
					}					
				}
			}
		} else if (method.equals("POST")) {
			if (path.equals("/person")) {
				String action = request.getParameter("action");
				switch (action) {
				case "add": {
					String strId = request.getParameter("idperson");
					Person person = strId==null || strId.isEmpty() ? new Person() : personDAO.getById(Integer.valueOf(strId));
					person.setFname(request.getParameter("fname"));
					person.setFname(request.getParameter("lname"));
					person.setFname(request.getParameter("nickname"));
					String errors = MyValidator.validate(person);
					
					if(!errors.isEmpty())  
		            {
		            	request.setAttribute("errors", errors);		            			            	
		            	request.getRequestDispatcher(PagesHelper.PERSON_FORM).forward(request, response);
		            } else {
			            personDAO.saveOrUpdate(person);
			            response.sendRedirect(request.getServletContext().getContextPath()+"/person");
		            } 
				}
					
				break;

				}
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
