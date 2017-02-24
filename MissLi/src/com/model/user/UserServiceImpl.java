package com.model.user;

import java.util.List;
//Service位于业务逻辑层,依赖于DAO层的组件，无需关注底层的实现细节，专注于业务逻辑的处理
public class UserServiceImpl implements UserService{
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
	public User getUserByNameAndPwd(String username, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isNameVaild(String username) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
