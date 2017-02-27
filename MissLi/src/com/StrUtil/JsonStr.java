package com.StrUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JsonStr extends StrUtil{
	Map<String,String> paramMap=new HashMap<String, String>();
	public void put(String key,String value){
		paramMap.put(key, value);
	}
	public void put(String key,boolean value){
		String str="";
		if(value==true)
			str="true";
		else
			str="false";
				
		paramMap.put(key, str);
	}
	public String toStr(){
		if(paramMap==null||paramMap.isEmpty()){
			System.out.println("JsonStr.toStr函数出错");
			return "";
		}//response.getWriter().write("{\"isNameVaild\":"+isNameVaild+"}");
		String jStr="{";
		Iterator it=paramMap.entrySet().iterator();
		while(it.hasNext()){
			Entry entry=(Entry) it.next();
			String key=(String)entry.getKey();
			String value=(String)entry.getValue();
			jStr=jStr+addQuoter(key)+":"+value;
			if(it.hasNext()){
				jStr=jStr+",";
			}
			else{
				jStr=jStr+"}";
			}
			}
		return jStr;
	}
	/*
	public static void main(String[] args){
		JsonStr jstr=new JsonStr();
		jstr.put("姓名", "侯传浩");
		System.out.println(jstr.toStr());
	}
	*/
}
