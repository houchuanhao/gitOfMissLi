package com.model.warm;

import java.util.Date;

//添加事务，删除事务，添加提醒
public class Warm {
	private int id;
	private int businessId;
	private int userId;
	private Date create;
	private Date begin;
	private String warmName;
	private String introduce;
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public Date getCreate() {
		return create;
	}
	public void setCreate(Date create) {
		this.create = create;
	}
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getWarmName() {
		return warmName;
	}
	public void setWarmName(String warmName) {
		this.warmName = warmName;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	
}
