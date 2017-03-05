package com.model.file;

import java.io.File;
import java.util.Date;

public class MyFile {
	private int id;
	private String fileName;  //1
	private int businessId;   //2
	private int userId;       //3
	private Date uploadDate;
	private File uploadFile;
	private String root;
	public void MyFile(){
		uploadDate=new Date();
	}
	
	
	
	
	
	
	
	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getRoot() {
		return root;
	}
	public void setRoot(String root) {
		this.root = root;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setUserId(String userId){
		int uid=Integer.parseInt(userId);
		this.userId=uid;
	}
}
