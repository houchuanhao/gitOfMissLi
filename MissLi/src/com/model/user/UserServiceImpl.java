package com.model.user;

import java.util.List;
//Serviceλ��ҵ���߼���,������DAO�������������ע�ײ��ʵ��ϸ�ڣ�רע��ҵ���߼��Ĵ���
/*
 * ͨ������UserDao��ʵ��UserService�ӿ�,UserServiceImpl��UserDaoImplֻʵ������Ӧ�Ľӿ�
 * ��û�г�Ա����,����Ҫʵ�ֽӿڣ����Գ�Ա��������ʹ��̬��
*/
public class UserServiceImpl implements UserService{
	//
	private static UserDao userDao=new UserDaoImpl();  //DAO�����ݷ��ʲ㣩��
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
		if(user==null){  //user==null �û���Ϊ��
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
