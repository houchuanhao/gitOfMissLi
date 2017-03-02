package com.model.business;

import java.util.List;

import com.model.user.User;

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
		return businessDao.getByUser(userName);  //通过用户名获取事务
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

	@Override
	public List<Business> getByUser(User user) {
		String userName=user.getUsername();
		return businessDao.getByUser(userName);  //通过用户名获取事务
		// TODO Auto-generated method stub
	}

	@Override
	public List<Business> getByBId(String id) {
		// TODO Auto-generated method stub
		int Id=Integer.parseInt(id);
		return businessDao.getByBId(Id);
	}
}
