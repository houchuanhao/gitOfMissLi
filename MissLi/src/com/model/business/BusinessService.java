package com.model.business;

import java.util.List;

import com.model.user.User;

public interface BusinessService {

	int add(String businessName,String userName,String introduce); //�������
	int del(String businessName,String start);//ͨ������������ʼ����ɾ������
	int rename(String oldName,String newName);//�޸�������
	List<Business> getByUser(String userName);  //ͨ���û�����ȡ����
	List<Business> getByUser(User user);  //ͨ���û�����ȡ����
	List<Business> getByBName(String businessname); //ͨ����������ȡ����
	List<Business> getByBId(String id);
}
