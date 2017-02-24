package com.model.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.db.*;
public class UserDaoImpl implements UserDao{

	@Override
	public int save(User user) throws Exception {  //
		if(user==null)
			return 0;
		String sql="insert into user('username','password') values(?,?)";
		List<Object> paramList=new ArrayList<Object>();
		paramList.add(user.getUsername());
		paramList.add(user.getPassword());
		int result=DbUtil.execute(sql,paramList);
		if(result>=1){
			user=getByName(user.getUsername());
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public List<User> findall() throws Exception {
		String sql="select * from user";
		List<User> userList=getUserList(sql,null);
		return userList;
		// TODO Auto-generated method stub
		//return null;
	}

	@Override
	public User getByName(String userName) throws Exception {
		// TODO Auto-generated method stub
		String sql="select * from student where username=?";
		List<Object>paramList=new ArrayList<Object>();
		paramList.add(userName.trim());
		List<User> username=getUserList(sql,paramList);
		return null;
	}

	@Override
	public User getByNameAndPwd(String userName, String userPwd) {
		// TODO Auto-generated method stub
		return null;
	}
	private List<User> getUserList(String sql,List<Object> paramList) throws Exception{
		List<User> userList=new ArrayList<User>();
		List<Map<String,String>> userMapList =DbUtil.getQueryList(sql, paramList);
		
		
		return null;
		
	}

}
