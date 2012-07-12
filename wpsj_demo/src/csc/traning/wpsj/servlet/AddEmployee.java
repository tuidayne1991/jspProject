package csc.traning.wpsj.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.ws.util.StringUtils;

import csc.traning.wpsj.dao.EmployeesDB;
import csc.traning.wpsj.domain.Employee;


/**
 * Servlet implementation class AddEmployee
 * @author dangthehuynh
 */
public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		List<String> messages = new ArrayList<String>();
		
		// In case of updating Employee
		if(action.equals("edit")){
			Employee e = new Employee(request.getParameter("firstName"),
					request.getParameter("lastName"),
					Integer.parseInt(request.getParameter("employeeId")),
					request.getParameter("cellPhone"),
					request.getParameter("department"),
					Integer.parseInt(request.getParameter("sex")));
			request.setAttribute("employeeInfo", e);
			if("".equals(e.getFirstName()))
				messages.add("First Name is required field.");
			 
			if("".equals(e.getLastName()))
				messages.add("Last Name is required filed.");
			if(messages.size() > 0){
				request.setAttribute("messages", messages);
				RequestDispatcher dispatcher = request.getRequestDispatcher("editEmployee.jsp");
			    dispatcher.forward(request, response);
			}else{
				try {
					EmployeesDB.updateEmployee(e);
					
					messages.add("Updated successfully.");
					request.setAttribute("messages", messages);
					RequestDispatcher dispatcher = request.getRequestDispatcher("view-employees");
				    dispatcher.forward(request, response);
				} catch (Exception e1) {
					throw new ServletException(e1.getMessage());
				}
			}
			
		}else if(action.equals("confirm")){
			//List<String> messages = new ArrayList<String>();
			Employee e = new Employee(request.getParameter("firstName"),
					request.getParameter("lastName"),
					genEmployeeId(),
					request.getParameter("cellPhone"),
					request.getParameter("department"),
					Integer.parseInt(request.getParameter("sex")));
			
			request.getSession().setAttribute("employeeInfo", e);
			
			if("".equals(e.getFirstName()))
				messages.add("First Name is required field.");
			 
			if("".equals(e.getLastName()))
				messages.add("Last Name is required filed.");
				
			if(messages.size() > 0){
				request.setAttribute("messages", messages);
				RequestDispatcher dispatcher = request.getRequestDispatcher("addEmployee.jsp");
			    dispatcher.forward(request, response);
			}else{
				//request.getSession().setAttribute("employeeInfo", e);
				RequestDispatcher dispatcher = request.getRequestDispatcher("employeeConfirmInfo.jsp");
			    dispatcher.forward(request, response);
			}
			
		}else if(action.equals("add")){
			//List<String> messages = new ArrayList<String>();
			HttpSession session =  request.getSession();
			Employee e =(Employee) session.getAttribute("employeeInfo");
			if(e != null){
				try {
					EmployeesDB.addEmployee(e);
					messages.add("Inserted successfully.");
				} catch (Exception e1) {
					throw new ServletException(e1.getMessage());
				}
				// Remove employeeInfo in Session scope after adding into the Employee List
				session.removeAttribute("employeeInfo");
				
				request.setAttribute("messages", messages);
				RequestDispatcher dispatcher = request.getRequestDispatcher("view-employees");
			    dispatcher.forward(request, response);
				//response.sendRedirect("view-employees");
			}else{
				throw new ServletException("Employee Not Found in Session scope any more.");
			}
		}
		
	}
	
	private int genEmployeeId(){
		List<Employee> employees;
		try {
			employees = EmployeesDB.getEmployeeList();
			if(employees != null && employees.size() > 0){
				int id = employees.get(0).getEmployeeId();
				for(Employee e:employees){
					if(e.getEmployeeId() > id){
						id = e.getEmployeeId();
					}
				}
				return id+1;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		return 1;
	}

}
