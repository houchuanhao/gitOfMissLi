package com.action;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Connecter;

public class LoginAction {
	public LoginAction(){
		
	}
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String username;
	public String execute() throws ClassNotFoundException{
		Connecter.createConnection();
		String sql="select * from user where username='"+username+"' and password='"+password+"'";
		try {
			ResultSet resultSet=Connecter.statement.executeQuery(sql);
			while(resultSet.next()){
				System.out.println(resultSet.getString("username"));
			}
			resultSet.last();
			int n=resultSet.getRow();
			resultSet.beforeFirst();
			System.out.print(n+"-----------"+sql);
			if(n>=1)  //一个用户
			{
				return "success";
			}
			else{
				return "fall";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sql;
		
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
