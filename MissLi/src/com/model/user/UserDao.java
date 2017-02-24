package com.model.user;

import java.util.List;

public interface UserDao {  //dao�㸺����־û�����Ľ�������װ�����ݵ���ɾ�Ĳ�Ȳ���
	int save(User user) throws Exception;
	List<User> findall() throws Exception;  //��ȡ�����û�
	User getByName(String userName) throws Exception;
	User getByNameAndPwd(String userName,String userPwd) throws Exception;
}
