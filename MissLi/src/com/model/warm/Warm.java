package com.model.warm;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.model.file.MyFile;

//添加事务，删除事务，添加提醒
public class Warm {
	private int id;
	private int businessId;
	private int userId;
	private Date build;
	private Date begin;
	private String warmName;
	private String introduce;
	
	
	
	public static void main(String args[]){
		// String root = ServletActionContext.getServletContext().getRealPath("/upload");
		 
				//这里千万注意，不能写成Configuration cfg = new Configuration();否则会报Hibernate Dialect must be explicitly set 错误信息将我们误导  
				//；实际上前面我们已经配置了mysql的方言；  
				        Configuration cfg = new Configuration().configure();//对于configure()方法，我们可以引入其源代码进行查看，其会在类路  
				//径下自动去加载一个默认的hibernate.cfg.xml文件；如果我们想换其他名字，可以使用其重载方法，具体可以查看其源代码（下载的压缩包中有）  
				        SessionFactory factory = cfg.buildSessionFactory();  
				        
				        
				        Warm warm=new Warm();
				        warm.setIntroduce("hello");
				        Session session = null;  
				        Transaction tx = null;  
				        try {  
				            session = factory.openSession();  
				            tx = session.beginTransaction();//开启事务  
				            session.save(warm);//进行保存  
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
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public Date getBuild() {
		return build;
	}
	public void setBuild(Date build) {
		this.build = build;
	}
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getWarmName() {
		return warmName;
	}
	public void setWarmName(String warmName) {
		this.warmName = warmName;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	
}
