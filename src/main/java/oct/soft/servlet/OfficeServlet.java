package oct.soft.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import oct.soft.dao.BranchDAO;
import oct.soft.dao.OfficeDAO;
import oct.soft.dao.PersonDAO;
import oct.soft.dao.PhoneDAO;
import oct.soft.helpers.PagesHelper;
import oct.soft.model.Office;
import oct.soft.validator.MyValidator;

/**
 * Servlet implementation class OfficeServlet
 */
@WebServlet(name = "OfficeServlet", urlPatterns = { "/office","/office-add-phone","/office-delete-phone" })
public class OfficeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	DataSource dataSource = null;
	   BranchDAO branchDAO  = null;
	   OfficeDAO officeDAO = null;
	   PhoneDAO phoneDAO = null;
	   PersonDAO personDAO = null;
	   
	public OfficeServlet() {

	}

	@Override
	public void init() throws ServletException {
		dataSource = (DataSource) getServletContext().getAttribute("dataSource");
		branchDAO = new BranchDAO(dataSource);
		officeDAO = new OfficeDAO(dataSource);
		phoneDAO = new PhoneDAO(dataSource);
		personDAO = new PersonDAO(dataSource);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 String method = request.getMethod();
	     String path = request.getServletPath(); 
	     if(method.equals("GET")) {
	    	 
	    	 if(path.equals("/office-delete-phone"))
	    	 {
	    		 int office =Integer.valueOf(request.getParameter("office"));
	    		 int number = Integer.valueOf(request.getParameter("number"));
	    		 phoneDAO.deleteOfficePhone(office, number);
	    		 response.sendRedirect(request.getContextPath()+"/office?edit="+office);
	    		 return;
	    	 }
	     else {
	            String editId = request.getParameter("edit");
	            boolean add = request.getParameterMap().keySet().contains("add");            
	            if(editId != null) 
	            {
	                Office office = officeDAO.getOffice(Integer.valueOf(editId));
	                request.setAttribute("office", office);
	                request.setAttribute("branchList", branchDAO.branchList());	
	                request.setAttribute("selectedBranchId", office.getParent());
	                request.setAttribute("isEdit", true);
	                request.setAttribute("officePhones", phoneDAO.getOfficePhones(office.getIdoffice()));
	                request.setAttribute("officePersons", personDAO.personsByOffice(office.getIdoffice()));
	                request.getRequestDispatcher(PagesHelper.OFFICE_FORM).forward(request, response);
	            } else if (add) {
	                Office office = new Office();
	                office.setIsbranch(0);	                
	                request.setAttribute("office", office);
	                	                
	                request.setAttribute("branchList", branchDAO.branchList());
	                request.getRequestDispatcher(PagesHelper.OFFICE_FORM).forward(request, response);
	            }
	            else {
	            request.setAttribute("officeList", officeDAO.officeList());
	            request.getRequestDispatcher(PagesHelper.OFFICE_LIST).forward(request, response);
	                    }
	     }
	            
	        } else if (method.equals("POST")) {
	        	if(path.equals("/office-add-phone")){
	        		String strId = request.getParameter("idoffice");	     
	        		phoneDAO.addOfficePhone(Integer.valueOf(strId), Integer.valueOf(request.getParameter("phone")), request.getParameter("tel"));
	        		response.sendRedirect(request.getContextPath()+"/office?edit="+strId);
	        		return;
	        }
	        else {
	            String strId = request.getParameter("idoffice");
	            Office updatedOffice = strId == null || strId.isEmpty() ? new Office(): officeDAO.getOffice(Integer.valueOf(strId));
	            updatedOffice.setName(request.getParameter("name"));
	            updatedOffice.setParent(Integer.valueOf(request.getParameter("branchId")));
	            String errors = MyValidator.validate(updatedOffice);
	            
	            if(!errors.isEmpty())  
	            {
	            	request.setAttribute("errors", errors);
	            	request.setAttribute("branchList", branchDAO.branchList());
	            	request.setAttribute("selectedBranchId", request.getParameter("branchId"));
	            	request.getRequestDispatcher(PagesHelper.OFFICE_FORM).forward(request, response);
	            } else {
		            officeDAO.saveOrUpdate(updatedOffice);
		            response.sendRedirect(request.getServletContext().getContextPath()+"/office");
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
