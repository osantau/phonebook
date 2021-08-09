package oct.soft.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import oct.soft.dao.OfficeDAO;
import oct.soft.dao.PersonDAO;
import oct.soft.dao.PhoneDAO;
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
	OfficeDAO officeDAO = null;
	PhoneDAO phoneDAO = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PersonServlet() {

	}

	@Override
	public void init() throws ServletException {
		dataSource = (DataSource) getServletContext().getAttribute("dataSource");
		personDAO = new PersonDAO(dataSource);
		officeDAO = new OfficeDAO(dataSource);
		phoneDAO = new PhoneDAO(dataSource);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		String path = request.getServletPath();
		if (method.equals("GET")) {
			if (path.equals("/person")) {
				String action = request.getParameter("action");
				if (action == null) {
					request.setAttribute("personList", personDAO.getAll());
					request.getRequestDispatcher(PagesHelper.PERSON_LIST).forward(request, response);
				} else {

					if (action.equals("add")) {
						Person person = new Person();
						request.setAttribute("person", person);
						request.setAttribute("isEdit", false);
						request.getRequestDispatcher(PagesHelper.PERSON_FORM).forward(request, response);
					}

					else if (action.equals("edit")) {
						Person person = personDAO.getById(Integer.valueOf(request.getParameter("idperson")));
						request.setAttribute("person", person);
						request.setAttribute("isEdit", true);
						request.setAttribute("officeCombo", officeDAO.getBranchOfficeChilds(person.getIdperson()));
						request.setAttribute("offices", officeDAO.getOfficesByPerson(person.getIdperson()));
						request.setAttribute("personPhones", phoneDAO.getPersonPhones(person.getIdperson()));
						request.getRequestDispatcher(PagesHelper.PERSON_FORM).forward(request, response);
					} else if (action.equals("delete")) {
						int idperson = Integer.valueOf(request.getParameter("idperson"));
						personDAO.delete(idperson);
						response.sendRedirect(request.getContextPath() + "/person");
						return;
					}

					else if (action.equals("remove-office")) {
						int idperson = Integer.valueOf(request.getParameter("idperson"));
						int idoffice = Integer.valueOf(request.getParameter("idoffice"));
						personDAO.removePersonFromOffice(idperson, idoffice);
						response.sendRedirect(request.getContextPath() + "/person?action=edit&idperson=" + idperson);
						return;
					}

					else if (action.equals("remove-phone")) {
						int idperson = Integer.valueOf(request.getParameter("person"));
						int number = Integer.valueOf(request.getParameter("number"));
						personDAO.removePhoneFromPerson(idperson, number);
						response.sendRedirect(request.getContextPath() + "/person?action=edit&idperson=" + idperson);
						return;
					}

				}
			}
		} else if (method.equals("POST")) {
			if (path.equals("/person")) {
				String action = request.getParameter("action");
				if (action.equals("add-or-update")) {
					String strId = request.getParameter("idperson");
					Person person = strId == null || strId.isEmpty() ? new Person()
							: personDAO.getById(Integer.valueOf(strId));
					person.setFname(request.getParameter("fname"));
					person.setLname(request.getParameter("lname"));
					person.setNickname(request.getParameter("nickname"));
					String errors = MyValidator.validate(person);

					if (!errors.isEmpty()) {
						request.setAttribute("errors", errors);
						request.getRequestDispatcher(PagesHelper.PERSON_FORM).forward(request, response);
					} else {
						int personId = personDAO.saveOrUpdate(person);
						response.sendRedirect(request.getServletContext().getContextPath()
								+ "/person?action=edit&idperson=" + personId);
						return;
					}
				} else if (action.equals("add-office")) {
					Person person = personDAO.getById(Integer.valueOf(request.getParameter("idperson")));
					personDAO.addOfficeToPerson(person.getIdperson(),
							Integer.valueOf(request.getParameter("idoffice")));
					response.sendRedirect(
							request.getContextPath() + "/person?action=edit&idperson=" + person.getIdperson());
					return;
				}

				else if (action.equals("add-phone")) {
					Person person = personDAO.getById(Integer.valueOf(request.getParameter("idperson")));
					phoneDAO.addPersonPhone(person.getIdperson(), Integer.valueOf(request.getParameter("phone")),
							request.getParameter("tel"));
					response.sendRedirect(
							request.getContextPath() + "/person?action=edit&idperson=" + person.getIdperson());
					return;
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
