package com.model.business;

import com.opensymphony.xwork2.ActionSupport;

public class BusinessAction extends ActionSupport{
	private String businessName;
	private String id;
	private String userName;
	private String start; //开始日期
	private Business business;
	private String introduce;
	
	
	
	
	
	
	
	
	
	
	
	public String getIntroduce() {
		return introduce;
	}












	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}












	public String execute(){
		System.out.println("executing-----");
		if(getBusinessName()!=null&&!getBusinessName().isEmpty()){
			System.out.println("businessName为----"+businessName+introduce);
		}
		return "SUCCESS123";
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
}
