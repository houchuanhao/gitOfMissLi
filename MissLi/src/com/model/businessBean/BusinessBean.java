package com.model.businessBean;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.model.business.Business;
import com.model.warm.Warm;

public class BusinessBean {
	
	private int id;
	
	
	
	private int businessId;
	private Date begin;
	private String introduce;
	
	
	
	
	
	
	
	private Business business;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}





















	public static void main(String args[]){
					Configuration cfg = new Configuration().configure();//对于configure()方法，我们可以引入其源代码进行查看，其会在类路  
			//径下自动去加载一个默认的hibernate.cfg.xml文件；如果我们想换其他名字，可以使用其重载方法，具体可以查看其源代码（下载的压缩包中有）  
			        SessionFactory factory = cfg.buildSessionFactory();  
			        
			        
			        BusinessBean businessBean=new BusinessBean();
			        businessBean.setIntroduce("测试用的");
			        businessBean.setBusinessId(123);
			        Session session = null;  
			        Transaction tx = null;  
			        try {  
			            session = factory.openSession();  
			            tx = session.beginTransaction();//开启事务  
			            session.save(businessBean);//进行保存  
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
}
