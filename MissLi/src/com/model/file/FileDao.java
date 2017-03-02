package com.model.file;

import java.util.List;

import com.model.user.User;

public interface FileDao {
	int save(File file);   //����ļ�
	List<File> getByBId(int id);  //����������ļ�
	List<File> getByUId(int id); //�û��������ļ�
	int del(File file); //ɾ��ĳ���ļ�
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
