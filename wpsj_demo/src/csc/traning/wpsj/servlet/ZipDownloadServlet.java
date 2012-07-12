package csc.traning.wpsj.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import csc.traning.wpsj.domain.User;

/**
 * Servlet implementation class ZipDownloadServlet
 * @author dangthehuynh
 */
public class ZipDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZipDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			if(user == null){
				throw new ServletException("Required to login");
			}
			String dirPath = "users/" + user.getUserName() + "/upload-folder";
			
			String path = getServletContext().getRealPath(dirPath);
			File directory = new File(path);
			File[] files = directory.listFiles();
			if (files != null && files.length > 0) {
				byte[] zip = zipFiles(files);
				ServletOutputStream sos = response.getOutputStream();
				
				// Let Browser understand the zip file
				response.setContentType("application/zip");
				response.setHeader("Content-Disposition", "attachment; filename=\"downloads.zip\"");
				sos.write(zip);
				sos.flush();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	* Compress the given directory with all its files.
	*/
	private byte[] zipFiles(File[] files) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		byte bytes[] = new byte[2048];
		for (File f : files) {
			FileInputStream fis = new FileInputStream(f.getAbsolutePath());
			BufferedInputStream bis = new BufferedInputStream(fis);
			zos.putNextEntry(new ZipEntry(f.getName()));
			int bytesRead;
			while ((bytesRead = bis.read(bytes)) != -1) {
				zos.write(bytes, 0, bytesRead);
			}
			zos.closeEntry();
			bis.close();
			fis.close();
		}
		zos.flush();
		baos.flush();
		zos.close();
		baos.close();
		 
		return baos.toByteArray();
	}
}
