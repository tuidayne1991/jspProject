package csc.traning.wpsj.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csc.traning.wpsj.dao.EmployeesDB;
import csc.traning.wpsj.domain.Employee;


/**
 * Servlet implementation class EditEmployee
 * @author dangthehuynh
 */
public class EditEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String employeeId = request.getParameter("employeeId"); 
		Employee e;
		try {
			e = EmployeesDB.getEmployeeById(Integer.valueOf(employeeId));
		} catch (NumberFormatException e1) {
			throw new ServletException(e1.getMessage());
		} catch (Exception e1) {
			throw new ServletException(e1.getMessage());
		}
		request.setAttribute("employeeInfo", e);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("editEmployee.jsp");
		dispatcher.forward(request, response);
		
		//response.sendRedirect("editEmployee.jsp?employeeId="+request.getParameter("employeeId"));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
