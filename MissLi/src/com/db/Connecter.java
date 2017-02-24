package com.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Connecter{
    public  Connection conn=null;
    public  Statement statement=null;//����������ִ�в���������sql���
    public PreparedStatement pstmt=null;
    Connecter() throws SQLException{
    	connect();
    	
    }
    Connecter(String sql) throws SQLException{
    	connect();
    	getPreparedStatement( sql);
    }
    private void connect() throws SQLException{  //����

            try {
                Class.forName("com.mysql.jdbc.Driver"); //��������
            } catch (Exception e) {
                System.out.print("��������ʧ��-----------");
            }
        
            try {
                //�������������ݿ⣬���ݿ������ƣ��û���"root",����""
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diy?characterEncoding=utf-8", "root", "");
                System.out.println("�������ݿ�ɹ�");
            } catch (Exception e) {
                System.out.print("�������ݿ�ʧ��");
            }
            //Statement statement=conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);
            statement = conn.createStatement(); //�ɹ���������\
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
    public void close() throws SQLException{  //�ر�����
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
    protected static void createConnection(){  //��������
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
                Class.forName("com.mysql.jdbc.Driver"); //��������
            } catch (Exception e) {
                System.out.print("��������ʧ��-----------");
            }
        }
        if (statement == null) {
            try {
                //�������������ݿ⣬���ݿ������ƣ��û���"root",����""
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diy?characterEncoding=utf-8", "root", "");
                System.out.println("�������ݿ�ɹ�");
            } catch (Exception e) {
                System.out.print("�������ݿ�ʧ��");
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