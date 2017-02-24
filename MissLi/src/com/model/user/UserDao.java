package com.model.user;

import java.util.List;

public interface UserDao {  //dao层负责与持久化对象的交互，封装了数据的增删改查等操作
	int save(User user) throws Exception;
	List<User> findall() throws Exception;  //获取所有用户
	User getByName(String userName) throws Exception;
	User getByNameAndPwd(String userName,String userPwd) throws Exception;
}
