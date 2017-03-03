package com.model.warm;

public class WarmServiceImpl implements WarmService{

	private static WarmDao warmDao=new WarmDaoImpl();
	@Override
	public void add(Warm warm) {
		// TODO Auto-generated method stub
		warmDao.save(warm);
	}
	
}
