package com.model.warm;

import java.util.List;

public interface WarmService { 
	public void add(Warm warm);
	public void del(Warm warm);   //ɾ������
	public void modify(Warm warm); //�޸�����
	public List<Warm> getById(int id);
	public List<Warm> getByBid(String id);
	public List<Warm> getByUId(String id);
}
