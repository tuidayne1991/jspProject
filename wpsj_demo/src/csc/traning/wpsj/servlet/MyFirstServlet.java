package csc.traning.wpsj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * 
 * @author dangthehuynh
 *
 */
public class MyFirstServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        /* TODO output your page here*/
        out.println("<html>");
        out.println("<head>");
        out.println("<title>HelloServlets</title>");
        out.println("</head>");
        out.println("<body>");
        //Displays "HelloServlets" on the screen
        out.println("<h1>HelloServlets - this is my first Servlet.</h1>");
       
        out.println("<h2>Last Access: " + Calendar.getInstance().getTime().toString() + "</h2>");
        out.println("</body>");
        out.println("</html>");
        out.close();       
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}

}
