package com.model.file;

import java.io.File;
import java.util.List;

import com.model.user.User;

public interface FileDao {
	int save(MyFile file);   //����ļ�
	List<Object> getByBId(int id);  //����������ļ�
	List<MyFile> getByUId(int id); //�û��������ļ�
	int del(MyFile file); //ɾ��ĳ���ļ�
	int save(MyFile file,String fileName,int businessId,int userId);
	int getByFName(String fileName);
	int save(File file, String fileName, int businessId, int userId);
	
}
/*
 * 	int save(User user) throws Exception;
	List<User> findall() throws Exception;  //��ȡ�����û�
	User getByName(String userName) throws Exception;
	User getByNameAndPwd(String userName,String password) throws Exception;
	int modifyUser(User user);
 * 
 * 
 * 
 * */
