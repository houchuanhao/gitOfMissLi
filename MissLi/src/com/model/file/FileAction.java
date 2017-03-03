package com.model.file;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.model.user.User;
import com.model.user.UserService;
import com.model.user.UserServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
/*
public class UploadFileAction extends ActionSupport{
	private File sourceFile;
	private String fileName;
	private String contentType;
	public File getSourceFile() {
		return sourceFile;
	}
	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;		
	}
	public String execute(){
		if(sourceFile!=null){
			String realPath=ServletActionContext.getServletContext().getRealPath("/UploadFiles");
			File targetFile=new File(realPath,getFileName());  //目标文件
			try {
				FileUtils.copyFile(sourceFile, targetFile); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			addActionMessage("选择文件");
		}
		System.out.println("执行execute"+ServletActionContext.getServletContext().getRealPath("/UploadFiles"));
		return SUCCESS;
	}
}
*/

public class FileAction extends ActionSupport implements ServletRequestAware
{
	private MyFile myFile;
	
    private String username;
    
    //注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
    private File file;
    
    //提交过来的file的名字
    private String fileFileName;
    
    public HttpServletRequest getServletRequest() {
		return servletRequest;
	}
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	//提交过来的file的MIME类型
    private String fileContentType;

	private static FileService fileService=new FileServiceImpl();
	
	protected HttpServletRequest servletRequest=null;
	
    public void FileAction(){
    	myFile=new MyFile();
    }
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public File getFile()
    {
        return file;
    }

    public void setFile(File file)
    {
        this.file = file;
    }

    public String getFileFileName()
    {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName)
    {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType()
    {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType)
    {
        this.fileContentType = fileContentType;
    }
    
    public String upload() throws Exception  //先调用service保存到数据库,再保存到本地
    {
    	
    	HttpSession session=servletRequest.getSession();
    	User user=(User) session.getAttribute("user");
    	int businessId=Integer.parseInt(servletRequest.getParameter("id"));
    	
    	String root = ServletActionContext.getServletContext().getRealPath("/upload");
    	MyFile myFile =new MyFile();
    	myFile.setUserId(user.getId());
    	myFile.setBusinessId(businessId);
    	myFile.setFileName(fileFileName);
    	myFile.setUploadFile(file);
    	myFile.setRoot(root);
    	fileService.save(myFile);
        System.out.println("file: " + file.getName());
       // System.out.println("file: " + file.getPath());
        return SUCCESS;
    }
    public String del(){
    	int fId=Integer.parseInt(servletRequest.getParameter("fId"));
    	MyFile myFile =new MyFile();
    	myFile.setId(fId);
    	fileService.delete(myFile);
    	int  businessId=Integer.parseInt(servletRequest.getParameter("id"));
    	HttpServletRequest request =ServletActionContext.getRequest();
    	List<MyFile> fileList=new ArrayList<MyFile>();
    	fileList=fileService.getFileByBId(String.valueOf(businessId));
    	
    	/*
    	for(int i=0;i<fileList.size();i++){
    		System.out.println(fileList.get(i).getFileName());
    	}
    	*/
    	request.setAttribute("fileList", fileList);
    	
    	return "download";
    }
    public String download(){
    	int  businessId=Integer.parseInt(servletRequest.getParameter("id"));
    	HttpServletRequest request =ServletActionContext.getRequest();
    	List<MyFile> fileList=new ArrayList<MyFile>();
    	fileList=fileService.getFileByBId(String.valueOf(businessId));
    	
    	/*
    	for(int i=0;i<fileList.size();i++){
    		System.out.println(fileList.get(i).getFileName());
    	}
    	*/
    	request.setAttribute("fileList", fileList);
    	
    	return "download";
    }
}
