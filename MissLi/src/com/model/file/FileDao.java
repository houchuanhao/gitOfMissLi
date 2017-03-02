package com.model.file;

import java.util.List;

import com.model.user.User;

public interface FileDao {
	int save(File file);   //添加文件
	List<File> getByBId(int id);  //事务的所有文件
	List<File> getByUId(int id); //用户的所有文件
	int del(File file); //删除某个文件
}
/*
 * 	int save(User user) throws Exception;
	List<User> findall() throws Exception;  //获取所有用户
	User getByName(String userName) throws Exception;
	User getByNameAndPwd(String userName,String password) throws Exception;
	int modifyUser(User user);
 * 
 * 
 * 
 * */
