package com.model.user;

import java.util.List;

public interface UserService {
	int addUser(User user) throws Exception;
	User getUserByNameAndPwd(String username,String pwd) throws Exception;
	boolean isNameVaild(String username) throws Exception;
	List<User> findAll() throws Exception;
}
