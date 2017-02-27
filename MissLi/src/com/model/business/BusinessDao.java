package com.model.business;

import java.util.List;

import com.model.user.User;

public interface BusinessDao {
	int add(Business us); //�������
	int del(String businessName,String start);//ͨ������������ʼ����ɾ������
	int rename(String oldName,String newName);//�޸�������
	List<Business> getByUser(String userName);  //ͨ���û�����ȡ����
	List<Business> getByBName(String businessname); //ͨ����������ȡ����
	
	/*
	int save(User user) throws Exception;
	List<User> findall() throws Exception;  //��ȡ�����û�
	User getByName(String userName) throws Exception;
	User getByNameAndPwd(String userName,String password) throws Exception;
	int modifyUser(User user);
	*/
}
