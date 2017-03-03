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
    //���  
    public static void add(Object obj){  
        Session session = null;  
        Transaction tx = null;  
        try {  
            session = HibernateUtils.getSession();  
            tx = session.beginTransaction();  
            session.save(obj);//����save()�������û�п�����������ִ��һ��������䣬��֮������û���ύ�������ֽ���  
            //session.persist(obj);//�ع���,��persist()������û�п��������ʱ������������ȥִ�У���û�������������  
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
    //�޸�  
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
    //ɾ��  
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
	        //������Ҫ����һ��get()��load()������load()��������//ȥ�������ݿ�ֻ���ڵ�һ��ʹ�õ�ʱ��Ż�ȥ���أ������أ���  
	        //load������Զ�����ܷ��ؿն���(��������ڣ�������һ��user������)�������ȥ��������������������  
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
