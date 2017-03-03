package com.model.file;

import java.util.List;
import java.util.Date;  
import org.hibernate.HibernateException;  
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;  
public class FileDaoImpl implements FileDao{

	public static void main(String[] args){   
		//����ǧ��ע�⣬����д��Configuration cfg = new Configuration();����ᱨHibernate Dialect must be explicitly set ������Ϣ��������  
		//��ʵ����ǰ�������Ѿ�������mysql�ķ��ԣ�  
		        Configuration cfg = new Configuration().configure();//����configure()���������ǿ���������Դ������в鿴���������·  
		//�����Զ�ȥ����һ��Ĭ�ϵ�hibernate.cfg.xml�ļ�����������뻻�������֣�����ʹ�������ط�����������Բ鿴��Դ���루���ص�ѹ�������У�  
		        SessionFactory factory = cfg.buildSessionFactory();  
		        
		        
		        File file=new File();
		        file.setFileName("�����ļ�");
		        file.setBusinessId(2);
		        file.setUserId(222);
		        Date date=new Date();
		        file.setUploadDate(new Date());
		        Session session = null;  
		        Transaction tx = null;  
		        try {  
		            session = factory.openSession();  
		            tx = session.beginTransaction();//��������  
		            session.save(file);//���б���  
		            tx.commit();//�ύ����  
		        } catch (HibernateException e) {  
		            if(tx!=null){  
		                tx.rollback();//�ع�����  
		            }  
		            throw e;//�����׳��쳣  
		        }finally{  
		            if(session!=null){  
		                session.close();//�ر�session  
		            }  
		        }  
		    }  
	
	
	
	
	
	
	
	@Override
	public int save(File file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<File> getByBId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<File> getByUId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int del(File file) {
		// TODO Auto-generated method stub
		return 0;
	}

}
