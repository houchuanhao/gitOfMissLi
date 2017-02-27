package com.model.business;

import java.util.List;

import com.model.user.User;

public interface BusinessDao {
	int add(Business us); //添加事务
	int del(String businessName,String start);//通过事务名，开始日期删除事务
	int rename(String oldName,String newName);//修改事务名
	List<Business> getByUser(String userName);  //通过用户名获取事务
	List<Business> getByBName(String businessname); //通过事务名获取事务
	
	/*
	int save(User user) throws Exception;
	List<User> findall() throws Exception;  //获取所有用户
	User getByName(String userName) throws Exception;
	User getByNameAndPwd(String userName,String password) throws Exception;
	int modifyUser(User user);
	*/
}
