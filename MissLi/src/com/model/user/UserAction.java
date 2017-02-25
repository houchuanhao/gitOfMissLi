package com.model.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements ServletRequestAware{

	private String username;
	private String password;
	private String cinfirmPassword;
	private User user;
	private static UserService userService=new UserServiceImpl();
	protected HttpServletRequest servletRequest=null;
	//-----------------下面是6个成员函数
	//1
	public String register() {  //调用此函数之前之前需要确定用户名可用
		User registingUser=new User();
		registingUser.setUsername(username);
		registingUser.setPassword(password);
		try {
			userService.addUser(user);  //此处执行注册时并没有验证用户名是否存在
		} catch (Exception e) {
			servletRequest.setAttribute("fall", "在UserAction.register处出错");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setUser(registingUser);
		return SUCCESS;
	}
	//2
	public String login(){
		User userLog=null;
		try {
			userLog=userService.getUserByNameAndPwd(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(userLog==null){ //用户名或者密码错误
			addActionError(getText("errors.userNameOrPassword"));
			return INPUT;
		}
		setUser(userLog);
		servletRequest.getSession().setAttribute("user", userLog);
		return SUCCESS;
	}
	//3
	public String logout(){
		servletRequest.getSession().setAttribute("user", null);
		return SUCCESS;
	}
	//4   用于获取所以得用户
	public String list() throws Exception{
		List<User> userList=null;
		userList=userService.findAll(); 
		servletRequest.setAttribute("userList", userList);
		return "userList";
	}
	//5  
	public String checkUser() throws Exception{
		String userName=servletRequest.getParameter("userName");
		boolean isNameVaild=userService.isNameVaild(userName);
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setHeader("Cache-control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html");
		response.getWriter().write("{\"isNameVaild\":"+isNameVaild+"}");
		return null;
	}
	public String input(){
		return INPUT;
	}// 
	@Override
	public void setServletRequest(HttpServletRequest request){
		this.servletRequest=request;
	}
	
	
	
	
	
	
	
	
	
	
	
	//------------下面是get与set
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCinfirmPassword() {
		return cinfirmPassword;
	}
	public void setCinfirmPassword(String cinfirmPassword) {
		this.cinfirmPassword = cinfirmPassword;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public static UserService getUserService() {
		return userService;
	}
	public static void setUserService(UserService userService) {
		UserAction.userService = userService;
	}
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

}
