package com.model.warm;

import java.util.List;

public interface WarmService { 
	public void add(Warm warm);
	public void del(Warm warm);   //É¾³ıÌáĞÑ
	public void modify(Warm warm); //ĞŞ¸ÄÌáĞÑ
	public List<Warm> getById(int id);
	public List<Warm> getByBid(String id);
	public List<Warm> getByUId(String id);
}
