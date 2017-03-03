package com.model.warm;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.model.user.User;
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
	
	
	






	public String add(){ //Ìí¼ÓÌáÐÑ
		Warm warm=new Warm();
		userId=servletRequest.getParameter("userId");
		businessId=servletRequest.getParameter("businessId");
		if(businessId==null){
			businessId="99999";
		}
		
		User user=(User) servletRequest.getSession().getAttribute("user");
		System.out.print(begin);
		warm.setBegin(begin);
		warm.setIntroduce(introduce);
		warm.setUserId(Integer.parseInt(user.getId()));
		warm.setBusinessId(Integer.parseInt(businessId));
		warm.setWarmName(warmName);
		warmService.add(warm);
		return SUCCESS;  //Ìí¼Ó
		
	}
	public String myWarm(){
		User user=(User) servletRequest.getSession().getAttribute("user");
		if(user==null){
			return "login"; //Î´ÔøµÇÂ¼
		}
		String id=user.getId();
		List<Warm> warmList=warmService.getByUId(user.getId());
		servletRequest.setAttribute("warmList", warmList);
		return "myWarm";
	}
	
	
	
	
	
	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
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
