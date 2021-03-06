package com.model.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.model.attribute.Attribute;
import com.model.attribute.AttributeService;
import com.model.attribute.AttributeServiceImpl;
import com.model.user.User;
import com.opensymphony.xwork2.ActionSupport;

public class BusinessAction extends ActionSupport implements ServletRequestAware{
	private String businessName;
	private String id;
	private String userName;
	private String start; //开始日期
	private Business business;
	private String introduce;
	private static BusinessService businessService=new BusinessServiceImpl();
	protected HttpServletRequest servletRequest=null;
	
	public String addBusiness(){
		System.out.println("out");
		userName=(String)servletRequest.getSession().getAttribute("userName");
		System.out.println("out----------------");
		int result=businessService.add(businessName, userName, introduce);
		
		if(result==1){
			return SUCCESS;
		}
		else{
			return ERROR;
		}
	}
	public String getMyBusiness(){  //我的事务
		if(servletRequest.getSession().getAttribute("user")==null) //未登录
		{
			return "logIN"; //未登录
		}
		User user=(User)servletRequest.getSession().getAttribute("user");
		List<Business> businessList=new ArrayList<Business>();
		businessList=businessService.getByUser(user.getUsername());
		HttpServletRequest request =ServletActionContext.getRequest();
		request.setAttribute("businessList", businessList);
		System.out.print(businessList+user.getUsername());
		return "myBusiness";
	}
	public String manage(){ //对单个事务进行管理
		String id=(String)servletRequest.getAttribute("id");
		List<Business> businessList=new ArrayList<Business>();
		businessList=businessService.getByBId(id);
		Business bs=businessList.get(0);
		servletRequest.setAttribute("business", bs); 
		AttributeService as=new AttributeServiceImpl();
		//--------------------------------------
		List<Attribute> attributeList=as.getByBId(Integer.parseInt(id));
				//as.get(bs.getBusinessName());
		servletRequest.setAttribute("attributeList", attributeList);
		System.out.println(bs.getBusinessName());
		for(int i=0;i<attributeList.size();i++){
			System.out.println(attributeList.get(i).getAttributeName());
		}
		return "manage";
	}
	public String getAll(){
		User user=(User) servletRequest.getSession().getAttribute("user");
		List<Business> businessList=businessService.getByUser(user.getUsername());
		//System.out.println("getAll"+businessList.size());
		servletRequest.setAttribute("businessList", businessList);
		return "addExistBusiness";
	}
	
	
	
	
	













	public static BusinessService getBusinessService() {
		return businessService;
	}




















	public static void setBusinessService(BusinessService businessService) {
		BusinessAction.businessService = businessService;
	}




















	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}




















	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}




















	public String execute(){
		addBusiness();
		System.out.println(userName+","+businessName+","+introduce);
		return "";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
}
