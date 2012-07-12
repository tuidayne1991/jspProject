package csc.traning.wpsj.servlet.lessons;

import java.io.*;
import javax.servlet.http.*;
import java.util.zip.*;



public class GzipUtilities {
  
  /** Does the client support gzip? */
  
  public static boolean isGzipSupported
      (HttpServletRequest request) {
    String encodings = request.getHeader("Accept-Encoding");
    return((encodings != null) &&
           (encodings.contains("gzip")));
  }

  /** Has user disabled gzip (e.g., for benchmarking)? */
  
  public static boolean isGzipDisabled
      (HttpServletRequest request) {
    String flag = request.getParameter("disableGzip");
    return((flag != null) && (!flag.equalsIgnoreCase("false")));
  }

  /** Return gzipping PrintWriter for response. */
  
  public static PrintWriter getGzipWriter
      (HttpServletResponse response) throws IOException {
    return(new PrintWriter
            (new GZIPOutputStream
              (response.getOutputStream())));
  }
}
