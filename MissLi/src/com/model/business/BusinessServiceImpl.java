package com.model.business;

import java.util.List;

public class BusinessServiceImpl implements BusinessService{
	private static BusinessDao businessDao=new BusinessDaoImpl();



	@Override
	public int del(String businessName, String start) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int rename(String oldName, String newName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Business> getByUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Business> getByBName(String businessname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(String businessName, String userName, String introduce) {
		Business bs=new Business();
		bs.setBusinessName(businessName);
		bs.setUserName(userName);
		bs.setIntroduce(introduce);
		return businessDao.add(bs);
		// TODO Auto-generated method stub
	}
}
