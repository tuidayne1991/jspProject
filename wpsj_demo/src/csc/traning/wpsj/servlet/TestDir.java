package csc.traning.wpsj.servlet;

import java.io.File;
import java.util.Calendar;

/**
 * 
 * @author dangthehuynh
 *
 */
public class TestDir {
	public static void main(String args[]){
		String dirPath = "D:\\Aptech\\WPSJ";
		
		Calendar calendar = Calendar.getInstance();
		
		
		File file = new File(dirPath);
		File[] files = file.listFiles();
		for(File f: files){
			if(f.isFile()){
				calendar.setTimeInMillis(f.lastModified());
				System.out.println("File: "+ f.getName() + " " + f.length()/1024 + "KB " + calendar.getTime());
				//System.out.println(f.getAbsolutePath());
			}
			if(f.isDirectory()){
				System.out.println("Dir: "+ f.getName() + " Parent: " + f.getParent());
			}
			
		}
	}

}
