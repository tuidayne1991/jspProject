package csc.traning.wpsj.servlet.lessons;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LongServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    
    // Change the definition of "out" depending on whether
    // or not gzip is supported.
    PrintWriter out;
    if (GzipUtilities.isGzipSupported(request) &&
        !GzipUtilities.isGzipDisabled(request)) {
      out = GzipUtilities.getGzipWriter(response);
      response.setHeader("Content-Encoding", "gzip");
    } else {
      out = response.getWriter();
    }

    // Once "out" has been assigned appropriately, the
    // rest of the page has no dependencies on the type
    // of writer being used.
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    String title = "Long Page";
    out.println
      (docType +
       "<HTML>\n" +
       "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
       "<BODY BGCOLOR=\"#FDF5E6\">\n" +
       "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n");
    String line = "Blah, blah, blah, blah, blah. " +
                  "Yadda, yadda, yadda, yadda.";
    for(int i=0; i<10000; i++) {
      out.println(line);
    }
    out.println("</BODY></HTML>");
    out.close(); // Needed for gzip; optional otherwise.
  }
}