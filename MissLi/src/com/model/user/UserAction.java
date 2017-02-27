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
	//-----------------������6����Ա����
	//1
	public String register() {  //���ô˺���֮ǰ֮ǰ��Ҫȷ���û�������
		User registingUser=new User();
		registingUser.setUsername(username);
		registingUser.setPassword(password);
		try {
			userService.addUser(user);  //�˴�ִ��ע��ʱ��û����֤�û����Ƿ����
		} catch (Exception e) {
			servletRequest.setAttribute("fall", "��UserAction.register������");
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
		if(userLog==null){ //�û��������������
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
	//4   ���ڻ�ȡ���Ե��û�
	public String list() throws Exception{
		List<User> userList=null;
		userList=userService.findAll(); 
		servletRequest.setAttribute("userList", userList);
		return "userList";
	}
	//5  
	public String checkUser() throws Exception{  //�ο������Ĵ���
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
	public String confirm() throws Exception{ //��֤����
		
		String userName=servletRequest.getParameter("username");
		String password=servletRequest.getParameter("password");
		String email=servletRequest.getParameter("email");
		System.out.print("��֤����ing");
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
	public String signIn(){  //��¼
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
		if(loginUser==null){ //�û������������
			jStr.put("loginSuccess", false);
		}
		else  //��¼�ɹ�
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
	public String signUp() throws Exception{ //ע��
		HttpServletResponse response=getResponse();
		String userName=servletRequest.getParameter("userName");
		String password=servletRequest.getParameter("password");
		String email=servletRequest.getParameter("email");
		boolean isNameVaild=userService.isNameVaild(userName);
		JsonStr jstr=new JsonStr();
		System.out.print("userAction.signUp������"+"isNameVaild"+isNameVaild);
		//--------------
		String confirmUrl="http://"+Configuration.address+"/MissLi/user_confirm.action?username="+userName+"&password="+password+"&"
				+ "email="+email;
		if(isNameVaild){  //�û�������
			System.out.println("�û�������");
			//jstr.put("isVaild", "true");
			User sUser=new User();
			sUser.setUsername(userName);
			sUser.setPassword(password);
			userService.addUser(sUser);
			//Ȼ���ʼ�����ȥ
		}else{
			User user=userService.getUserByName(userName); //ע���˵���û����֤
			
			if(user.getEmail()==null||user.getEmail().equals("NULL")||user.getEmail().equals("")){
				//���ʼ�����ȥ
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
			mail.send(email, "������ѧ����ϵͳ����������ַǰ��ע��", confirmUrl);
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

	public void initResponse(){ //��ʼ����Ӧ
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setHeader("Cache-control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html");
	}
	
	
	
	
	
	
	
	
	//------------������get��set
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
