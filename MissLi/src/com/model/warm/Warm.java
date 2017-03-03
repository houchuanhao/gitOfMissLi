package com.model.warm;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.model.file.MyFile;

//�������ɾ�������������
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
		 
				//����ǧ��ע�⣬����д��Configuration cfg = new Configuration();����ᱨHibernate Dialect must be explicitly set ������Ϣ��������  
				//��ʵ����ǰ�������Ѿ�������mysql�ķ��ԣ�  
				        Configuration cfg = new Configuration().configure();//����configure()���������ǿ���������Դ������в鿴���������·  
				//�����Զ�ȥ����һ��Ĭ�ϵ�hibernate.cfg.xml�ļ�����������뻻�������֣�����ʹ�������ط�����������Բ鿴��Դ���루���ص�ѹ�������У�  
				        SessionFactory factory = cfg.buildSessionFactory();  
				        
				        
				        Warm warm=new Warm();
				        warm.setIntroduce("hello");
				        Session session = null;  
				        Transaction tx = null;  
				        try {  
				            session = factory.openSession();  
				            tx = session.beginTransaction();//��������  
				            session.save(warm);//���б���  
				            tx.commit();//�ύ����  
				            
				        } catch (HibernateException e) {  
				            if(tx!=null){  
				                tx.rollback();//�ع�����  
				            }  
				            throw e;//�����׳��쳣  
				        }finally{  
				            if(session!=null){  
				                session.close();//�ر�session  
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
