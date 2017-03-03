package com.model.file;

import java.util.List;
import java.util.Date;  
import org.hibernate.HibernateException;  
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;  
public class FileDaoImpl implements FileDao{

	public static void main(String[] args){   
		//这里千万注意，不能写成Configuration cfg = new Configuration();否则会报Hibernate Dialect must be explicitly set 错误信息将我们误导  
		//；实际上前面我们已经配置了mysql的方言；  
		        Configuration cfg = new Configuration().configure();//对于configure()方法，我们可以引入其源代码进行查看，其会在类路  
		//径下自动去加载一个默认的hibernate.cfg.xml文件；如果我们想换其他名字，可以使用其重载方法，具体可以查看其源代码（下载的压缩包中有）  
		        SessionFactory factory = cfg.buildSessionFactory();  
		        
		        
		        File file=new File();
		        file.setFileName("测试文件");
		        file.setBusinessId(2);
		        file.setUserId(222);
		        Date date=new Date();
		        file.setUploadDate(new Date());
		        Session session = null;  
		        Transaction tx = null;  
		        try {  
		            session = factory.openSession();  
		            tx = session.beginTransaction();//开启事务  
		            session.save(file);//进行保存  
		            tx.commit();//提交事务  
		        } catch (HibernateException e) {  
		            if(tx!=null){  
		                tx.rollback();//回滚事务  
		            }  
		            throw e;//必须抛出异常  
		        }finally{  
		            if(session!=null){  
		                session.close();//关闭session  
		            }  
		        }  
		    }  
	
	
	
	
	
	
	
	@Override
	public int save(File file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<File> getByBId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<File> getByUId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int del(File file) {
		// TODO Auto-generated method stub
		return 0;
	}

}
