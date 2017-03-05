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
					Configuration cfg = new Configuration().configure();//����configure()���������ǿ���������Դ������в鿴���������·  
			//�����Զ�ȥ����һ��Ĭ�ϵ�hibernate.cfg.xml�ļ�����������뻻�������֣�����ʹ�������ط�����������Բ鿴��Դ���루���ص�ѹ�������У�  
			        SessionFactory factory = cfg.buildSessionFactory();  
			        
			        
			        BusinessBean businessBean=new BusinessBean();
			        businessBean.setIntroduce("�����õ�");
			        businessBean.setBusinessId(123);
			        Session session = null;  
			        Transaction tx = null;  
			        try {  
			            session = factory.openSession();  
			            tx = session.beginTransaction();//��������  
			            session.save(businessBean);//���б���  
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
}
