package com.model.user;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.jms.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.Configuration;
import com.StrUtil.JsonStr;
import com.mail.SendMail;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements ServletRequestAware{
	private String email;
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
	public String checkUser() throws Exception{  //参考范例的代码
		String userName=servletRequest.getParameter("userName");
		boolean isNameVaild=userService.isNameVaild(userName);
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setHeader("Cache-control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html");
		response.getWriter().write("{\"isNameVaild\":"+isNameVaild+"}");
		System.out.println("{\"isNameVaild\":"+isNameVaild+"}11");
		HttpServletResponse response1=ServletActionContext.getResponse();
		System.out.println(response1.getHeaderNames()+"responst1");
		return null;
	}
	public String input(){
		return INPUT;
	}
	public String confirm() throws Exception{ //验证邮箱
		
		String userName=servletRequest.getParameter("username");
		String password=servletRequest.getParameter("password");
		String email=servletRequest.getParameter("email");
		System.out.print("验证邮箱ing");
		Map<String ,String[]> map=servletRequest.getParameterMap();
		HttpServletResponse response=ServletActionContext.getResponse();
		initResponse();
		User cUser=new User();
		cUser.setEmail(email);
		cUser.setPassword(password);
		cUser.setUsername(userName);
		userService.modifyUser(cUser);
		
		return null;
	}
	//
	public String signIn(){  //登录
		String userName=servletRequest.getParameter("userName");
		String password=servletRequest.getParameter("password");
		HttpServletResponse response=getResponse();
		User loginUser=null;
		JsonStr jStr=new JsonStr();
		try {
			loginUser=userService.getUserByNameAndPwd(userName, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(loginUser==null){ //用户名或密码错误
			jStr.put("loginSuccess", false);
		}
		else  //登录成功
		{
			Cookie cookie=new Cookie("userName", userName);
			response.addCookie(cookie);
			servletRequest.getSession().setAttribute("userName", userName);
			jStr.put("loginSuccess", true);
		}
		try {
			response.getWriter().write(jStr.toStr());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String signUp() throws Exception{ //注册
		HttpServletResponse response=getResponse();
		String userName=servletRequest.getParameter("userName");
		String password=servletRequest.getParameter("password");
		String email=servletRequest.getParameter("email");
		boolean isNameVaild=userService.isNameVaild(userName);
		JsonStr jstr=new JsonStr();
		System.out.print("userAction.signUp函数中"+"isNameVaild"+isNameVaild);
		//--------------
		String confirmUrl="http://"+Configuration.address+"/MissLi/user_confirm.action?username="+userName+"&password="+password+"&"
				+ "email="+email;
		if(isNameVaild){  //用户名可用
			System.out.println("用户名可用");
			//jstr.put("isVaild", "true");
			User sUser=new User();
			sUser.setUsername(userName);
			sUser.setPassword(password);
			userService.addUser(sUser);
			//然后将邮件发出去
		}else{
			User user=userService.getUserByName(userName); //注册了但是没有验证
			
			if(user.getEmail()==null||user.getEmail().equals("NULL")||user.getEmail().equals("")){
				//将邮件发出去
				isNameVaild=true;
				//jstr.put("isVaild", "true");
			}
			else{
				isNameVaild=false;
				//jstr.put("isVaild", "false");	
			}
		}
		
		
		
		if(isNameVaild){
			SendMail mail=new SendMail();
			mail.send(email, "辅助教学管理系统访问以下网址前往注册", confirmUrl);
		}
		jstr.put("isNameVaild", isNameVaild);
		/*String str="http://"+Configuration.address+"/MissLi/user_confirm.action?username="+userName+"?password="+password+"?email="+email;
		jstr.put("address", str);*/
		System.out.println(jstr.toStr());
		response.getWriter().write(jstr.toStr());
		return null;
	}
	@Override
	public void setServletRequest(HttpServletRequest request){
		this.servletRequest=request;
	}

	public void initResponse(){ //初始化相应
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setHeader("Cache-control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html");
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
	public void configUser(){
		user.username=username;
		user.password=password;
		
	}
	private  HttpServletResponse getResponse(){
		HttpServletResponse response=ServletActionContext.getResponse();
		initResponse();
		return response;
	}

}
