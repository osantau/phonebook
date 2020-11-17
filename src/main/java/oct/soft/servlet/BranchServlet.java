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
import oct.soft.dao.BranchDAO;
import oct.soft.db.util.MyDataSource;
import oct.soft.helpers.PagesHelper;
import oct.soft.model.Office;
import oct.soft.validator.MyValidator;

/**
 *
 * @author osantau
 */
@WebServlet(name = "BranchServlet", urlPatterns = {"/branches"})
public class BranchServlet extends HttpServlet {
     
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    public void init()        
            throws ServletException {        
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BranchDAO branchDAO = new BranchDAO();        
        String method = request.getMethod();
        String path = request.getServletPath();        
        if(method.equals("GET")) {
            String editId = request.getParameter("edit");
            boolean add = request.getParameterMap().keySet().contains("add");            
            if(editId != null) 
            {
                Office branch = branchDAO.getBranch(Integer.valueOf(editId));
                request.setAttribute("branch", branch);
                request.getRequestDispatcher(PagesHelper.BRANCH_FORM).forward(request, response);
            } else if (add) {
                Office branch = new Office();
                branch.setIsbranch(1);
                branch.setParent(0);
                request.setAttribute("branch", branch);
                request.getRequestDispatcher(PagesHelper.BRANCH_FORM).forward(request, response);
            }
            else {
            request.setAttribute("branchList", branchDAO.branchList());
            request.getRequestDispatcher(PagesHelper.BRANCH_LIST).forward(request, response);
                    }
            
        } else if (method.equals("POST")) {
            String strId = request.getParameter("idoffice");
            Office updatedBranch = strId == null || strId.isEmpty() ? new Office(): branchDAO.getBranch(Integer.valueOf(strId));
            updatedBranch.setName(request.getParameter("name"));
            String errors =MyValidator.validate(updatedBranch);
            
            if(!errors.isEmpty())  
            {
            	request.setAttribute("errors", errors);
            	request.getRequestDispatcher(PagesHelper.BRANCH_FORM).forward(request, response);
            } else {
	            branchDAO.saveOrUpdate(updatedBranch);
	            response.sendRedirect(request.getServletContext().getContextPath()+"/branches");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
