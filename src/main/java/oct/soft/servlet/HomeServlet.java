package oct.soft.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import oct.soft.dao.ReportDAO;
import oct.soft.dao.beans.BirouBean;
import oct.soft.dao.beans.PersonBean;
import oct.soft.helpers.PagesHelper;
import oct.soft.model.User;

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
	    	 if (path.equals("/search"))
		    	{
	    		 String basePath = request.getServletContext().getContextPath();
	    		 	User user = (User)(request.getSession().getAttribute("user"));
	    		 	String keyword = request.getParameter("keyword");
	    		 	List<BirouBean> filiale = reportDAO.searchBranch(keyword);
	    		 	List<BirouBean> birouri = reportDAO.searchOffice(keyword);
	    		 	List<PersonBean> persoane = reportDAO.searchPerson(keyword);
	    		 	
	    		 	StringBuilder sb = new StringBuilder();

					if (!filiale.isEmpty()) {
						sb = new StringBuilder();
						sb.append("<div align=\"center\"><table cellspacing=\"5px\">\r\n"
								+ "                         <thead>\r\n"
								+ "                         <th>Nr.<br />Crt.</th>\r\n"
								+ "                            <th>Filiala</th>\r\n"
								+ "                            <th>Telefon</th>\r\n"
								+ "                         </thead>\r\n"
								+ "                        <tbody>");
						int count = 1;
						
						for(BirouBean f:filiale) {
							sb.append("<tr><td>").append(count++)
	                          .append("</td><td>").append(f.getName())
	                          .append("</td><td align=\"center\"><b>")
	                          .append(f.getNumber())
	                          .append("</b></td>");	                          
	                                  
							
							if(user!=null && user.isAuthenticated())
							{
								sb.append("<td>")
								.append("<a href=\"")
								.append(basePath+"/branches?edit="+f.getIdoffice()).append("\"")
								.append(" title =\"Modifica\" style=\"text-decoration:none;\" >")
								.append("<img src=\""+basePath+"/assets/images/pencil.png\" border=\"0\"/>")
								.append("</a>")
								.append("</td>")
								.append("</tr>");
							}
						}
						sb.append("</tbody></table>");
						response.getWriter().write(sb.toString());
					} else if (!birouri.isEmpty()) {
						sb = new StringBuilder("<div align=\"center\"><table cellspacing=\"5px\">\r\n"
								+ "                         <thead>\r\n"
								+ "                         <th>Nr.<br />Crt.</th>\r\n"
								+ "                            <th>Birou</th>\r\n"
								+ "                            <th>Filiala</th>\r\n"
								+ "                            <th>Telefon</th>\r\n"
								+ "                         </thead>\r\n"
								+ "                        <tbody>");
						
						int count = 1;
						for(BirouBean b : birouri)
						{
							sb.append("<tr>")
							.append("<td>").append(count++).append("</td><td>").append(b.getName()).append("</td>")
							.append("<td>").append(b.getBranch()).append("</td>")
							.append("</td><td align=\"center\"><b>").append(b.getNumber()).append("</b></td>");
							
							if(user!=null && user.isAuthenticated())
							{
								sb.append("<td>")
								.append("<a href=\"")
								.append(basePath+"/office?edit="+b.getIdoffice()).append("\"")
								.append(" title =\"Modifica\" style=\"text-decoration:none;\" >")
								.append("<img src=\""+basePath+"/assets/images/pencil.png\" border=\"0\"/>")
								.append("</a>")
								.append("</td>");
							} 
							
							sb.append("</tr>");
						}
						sb.append("</tbody></table>");
						response.getWriter().write(sb.toString());
					} else if (!persoane.isEmpty()) {
						sb = new StringBuilder("<div align=\"center\"><table cellspacing=\"5\" style=\"text-align: center;\"><tbody>");
						
						for(PersonBean p : persoane)
						{
							sb.append("<tr><td style=\"font-weight: bold; vertical-align: top; text-align: left;\">"+p.getFname()+" "+p.getLname()+"</td>");
							sb.append("<td>  <ul style=\"text-align: left; list-style-type: bullet;\">");
								for(String b : p.getNumber().split(",")) {
									
								}
							sb.append("</ul></td>");
						}
						
						sb.append("</tbody></table>");						
						
						response.getWriter().write(sb.toString());
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
