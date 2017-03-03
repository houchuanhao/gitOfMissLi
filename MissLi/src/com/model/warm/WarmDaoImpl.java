package com.model.warm;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.model.HibernateUtils;

public class WarmDaoImpl implements WarmDao{

	
	
	@Override
	public void save(Warm warm) {
		// TODO Auto-generated method stub
		HibernateUtils.add(warm);
	}

	@Override
	public void del(Warm warm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(Warm warm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Warm> getByUId(String id) {
		// TODO Auto-generated method stubon
		Session session=HibernateUtils.getSession();
		Query query=session.createQuery("from Warm where userId="+id);
		return (List<Warm>)query.list();
	}

	@Override
	public List<Warm> getById(int id) {
		return (List<Warm>)HibernateUtils.findById(Warm.class, id);
		// TODO Auto-generated method stub
	}

	@Override
	public List<Warm> getByBid(String id) {
		// TODO Auto-generated method stubon
		Session session=HibernateUtils.getSession();
		Query query=session.createQuery("from Warm where businessId="+id);
		return (List<Warm>)query.list();
	}

}
