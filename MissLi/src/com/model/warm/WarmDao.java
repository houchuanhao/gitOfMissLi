package com.model.warm;

import java.util.List;

public interface WarmDao {
	public void save(Warm warm);  //添加提醒
	public void del(Warm warm);   //删除提醒
	public void modify(Warm warm); //修改提醒
	public List<Warm> getByUId(String id);
	public List<Warm> getById(int id);
	public List<Warm> getByBid(String id);
}
