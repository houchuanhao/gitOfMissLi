package com.model.attribute;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.db.DbUtil;
import com.model.business.Business;
import com.model.business.BusinessService;
import com.model.business.BusinessServiceImpl;
import com.model.user.User;
import com.opensymphony.xwork2.ActionSupport;

public class AttributeAction extends ActionSupport implements ServletRequestAware{
	public HttpServletRequest servletRequest=null;
	String id;
	String businessName;
	String val;
	String type;
	String attributeName;
	int businessId;
	private static AttributeService attributeService =new AttributeServiceImpl();
	public String doAdd(){  //添加属性
		Attribute attribute =new Attribute();
		attribute.setBusinessName(businessName);
		attribute.setType(type);
		attribute.setAttributeName(attributeName);
		attribute.setVal(val);
		System.out.println(id);
		attribute.setBusinessId(businessId);
		attributeService.add(attribute);
		return SUCCESS;//添加成功
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String add(){  //添加属性
		setRequest();
		return "doAdd";
	}
	public String modify(){
		Attribute attribute =new Attribute();
		attribute.setId(Integer.parseInt(id));
		attribute.setBusinessName(businessName);
		attribute.setType(type);
		attribute.setAttributeName(attributeName);
		attributeService.modify(attribute);
		
		return attributeName;
		
	}
	public String get(){  //所有属性
		setRequest();
		int bId=Integer.parseInt(id);
		servletRequest.setAttribute("attributeList",attributeService.getByBId(bId));
		System.out.print("businessName"+businessName);
		return "get";
	}
	public String del(){ //删除
		Attribute attribute =new Attribute();
		attribute.setId(Integer.parseInt(servletRequest.getParameter("id")));
		return SUCCESS;
	}
	public String getAttribute(){
		String businessName=servletRequest.getParameter("businessName");
		Attribute attribute=new Attribute();
		List<Attribute> attributeList=attributeService.get(businessName);
		servletRequest.setAttribute("attribute", attributeList);
		return SUCCESS;
	}
	public String compare() throws Exception{
		AttributeService as=new AttributeServiceImpl();  //已经知道了事务名，属性名
		User user=(User)servletRequest.getSession().getAttribute("user");
		String userId=user.getId();
		String attributeName=servletRequest.getParameter("attributeName");
		String businessName=servletRequest.getParameter("businessName");
		Statement stmt=DbUtil.getConnection().createStatement();
		Connection conn=DbUtil.getConnection();
		PreparedStatement pstmt= conn.prepareStatement("select * from business,attribute where userName=? and business.businessName=? and business.id=attribute.businessId and attribute.attributeName=?");
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, businessName);
		pstmt.setString(3, attributeName);
		System.out.println(pstmt);
		ResultSet rs=null;
		try {
			rs=pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		servletRequest.setAttribute("rs", rs);
		return "compare";
		
		//servletRequest.setAttribute("", o); //事务名，属性，日期  已知事务名，求得属性，
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setRequest(){
		BusinessService bs=new BusinessServiceImpl();
		Business b=bs.getByBId(servletRequest.getParameter("id")).get(0);  //获取事务
		servletRequest.setAttribute("business", b);
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
}
