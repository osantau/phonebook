package oct.soft.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import oct.soft.dao.ReportDAO;
import oct.soft.helpers.PagesHelper;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(name = "HomeServlet", urlPatterns = { "/search", "/export" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataSource dataSource = null;
	ReportDAO reportDAO = null;

	@Override
	public void init() throws ServletException {
		dataSource = (DataSource) getServletContext().getAttribute("dataSource");
		reportDAO = new ReportDAO(dataSource);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {

	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 String method = request.getMethod();
	     String path = request.getServletPath(); 
	     
	     if(method.equals("GET"))
	     {
	    	if (path.equals("/export"))
	    	{
	    		String type = request.getParameter("type");
	    		if(type != null)
	    		{	
	    			if(type.equals("interioare"))
	    			{
	    				byte[] outArray = reportDAO.reportBirouri().toByteArray();
	    				response.setContentLength(outArray.length);
	    			    response.setHeader("Expires:", "0"); // eliminates browser caching
	    			    response.setHeader("Content-Disposition", "attachment; filename=Interioare Birouri.xlsx");
	    			    OutputStream outStream = response.getOutputStream();
	    			    outStream.write(outArray);
	    			    outStream.flush();
	    			} 
	    			else if (type.equals("filiale"))
	    			{
	    				byte[] outArray = reportDAO.reportFiliale().toByteArray();
	    				response.setContentLength(outArray.length);
	    			    response.setHeader("Expires:", "0"); // eliminates browser caching
	    			    response.setHeader("Content-Disposition", "attachment; filename=Numere Filiale.xlsx");
	    			    OutputStream outStream = response.getOutputStream();
	    			    outStream.write(outArray);
	    			    outStream.flush();
	    			}
	    			else if (type.equals("personal"))
	    			{
	    				byte[] outArray = reportDAO.reportPersonal().toByteArray();
	    				response.setContentLength(outArray.length);
	    			    response.setHeader("Expires:", "0"); // eliminates browser caching
	    			    response.setHeader("Content-Disposition", "attachment; filename=Numere Personal.xlsx");
	    			    OutputStream outStream = response.getOutputStream();
	    			    outStream.write(outArray);
	    			    outStream.flush();
	    			}
	    		}
	    		else {	
	    		request.getRequestDispatcher(PagesHelper.EXPORT_LIST).forward(request, response);
	    		}
	    	}
	     } else if(method.equals("POST"))
	     {
	    	 
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
