package com.model.warm;

import java.util.Date;
import java.util.List;

public class WarmServiceImpl implements WarmService{

	private static WarmDao warmDao=new WarmDaoImpl();
	@Override
	public void add(Warm warm) {
		warm.setBuild(new Date());
		// TODO Auto-generated method stub
		warmDao.save(warm);
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
		
		return warmDao.getByUId(id);
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
