package com.model.user;

import java.util.List;

public interface UserService {
	int addUser(User user) throws Exception;
	int modifyEmail(String userName,String email);
	User getUserByNameAndPwd(String username,String pwd) throws Exception;
	boolean isNameVaild(String username) throws Exception;
	List<User> findAll() throws Exception;
	User getUserByName(String username) throws Exception;
}
