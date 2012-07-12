package csc.traning.wpsj.servlet.upload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import csc.traning.wpsj.domain.FileInfo;
import csc.traning.wpsj.domain.User;

/**
 * Servlet implementation class ViewFileFolderServlet
 */
public class ViewFileFolderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewFileFolderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		
		if(user == null){
			throw new ServletException("Required to login");
		}
		String dirPath = "users/" + user.getUserName() + "/upload-folder";
		String path = getServletContext().getRealPath(dirPath);
		
		Calendar calendar = Calendar.getInstance();
		File file = new File(path);
		if(file.exists()){
			File[] files = file.listFiles();
			for(File f: files){
				calendar.setTimeInMillis(f.lastModified());
				FileInfo fileInfo = null;
				if(f.isFile()){
					fileInfo = new FileInfo(f.getName(),"File",f.length()/1024 + "KB",calendar.getTime());
				}else{
					fileInfo = new FileInfo(f.getName(),"Folder","-",calendar.getTime());
				}
				
				fileInfoList.add(fileInfo);
			}
		}
		request.setAttribute("fileInfoList", fileInfoList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/file_management.jsp");
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
