package com.model.attribute;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.model.HibernateUtils;
import com.model.businessBean.BusinessBean;

public class Attribute {
	private int id;
	
	//��֪businessName  select * from Business,Attribute where Attribute.businessName='2213' and Attribute.businessId=Business.id 
	private String attributeName;   //����
	private String businessName;  //����
	private String val; //ֵ
	private int businessId;  //����id
	private String type;//ʱ�䣬����
	
	
	
	
//������get set
	
	public int getId() {
		return id;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public static void main(String args[]){
		Attribute attribute=new Attribute();
		attribute.setType("value");
		HibernateUtils.add(attribute);
	}
	
	
	
}
