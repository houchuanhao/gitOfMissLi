package com.model.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.model.HibernateUtils;

public class FileServiceImpl implements FileService {


	
	private static FileDao fileDao =new FileDaoImpl();
	
	
	
	
	
	
	
	@Override
	public List<Object> getFileByBId(String bId){
		int id=Integer.parseInt(bId);
		return fileDao.getByBId(id);
		
	}
@Override
	public List<MyFile> getFileByUId(String uId) {
		int id=Integer.parseInt(uId);
		return fileDao.getByUId(id);
		// TODO Auto-generated method stub
	}
	@Override
	public int getByFName(String fileName) {
		return fileDao.getByFName(fileName);
	}
	@Override
	public String save(MyFile myFile) throws IOException {
		myFile.setUploadDate(new Date());
		String fileName=myFile.getFileName();
		String str=fileName;
		int n=fileName.length();
		if(!fileName.contains(".")){  //没有扩展名
			for(int i=0;;i++){  //确保没有重复文件
				if(getByFName(fileName)>=1){   //有重复文件
					fileName=str+String.valueOf(i);
				}
				else
				{
					break;
				}
			}
		}
		else{  //有扩展名
			int state=fileName.indexOf(".");
			String firstName=fileName.substring(0,state);
			String lastName=fileName.substring(state);
			for(int i=0;;i++){  //确保没有重复文件
				if(getByFName(fileName)>=1){   //有重复文件
					fileName=firstName+String.valueOf(i)+lastName;
				}
				else
				{
					break;
				}
			}
		}
        InputStream is = new FileInputStream(myFile.getUploadFile());
        
        OutputStream os = new FileOutputStream(new File(myFile.getRoot(), fileName));	
		
        byte[] buffer = new byte[500];
        int length = 0;
        
        while(-1 != (length = is.read(buffer, 0, buffer.length)))
        {
            os.write(buffer);
        }
        
        os.close();
        is.close();
		
		myFile.setFileName(fileName);
		fileDao.save(myFile);
		
		
		
		
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int fileId) {
		// TODO Auto-generated method stub
		MyFile file=new MyFile();
		file.setId(fileId);
		HibernateUtils.delete(file);
	}


	@Override
	public void delete(MyFile myFile) { //已经设置id
		HibernateUtils.delete(myFile);
		// TODO Auto-generated method stub
		
	}
	@Override
	public MyFile getById(int id) {
		return (MyFile)HibernateUtils.findById(MyFile.class, id);
		// TODO Auto-generated method stub
	}




}
