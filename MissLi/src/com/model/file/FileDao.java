package com.model.file;

import java.io.File;
import java.util.List;

import com.model.user.User;

public interface FileDao {
	int save(MyFile file);   //添加文件
	List<Object> getByBId(int id);  //事务的所有文件
	List<MyFile> getByUId(int id); //用户的所有文件
	int del(MyFile file); //删除某个文件
	int save(MyFile file,String fileName,int businessId,int userId);
	int getByFName(String fileName);
	int save(File file, String fileName, int businessId, int userId);
	
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
