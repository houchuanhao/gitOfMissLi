package com.StrUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StrUtil {
	public String addQuoter(String str){
		return "\""+str+"\"";
	}
	public static int getTime(){
		Date date=new Date();
		//DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat format=new SimpleDateFormat("yyyyMMdd");
		String time=format.format(date);
		//System.out.print(time);
		int a=Integer.parseInt(time);
		//System.out.print(a+12);
		return a;
	}
}
