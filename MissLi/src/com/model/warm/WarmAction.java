package com.model.warm;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class WarmAction extends ActionSupport implements ServletRequestAware{
	protected HttpServletRequest servletRequest=null;
	private static WarmService warmService =new WarmServiceImpl();
	private int id;
	private String warmName;
	private String userId;
	private String businessId;
	private Date begin;
	private String introduce;
	
	
	






	public void add(){ //ÃÌº”Ã·–—
		Warm warm=new Warm();
		userId=servletRequest.getParameter("userId");
		businessId=servletRequest.getParameter("businessId");
		
	}
	
	
	
	
	
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}
	public static WarmService getWarmService() {
		return warmService;
	}
	public static void setWarmService(WarmService warmService) {
		WarmAction.warmService = warmService;
	}
	
	
	
	public String getWarmName() {
		return warmName;
	}





	public void setWarmName(String warmName) {
		this.warmName = warmName;
	}





	public String getUserId() {
		return userId;
	}





	public void setUserId(String userId) {
		this.userId = userId;
	}





	public String getBusinessId() {
		return businessId;
	}





	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}





	public Date getBegin() {
		return begin;
	}





	public void setBegin(Date begin) {
		this.begin = begin;
	}





	public String getIntroduce() {
		return introduce;
	}





	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
}
