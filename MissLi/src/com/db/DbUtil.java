package com.db;
/*
import com.sun.rowset.*;
import javax.sql.rowset.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

public class DbUtil {
	//Connecter connecter;
	public DbUtil() throws SQLException{
		//connecter=new Connecter();
	}
	public static int execute(String sql,List<Object> paramList) throws SQLException{  //insert,update,drop
		Connecter conn=new Connecter(sql);
		int result=0;
		if(sql==null||sql.trim().equals("")){
			return 0;
		}
		
		conn.close();
		return result;
	}
	public static CachedRowSetImpl query(String sql,List<Object> paramList) throws SQLException{//返回查询结果
		ResultSet rs;
		Connecter conn=new Connecter();
		rs=conn.statement.executeQuery(sql);
		CachedRowSetImpl cRS=new CachedRowSetImpl();
		cRS.populate(rs);
		cRS.last();
		conn.close();
		return cRS;
		
	}
	public static List<Map<String,String>> getQueryList(String sql,List<Object> paramList){
		return null;
		
	}
	//4
	public static void setPreparedStatementParam(PreparedStatement pstmt,List<Object> paramList){
		if(pstmt==null||paramList==null||paramList.isEmpty()){
			return;
		}
		DateFormat df=DateFormat.getDateTimeInstance();
		for(int i=0;i<paramList.size();i++){
			if(paramList.get(i) instanceof Integer){
				
			}
		}
	}
	private static List<Map<String,String>> getQueryList(ResultSet rs){
		return null;
	}
	}
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.sql.DataSource;

//import org.apache.log4j.Logger;

/**
 * 数据库操作辅助类,数据库连接池
 */
public class DbUtil {

    //private static Logger logger = Logger.getLogger("DbUtils");

    /**
     * 该语句必须是一个 SQL INSERT、UPDATE 或 DELETE 语句
     * @param sql
     * @param paramList：参数，与SQL语句中的占位符一一对应
     * @return
     * @throws Exception
     */
	//1
    public static int execute(String sql, List<Object> paramList) throws Exception {
        if(sql == null || sql.trim().equals("")) {
            //logger.info("parameter is valid!");
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = getConnection();
            pstmt = DbUtil.getPreparedStatement(conn, sql);
            setPreparedStatementParam(pstmt, paramList);
            if(pstmt == null) {
                return -1;
            }
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            //logger.info(e.getMessage());
            throw new Exception(e);
        } finally {
            closeStatement(pstmt);
            closeConn(conn);
        }

        return result;
    }

    /**
     * 将查询数据库获得的结果集转换为Map对象
     * @param sql：查询语句
     * @param paramList：参数
     * @return
     */
    //2
    public static List<Map<String, String>> getQueryList(String sql, List<Object> paramList) throws Exception {
        if(sql == null || sql.trim().equals("")) {
            //logger.info("parameter is valid!");
            return null;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String, String>> queryList = null;
        try {
            conn = getConnection();
            pstmt = DbUtil.getPreparedStatement(conn, sql);
            setPreparedStatementParam(pstmt, paramList);
            if(pstmt == null) {
                return null;
            }
            rs = getResultSet(pstmt);  //获取查询结果，rs=pstmt.executeQuery();
            queryList = getQueryList(rs);
        } catch (RuntimeException e) {
            //logger.info(e.getMessage());
            System.out.println("parameter is valid!");
            throw new Exception(e);
        } finally {
            closeResultSet(rs);
            closeStatement(pstmt);
            closeConn(conn);
        }
        return queryList;
    }
    //3说得对
    private static void setPreparedStatementParam(PreparedStatement pstmt, List<Object> paramList) throws Exception {
        if(pstmt == null || paramList == null || paramList.isEmpty()) {
            return;
        }
        DateFormat df = DateFormat.getDateTimeInstance();
        for (int i = 0; i < paramList.size(); i++) {
            if(paramList.get(i) instanceof Integer) {
                int paramValue = ((Integer)paramList.get(i)).intValue();
                pstmt.setInt(i+1, paramValue);
            } else if(paramList.get(i) instanceof Float) {
                float paramValue = ((Float)paramList.get(i)).floatValue();
                pstmt.setFloat(i+1, paramValue);
            } else if(paramList.get(i) instanceof Double) {
                double paramValue = ((Double)paramList.get(i)).doubleValue();
                pstmt.setDouble(i+1, paramValue);
            } else if(paramList.get(i) instanceof Date) {
                pstmt.setString(i+1, df.format((Date)paramList.get(i)));
            } else if(paramList.get(i) instanceof Long) {
                long paramValue = ((Long)paramList.get(i)).longValue();
                pstmt.setLong(i+1, paramValue);
            } else if(paramList.get(i) instanceof String) {
                pstmt.setString(i+1, (String)paramList.get(i));
            }
        }
        return;
    }

    /**
     * 获得数据库连接
     * @return
     * @throws Exception
     */
    //4
    private static Connection getConnection() throws Exception {
        InitialContext cxt = new InitialContext();
        DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/Struts2DB");
        if ( ds == null ) {
           throw new Exception("Data source not found!");
        }

        return ds.getConnection();
    }
    //5
    private static PreparedStatement getPreparedStatement(Connection conn, String sql) throws Exception {
        if(conn == null || sql == null || sql.trim().equals("")) {
            return null;
        }
        PreparedStatement pstmt = conn.prepareStatement(sql.trim());   //String.trim()  去掉字符串两头的空格
        return pstmt;
        //添加两行注释
    }

    /**
     * 获得数据库查询结果集
     * @param pstmt
     * @return
     * @throws Exception
     */
    //6
    private static ResultSet getResultSet(PreparedStatement pstmt) throws Exception {
        if(pstmt == null) {
            return null;
        }
        ResultSet rs = pstmt.executeQuery();
        return rs;
    }

    /**
     * @param rs
     * @return
     * @throws Exception
     */
    //将resultSet类型的查询结果转换为List<Map<String,String>>类型
    //7
    private static List<Map<String, String>> getQueryList(ResultSet rs) throws Exception {
        if(rs == null) {
            return null;
        }
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int columnCount = rsMetaData.getColumnCount();  //获取列数
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        while (rs.next()) {
            Map<String, String> dataMap = new HashMap<String, String>();
            for (int i = 0; i < columnCount; i++) {
                dataMap.put(rsMetaData.getColumnName(i+1), rs.getString(i+1));
                //String rsMetaData.getColumnName(int)  返回序号为int的列名
            }
            dataList.add(dataMap);
        }
        return dataList;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    private static void closeConn(Connection conn) {
        if(conn == null) {
            return;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            //logger.info(e.getMessage());
        }
    }

    /**
     * 关闭
     * @param stmt
     */
    private static void closeStatement(Statement stmt) {
        if(stmt == null) {
            return;
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            //logger.info(e.getMessage());
        }
    }

    /**
     * 关闭
     * @param rs
     */
    private static void closeResultSet(ResultSet rs) {
        if(rs == null) {
            return;
        }
        try {
            rs.close();
        } catch (SQLException e) {
            //logger.info(e.getMessage());
        }
    }
}



