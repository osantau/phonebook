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

import com.mysql.cj.result.IntegerValueFactory;

import oct.soft.dao.ReportDAO;
import oct.soft.dao.beans.BirouBean;
import oct.soft.dao.beans.PersonBean;
import oct.soft.helpers.PagesHelper;
import oct.soft.model.User;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(name = "HomeServlet", urlPatterns = { "/search", "/export","/rempersint","/persnrint" })
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
	    		 	boolean userIsAuthenticated = (user!=null && user.isAuthenticated());
	    		 	System.out.println(userIsAuthenticated);
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
	                                  
							
							if(userIsAuthenticated)
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
							
							if(userIsAuthenticated)
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
							sb.append("<li>").append(p.getBranch()).append(" - ").append(p.getName()).append(": <b>");
                                                        for(String number : p.getNumber().split(","))
                                                        {
                                                            if(number.length() == 3){
                                                             
                                                            	if(reportDAO.hasPersonNumber(Integer.valueOf(p.getIdperson()), number))
                                                            	{
                                                            		sb.append("<span class=\"nrint\" style=\"color: red;\">&nbsp;"+number+"&nbsp;<input type=\"hidden\" id=\"person_id\" value=\""+p.getIdperson()+"\"/></span>&nbsp;");
                                                            		if(userIsAuthenticated)
                                                            		{
                                                            			sb.append("<a href=\"#\" class=\"remphone\" style=\"cursor:hand;\">[-]<input type=\"hidden\" id=\"remp_id\" value=\""+p.getIdperson()+"\"/>\r\n"
                                                            					+ "                                            <input type=\"hidden\" id=\"remnum\" value=\""+number+"\"/></a>");
                                                            		}
                                                            	} else if(userIsAuthenticated)
                                                            	{
                                                            		sb.append("<span class=\"nrint\"><a href=\"#\" style=\"cursor:hand; text-decoration:none\">"+number+"</a><input type=\"hidden\" id=\"person_id\" value=\""+p.getIdperson()+"\"/></span>&nbsp;");
                                                            	} else {
                                                            		sb.append("<span class=\"nrint\">&nbsp;"+number+"&nbsp;<input type=\"hidden\" id=\"person_id\" value=\""+p.getIdperson()+"\"/></span>&nbsp;");
                                                            	}
                                                            	
                                                            } else {
                                                            	sb.append("&nbsp;&nbsp;").append(number).append("&nbsp;&nbsp;");
                                                            }
                                                        }
                                                        sb.append("</b></li>");
                                                        
                                                        if(p.getTelserv()!=null && !p.getTelserv().isEmpty())
                                                        {
                                                        	sb.append("<li>Tel serv: <b>"+p.getTelserv()+"</b></li>");
                                                        } else if(p.getTelfix()!=null && !p.getTelfix().isEmpty())
                                                        {
                                                        	sb.append("<li>Tel fix: <b>"+p.getTelfix()+"</b></li>");
                                                        } else if(p.getTelmobil()!=null && !p.getTelmobil().isEmpty())
                                                        {
                                                        	sb.append("<li>Tel mobil: <b>"+p.getTelmobil()+"</b></li>");
                                                        }

							sb.append("</ul></td>");
							
							if(userIsAuthenticated)
							{
								sb.append("<td><a href=\"").append(basePath).append("/person?action=edit&idperson=").append(p.getIdperson())
								   .append("\" title=\"Modifica\" style=\"text-decoration:none;\">")
								   .append("<img src=\"").append(basePath).append("/assets/images/pencil.png\" border=\"0\"/>")
								   .append("</a></td>");
								
							}
							sb.append("</tr>");
						}
						
						sb.append("</tbody></table>");						
						// javascript functionality
						sb.append("<input type=\"hidden\" id=\"base_url\" value=\""+basePath+"\"/>");
						sb.append("<div id=\"info\" style=\"text-weight: bolder;\"></div>");
						
						if(userIsAuthenticated)
						{
							sb.append("<script type=\"text/javascript\">   \r\n"
									+ "                    $(\".remphone\").click(function(e){\r\n"
									+ "                    var remp_id=$(this).find(\"#remp_id\").val();\r\n"
									+ "                    var remnum=$(this).find(\"#remnum\").val();\r\n"
									+ "                    var base_url=$(\"#base_url\").val()+\"/rempersint\";  \r\n"
									+ "                    var base_url2=$(\"#base_url\").val()+\"/search\";\r\n"
									+ "                    var term=$(\"#faq_search_input\").val();\r\n"
									+ "                            $.post(\r\n"
									+ "                                base_url,{remnum: remnum, rempers_id: remp_id},function(response){\r\n"
									+ "                              //  $(\"#info\").html(response).show();\r\n"
									+ "                                          }\r\n"
									+ "                                );\r\n"
									+ "                            $.ajax({\r\n"
									+ "                                            type: \"POST\",\r\n"
									+ "                                            url: base_url2,\r\n"
									+ "                                            data: \"keyword=\"+term,\r\n"
									+ "                                            success: function(response){\r\n"
									+ "                                                        $(\".gbox\").html(response).show();\r\n"
									+ "\r\n"
									+ "                                                                            }   \r\n"
									+ "                                        });\r\n"
									+ "                               \r\n"
									+ "                     });\r\n"
									+ "                    $(\".nrint\").click(function(){\r\n"
									+ "                           var person_id=$(this).find(\"#person_id\").val();\r\n"
									+ "                           var numar=$(this).text();\r\n"
									+ "                           var base_url=$(\"#base_url\").val()+\"/persnrint\";  \r\n"
									+ "                           var base_url2=$(\"#base_url\").val()+\"/search\";\r\n"
									+ "                           var term=$(\"#faq_search_input\").val();\r\n"
									+ "									console.log(base_url);"
									+ "                             $.post(\r\n"
									+ "                                base_url,{numar: numar, person_id: person_id},function(response){\r\n"
									+ "                              //  $(\"#info\").html(response).show();\r\n"
									+ "                                                    }\r\n"
									+ "                                );\r\n"
									+ "                       $.ajax({\r\n"
									+ "                                            type: \"POST\",\r\n"
									+ "                                            url: base_url2,\r\n"
									+ "                                            data: \"keyword=\"+term,\r\n"
									+ "                                            success: function(response){\r\n"
									+ "                                                        $(\".gbox\").html(response).show();\r\n"
									+ "\r\n"
									+ "                                                                            }   \r\n"
									+ "                                        });           \r\n"
									+ "                              });                                            \r\n"
									+ "                    </script>");
						}
						
						
						response.getWriter().write(sb.toString());
					}
		    	} else if(path.equals("/persnrint"))
		    	{
		    		int idperson  = Integer.valueOf(request.getParameter("person_id"));
		    		String number = request.getParameter("numar");
		    		reportDAO.addPersonInt(number, idperson);
		    	} else if(path.equals("/rempersint")) {
		    		int idperson  = Integer.valueOf(request.getParameter("rempers_id"));
		    		String number = request.getParameter("remnum");
		    		reportDAO.removePersonInt(number, idperson);
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
