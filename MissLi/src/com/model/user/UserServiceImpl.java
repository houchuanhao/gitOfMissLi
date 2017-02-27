package com.model.user;

import java.util.List;
//Service位于业务逻辑层,依赖于DAO层的组件，无需关注底层的实现细节，专注于业务逻辑的处理
/*
 * 通过调用UserDao来实现UserService接口,UserServiceImpl与UserDaoImpl只实现了相应的接口
 * 并没有成员变量,由于要实现接口，所以成员函数不能使静态的
*/
public class UserServiceImpl implements UserService{
	//
	private static UserDao userDao=new UserDaoImpl();  //DAO（数据访问层）层
	@Override
	public int addUser(User user) throws Exception {
		if(user==null)
			return 0;
		else{
			return userDao.save(user);
		}// TODO Auto-generated method stub
	}

	@Override
	public User getUserByNameAndPwd(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		User user=userDao.getByNameAndPwd(username, password);
		return user;
	}

	@Override
	public boolean isNameVaild(String username) throws Exception {
		// TODO Auto-generated method stub
		User user=userDao.getByName(username);
		if(user==null){  //user==null 用户名为空
			return true;
		}
		else
			return false;
	}

	@Override
	public List<User> findAll() throws Exception {
		//User user=new User();
		return userDao.findall();
		// TODO Auto-generated method stub
		//return null;
	}

	@Override
	public User getUserByName(String userName) throws Exception {
		// TODO Auto-generated method stub
		User user=userDao.getByName(userName);
		return null;
	}

	@Override
	public int modifyEmail(String userName, String email) {
		// TODO Auto-generated method stub
		
		return 0;
	}

}
