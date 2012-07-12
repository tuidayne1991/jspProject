package csc.traning.wpsj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xml.internal.security.Init;

import csc.traning.wpsj.dao.Counter;
import csc.traning.wpsj.dao.EmployeesDB;
import csc.traning.wpsj.dao.UserDB;
import csc.traning.wpsj.domain.User;
import csc.traning.wpsj.servlet.lessons.CookieUtilities;


/**
 * Servlet implementation class Logon
 * @author dangthehuynh
 */
public class LogonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
		//showParameters(request,response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		User user = UserDB.getUser(userName, password);
		
		if(user != null){
			// Set to remember password in Cookie
			String isRememberPass = request.getParameter("rememberme");
			if(isRememberPass!= null && isRememberPass.equals("yes")){
				Cookie useNameCookie = new Cookie("userName", request.getParameter("userName"));
				useNameCookie.setMaxAge(3600);
				response.addCookie(useNameCookie);
				
				Cookie passwordCookie = new Cookie("password", request.getParameter("password"));
				passwordCookie.setMaxAge(3600);
				response.addCookie(passwordCookie);
			}
			
			HttpSession sess = request.getSession(false);
			sess.setAttribute("user", user);
			
			// Keep track list of users are online
			Counter.add(user.getUserName());
			
			// add the numOfOnline to application scope
			sess.setAttribute("numOfOnline", Counter.size());
			String navigationPage = "ViewFileFolderServlet";
			if(user.getRole().equals("admin")){
				navigationPage = "view-employees";
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(navigationPage);
		    dispatcher.forward(request, response);
			//response.sendRedirect("view-employees");
		}else{
			request.setAttribute("message", "Wrong username/password or not match.");
			//response.sendRedirect("logon.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("logon.jsp");
		    dispatcher.forward(request, response);
		}
		    
	}
	
	private void showParameters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    String docType =
	      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
	      "Transitional//EN\">\n";
	    String title = "Reading All Request Parameters";
	    out.println(docType +
	                "<HTML>\n" +
	                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
	                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
	                "<H1 ALIGN=CENTER>" + title + "</H1>\n" +
	                "<TABLE BORDER=1 ALIGN=CENTER>\n" +
	                "<TR BGCOLOR=\"#FFAD00\">\n" +
	                "<TH>Parameter Name<TH>Parameter Value(s)");
	    @SuppressWarnings("unchecked")
	    Enumeration<String> paramNames = request.getParameterNames();
	    while(paramNames.hasMoreElements()) {
	      String paramName = paramNames.nextElement();
	      out.print("<TR><TD>" + paramName + "\n<TD>");
	      String[] paramValues =
	        request.getParameterValues(paramName);
	      if (paramValues.length == 1) {
	        String paramValue = paramValues[0];
	        if (paramValue.length() == 0)
	          out.println("<I>No Value</I>");
	        else
	          out.println(paramValue);
	      } else {
	        out.println("<UL>");
	        for(int i=0; i<paramValues.length; i++) {
	          out.println("<LI>" + paramValues[i]);
	        }
	        out.println("</UL>");
	      }
	    }
	    out.println("</TABLE>\n</BODY></HTML>");
	  
	}
	
	  private void askForPassword(HttpServletResponse response) {
		    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // I.e., 401
		    response.setHeader("WWW-Authenticate",
		                       "BASIC realm=\"Insider-Trading\"");
	  }

}
