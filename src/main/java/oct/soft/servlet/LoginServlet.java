/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oct.soft.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;

import oct.soft.dao.UserDAO;
import oct.soft.helpers.PagesHelper;
import oct.soft.model.LoginForm;
import oct.soft.model.User;
import oct.soft.validator.MyValidator;

/**
 *
 * @author osantau
 */
@WebServlet(name = "LoginServlet", urlPatterns = { "/login", "/logout" })
public class LoginServlet extends HttpServlet {

	DataSource dataSource = null;
	UserDAO userDAO = null;

	@Override
	public void init() throws ServletException {
		dataSource = (DataSource) getServletContext().getAttribute("dataSource");
		userDAO = new UserDAO(dataSource);
	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String method = request.getMethod();
		String path = request.getServletPath();
		if (method.equals("GET")) {
			if (path.equals("/login")) {
				request.getRequestDispatcher(PagesHelper.LOGIN_PAGE).forward(request, response);
			} else if (path.equals("/logout")) {
				session.invalidate();
				response.sendRedirect(request.getContextPath());
			}

		} else if (method.equals("POST")) {

			LoginForm login = new LoginForm();
			login.setUsername(request.getParameter("username"));
			login.setPassword(request.getParameter("password"));
			String errors = MyValidator.validate(login);

			if (!errors.isEmpty()) {
				request.setAttribute("errors", errors);
				request.setAttribute("loginForm", login);
				request.getRequestDispatcher(PagesHelper.LOGIN_PAGE).forward(request, response);
			} else {
				User user = userDAO.getAuthenticatedUser(login.getUsername(), login.getPassword());
				if (!user.isAuthenticated()) {
					errors = "<p>Nume de utilizator sau parola incorecte !</p>";
					request.setAttribute("errors", errors);
					request.setAttribute("loginForm", login);
					request.getRequestDispatcher(PagesHelper.LOGIN_PAGE).forward(request, response);
				} else {
					session.setAttribute("user", user);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			}

		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
	// + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
