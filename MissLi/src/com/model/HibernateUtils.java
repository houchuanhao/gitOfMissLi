package com.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;  
  
public class HibernateUtils {  
    private static SessionFactory sessionfactory;  
    private HibernateUtils(){}  
    static{  
    Configuration cfg = new Configuration().configure();  
    sessionfactory = cfg.buildSessionFactory();   
    }  
    public static Session getSession(){  
        return sessionfactory.openSession();  
    }  
    //添加  
    public static void add(Object obj){  
        Session session = null;  
        Transaction tx = null;  
        try {  
            session = HibernateUtils.getSession();  
            tx = session.beginTransaction();  
            session.save(obj);//区别：save()方法如果没有开启事务，它会执行一条插入语句，但之后由于没有提交事务，它又进行  
            //session.persist(obj);//回滚了,而persist()方法在没有开启事务的时候，它根本不会去执行，即没有那条插入语句  
            tx.commit();  
        }finally{  
            if(session!=null){  
                session.close();  
            }  
        }  
    }  
    public static List<Object> queryBySql(String sql) {    
        List<Object> list = HibernateUtils.getSession().createSQLQuery(sql).list();    
        return list;    
    } 
    public static int excuteBySql(String sql)    
    {    
        int result ;    
        SQLQuery query = HibernateUtils.getSession().createSQLQuery(sql);    
        result = query.executeUpdate();    
        return result;    
    }  
    //修改  
    public static void update(Object obj){  
        Session session = null;  
        Transaction tx = null;  
        try {  
            session = HibernateUtils.getSession();  
            tx = session.beginTransaction();  
            session.update(obj);  
            tx.commit();  
        }finally{  
            if(session!=null){  
                session.close();  
            }  
        }  
    }  
    //删除  
    public static void delete(Object obj){  
        Session session = null;  
        Transaction tx = null;  
        try {  
            session = HibernateUtils.getSession();  
            tx = session.beginTransaction();  
            session.delete(obj);  
            tx.commit();  
        }finally{  
            if(session!=null){  
                session.close();  
            }  
        }  
    }  
    public static Object findById(Class clazz,int id){  
        Session session = null;  
	        try {  
	            session = HibernateUtils.getSession();  
	        //这里需要区分一下get()与load()的区别，load()不会立即//去访问数据库只有在第一次使用的时候才会去加载（懒加载）；  
	        //load方法永远不可能返回空对象(如果不存在，其会产生一个user的子类)具体可以去查资料区别这两个方法  
	            //Object obj = session.load(clazz, id);  
	            Object obj = session.get(clazz, id);
	            return obj;
	            }
	        finally{if(session!=null)
	            {
	            	session.close();
	            }
            }
        }
    }  
