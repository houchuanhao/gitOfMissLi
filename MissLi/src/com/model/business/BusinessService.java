package com.model.business;

import java.util.List;

public interface BusinessService {

	int add(String businessName,String userName,String introduce); //添加事务
	int del(String businessName,String start);//通过事务名，开始日期删除事务
	int rename(String oldName,String newName);//修改事务名
	List<Business> getByUser(String userName);  //通过用户名获取事务
	List<Business> getByBName(String businessname); //通过事务名获取事务
	
}
