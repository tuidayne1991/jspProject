package csc.traning.wpsj.domain;

import java.util.Date;

public class FileInfo {
	private String fileName;
	private String kind;
	private String sizeInfo;
	private Date lastModifiedDate;
	
	public FileInfo(String fileName, String kind, String sizeInfo,
			Date lastModifiedDate) {
		this.fileName = fileName;
		this.kind = kind;
		this.sizeInfo = sizeInfo;
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getSizeInfo() {
		return sizeInfo;
	}
	public void setSizeInfo(String sizeInfo) {
		this.sizeInfo = sizeInfo;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
