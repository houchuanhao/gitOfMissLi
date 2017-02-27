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
			File targetFile=new File(realPath,getFileName());  //Ŀ���ļ�
			try {
				FileUtils.copyFile(sourceFile, targetFile); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			addActionMessage("ѡ���ļ�");
		}
		System.out.println("ִ��execute"+ServletActionContext.getServletContext().getRealPath("/UploadFiles"));
		return SUCCESS;
	}
}
*/

public class FileAction extends ActionSupport
{
    private String username;
    
    //ע�⣬file������ָǰ��jsp�ϴ��������ļ����������ļ��ϴ������������ʱ�ļ���������ļ�
    private File file;
    
    //�ύ������file������
    private String fileFileName;
    
    //�ύ������file��MIME����
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
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
        System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
        InputStream is = new FileInputStream(file);
        
        OutputStream os = new FileOutputStream(new File(root, fileFileName));	
        
        System.out.println("fileFileName: " + fileFileName);
        // ��Ϊfile�Ǵ������ʱ�ļ��е��ļ������ǿ��Խ����ļ������ļ�·����ӡ����������֮ǰ��fileFileName�Ƿ���ͬ
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
