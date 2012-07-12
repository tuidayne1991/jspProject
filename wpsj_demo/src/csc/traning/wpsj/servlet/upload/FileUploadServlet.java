package csc.traning.wpsj.servlet.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import csc.traning.wpsj.domain.User;

 
 /**
  * 
  * @author dangthehuynh
  *
  */
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String TMP_DIR_PATH = "";
	private File tmpDir;
	private static String DESTINATION_DIR_PATH ="";
	private File destinationDir;
	private Integer sizeThreshold;
	private long limitCapacity;
 
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		sizeThreshold = Integer.valueOf(getServletConfig().getInitParameter("sizeThreshold"));
		limitCapacity = Integer.valueOf(getServletConfig().getInitParameter("limitCapacity"));
		limitCapacity = limitCapacity*1024*1024;
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
	    response.setContentType("text/plain");
	    
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			throw new ServletException("Required to login");
		}
		
		TMP_DIR_PATH = "users/" + user.getUserName() + "/tmp";
		DESTINATION_DIR_PATH = "users/" + user.getUserName() + "/upload-folder";
		
		String realTmpPath = getServletContext().getRealPath(TMP_DIR_PATH);
		tmpDir = new File(realTmpPath);
		if(!tmpDir.isDirectory()) {
			tmpDir.mkdirs();
		}
		
		String realUploadPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
		destinationDir = new File(realUploadPath);
		if(!destinationDir.isDirectory()) {
			destinationDir.mkdirs();
		}
		
		
		DiskFileItemFactory  fileItemFactory = new DiskFileItemFactory ();
		/*
		 *Set the size threshold, above which content will be stored on disk.
		 */
		fileItemFactory.setSizeThreshold(sizeThreshold*1024*1024);
		/*
		 * Set the temporary directory to store the uploaded files of size above threshold.
		 */
		fileItemFactory.setRepository(tmpDir);
 
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		try {

			//Parse the request to get the files
			List items = uploadHandler.parseRequest(request);
			Iterator itr = items.iterator();
			while(itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if(item.getSize() > limitCapacity){
					throw new ServletException(item.getName()+ "[" + item.getSize()/1024/1024+"MB] exceed limit capacity " 
							+limitCapacity/1024/1024);
				}

				//Write file to the real Dir.
				String fileName = item.getName();
				File file = new File(destinationDir,fileName.substring(fileName.lastIndexOf("\\")));
				item.write(file);
				
				out.print("Uploaded successfully. Field Name = "+item.getFieldName()+
						", File Name = "+item.getName()+
						", Content type = "+item.getContentType()+
						", File Size = "+item.getSize());
				out.println("Destination Dir: " + destinationDir);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("ViewFileFolderServlet");
		    dispatcher.forward(request, response);
		    
		}catch(FileUploadException ex) {
			log("Error encountered while parsing the request",ex);
			throw new ServletException(ex.getMessage());
		} catch(Exception ex) {
			log("Error encountered while uploading file",ex);
			throw new ServletException(ex.getMessage());
		}finally{
			out.close();
			try {
				deleteTmpFiles(tmpDir);
			} catch (Exception e) {
				throw new ServletException(e.getMessage());
			}
		}
 
	}
	
	private void deleteTmpFiles(File tmpDir) throws Exception{
		// Get all files in directory
		File[] files = tmpDir.listFiles();
		for (File file : files){
			// Delete each file
			if (!file.delete()){
				// Failed to delete file
				throw new Exception("Failed to delete "+file);
			}
		}
		
	}
}