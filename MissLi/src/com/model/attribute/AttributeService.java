package com.model.attribute;

import java.util.List;

public interface AttributeService {
	
	public void add(Attribute attribute); //添加属性
	public void del(int id); //删除属性
	public void modify(Attribute attribute);//修改
	public List<Attribute> get(String businessName);
	List<Attribute> getByBId(int bId);
}
