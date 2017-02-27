package com.model.file;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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

public class FileAction extends ActionSupport
{
    private String username;
    
    //注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
    private File file;
    
    //提交过来的file的名字
    private String fileFileName;
    
    //提交过来的file的MIME类型
    private String fileContentType;

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
    
    public String execute() throws Exception
    {
    	HttpServletRequest request=ServletActionContext.getRequest();
    	username=(String) request.getSession().getAttribute("userName");
        String root = ServletActionContext.getServletContext().getRealPath("/upload");
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        InputStream is = new FileInputStream(file);
        
        OutputStream os = new FileOutputStream(new File(root, fileFileName));	
        
        System.out.println("fileFileName: " + fileFileName);
        // 因为file是存放在临时文件夹的文件，我们可以将其文件名和文件路径打印出来，看和之前的fileFileName是否相同
        System.out.println("file: " + file.getName());
        System.out.println("file: " + file.getPath());
        
        byte[] buffer = new byte[500];
        int length = 0;
        
        while(-1 != (length = is.read(buffer, 0, buffer.length)))
        {
            os.write(buffer);
        }
        
        os.close();
        is.close();
        
        return SUCCESS;
    }
}
