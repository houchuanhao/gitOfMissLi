package com.model.attribute;

import java.util.List;

public interface AttributeService {
	
	public void add(Attribute attribute); //�������
	public void del(int id); //ɾ������
	public void modify(Attribute attribute);//�޸�
	public List<Attribute> get(String businessName);
	List<Attribute> getByBId(int bId);
}
