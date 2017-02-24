package com.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Connecter{
    public  Connection conn=null;
    public  Statement statement=null;//声明，用于执行不带参数的sql语句
    public PreparedStatement pstmt=null;
    Connecter() throws SQLException{
    	connect();
    	
    }
    Connecter(String sql) throws SQLException{
    	connect();
    	getPreparedStatement( sql);
    }
    private void connect() throws SQLException{  //连接

            try {
                Class.forName("com.mysql.jdbc.Driver"); //加载驱动
            } catch (Exception e) {
                System.out.print("加载驱动失败-----------");
            }
        
            try {
                //下面是连接数据库，数据库名名称，用户名"root",密码""
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diy?characterEncoding=utf-8", "root", "");
                System.out.println("连接数据库成功");
            } catch (Exception e) {
                System.out.print("连接数据库失败");
            }
            //Statement statement=conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);
            statement = conn.createStatement(); //成功建立连接\
     /*   	
        try{

            insert = conn.prepareStatement("insert into DIY(pUrl,dName,UserName,dInt,dCode) values(?,?,?,?,?)");
        }catch (Exception e){

        }
        */
    }
    public PreparedStatement getPreparedStatement(String sql) throws SQLException{
    	if(conn==null)
    		return null;
    	pstmt=conn.prepareStatement(sql);
		return pstmt;
    	
    }
    public void close() throws SQLException{  //关闭连接
    	if(conn==null)
    		return;
    	conn.close();
    	if(statement==null)
    		return;
    	statement.close();
    	if(pstmt==null)
    		return;
    	pstmt.close();
    }
/*
    protected static void createConnection(){  //建立连接
       try {
           new c();
       }
       catch (Exception e){
           System.out.println(e);
       }
    }
    private Connecter() throws Exception {
        if (conn == null)
        {//
            try {
                Class.forName("com.mysql.jdbc.Driver"); //加载驱动
            } catch (Exception e) {
                System.out.print("加载驱动失败-----------");
            }
        }
        if (statement == null) {
            try {
                //下面是连接数据库，数据库名名称，用户名"root",密码""
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diy?characterEncoding=utf-8", "root", "");
                System.out.println("连接数据库成功");
            } catch (Exception e) {
                System.out.print("连接数据库失败");
            }
            //Statement statement=conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);
            statement = conn.createStatement();
        }
        try{

            insert = conn.prepareStatement("insert into DIY(pUrl,dName,UserName,dInt,dCode) values(?,?,?,?,?)");
        }catch (Exception e){

        }
    }
    //public static
     * */
}