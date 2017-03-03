package com.model.warm;

import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Warm> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Warm> getByBid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
