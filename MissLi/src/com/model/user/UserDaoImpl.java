package com.model.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.db.*;
public class UserDaoImpl implements UserDao{

	@Override
	public int save(User user) throws Exception {  //
		if(user==null)
			return 0;
		String sql="insert into user(username,password) values(?,?)";
		System.out.println(sql);
		List<Object> paramList=new ArrayList<Object>();
		paramList.add(user.getUsername());
		paramList.add(user.getPassword());
		//paramList.add(user.getEmail());
		System.out.print(paramList.toString());
		int result=DbUtil.execute(sql,paramList);
		System.out.print(result);
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
		String sql="select * from user where username=?";
		List<Object>paramList=new ArrayList<Object>();
		paramList.add(userName);
		List<User> userList=getUserList(sql,paramList);
		if(userList==null||userList.isEmpty()){
			return null;
		}
		return userList.get(0);
	}
	@Override
	public User getByNameAndPwd(String userName, String password) throws Exception {
		// TODO Auto-generated method stub
		String sql="select * from user where userName=? and password =?";
		List<Object> paramList =new ArrayList<Object>();
		paramList.add(userName);
		
		paramList.add(password);
		
		List<User> userList=getUserList(sql,paramList);
		if(userList==null){
			return null;
		}
		else
			return userList.get(0);
		//return null;
	}
	private List<User> getUserList(String sql,List<Object> paramList) throws Exception{
		List<User> userList=new ArrayList<User>();
		List<Map<String,String>> userMapList =DbUtil.getQueryList(sql, paramList);
		if(userMapList==null||userMapList.isEmpty()){  //获取的用户数量为0
			return null; //返回一个节点数为0的List<User>类对象
		}
		for(Map<String,String> userMap:userMapList){
			Iterator<Entry<String,String>> userEntryIt=userMap.entrySet().iterator();
			User user=new User();
			while(userEntryIt.hasNext()){
				Entry<String,String> userEntry=userEntryIt.next();
				if(userEntry.getKey().equals("id")){
					user.setId(userEntry.getValue());
				}
				
				if(userEntry.getKey().equals("userName")){
					user.setUsername(userEntry.getValue());
				}
				if(userEntry.getKey().equals("password")){
					user.setPassword(userEntry.getValue());
				}
				if(userEntry.getKey().equals("email")){
					user.setEmail(userEntry.getValue());
				}
			}
			
			userList.add(user);
		}
		
		return userList;
		
	}

	@Override
	public int modifyUser(User user) {
		if(user==null)
			return 0;
		String sql="update user set password=?,email=?  where userName=?";
		//String sql="insert into user(username,password) values(?,?)";
		System.out.println(sql);
		List<Object> paramList=new ArrayList<Object>();
		paramList.add(user.getPassword());
		paramList.add(user.getEmail());
		paramList.add(user.getUsername());
		System.out.print(paramList.toString());
		int result=0;
		try {
			result = DbUtil.execute(sql,paramList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(result);
		if(result>=1){
			try {
				user=getByName(user.getUsername());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		return result;
	}
}
