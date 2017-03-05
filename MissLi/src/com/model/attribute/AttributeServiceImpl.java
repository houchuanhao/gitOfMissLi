package com.model.attribute;

import java.util.List;

import javax.management.Query;

import com.model.HibernateUtils;

public class AttributeServiceImpl implements AttributeService{

	@Override
	public void add(Attribute attribute) {
		// TODO Auto-generated method stub
		HibernateUtils.add(attribute);
	}

	@Override
	public void del(int id) {
		Attribute attr=new Attribute();
		attr.setId(id);
		HibernateUtils.delete(attr);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(Attribute attribute) {
		// TODO Auto-generated method stub
		HibernateUtils.update(attribute);
	}

	@Override
	public List<Attribute> get(String businessName) {
		// TODO Auto-generated method stub
		Query query=new Query();
		List<Attribute> attributeList=(List<Attribute>) HibernateUtils.getSession().createQuery("from Attribute where businessName='"+businessName+"'").list();
		return attributeList;
	}
	@Override
	public List<Attribute> getByBId(int bId){ //
		Query query=new Query();
		List<Attribute> attributeList=(List<Attribute>) HibernateUtils.getSession().createQuery("from Attribute where businessId='"+bId+"'").list();
		return attributeList;
	}

}
