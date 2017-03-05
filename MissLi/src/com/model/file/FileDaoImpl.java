package com.model.file;

import java.util.List;
import java.io.File;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;

import com.model.HibernateUtils;  
public class FileDaoImpl implements FileDao{

	public static void main(String[] args){ 
		// String root = ServletActionContext.getServletContext().getRealPath("/upload");
		 
		//����ǧ��ע�⣬����д��Configuration cfg = new Configuration();����ᱨHibernate Dialect must be explicitly set ������Ϣ��������  
		//��ʵ����ǰ�������Ѿ�������mysql�ķ��ԣ�  
		        Configuration cfg = new Configuration().configure();//����configure()���������ǿ���������Դ������в鿴���������·  
		//�����Զ�ȥ����һ��Ĭ�ϵ�hibernate.cfg.xml�ļ�����������뻻�������֣�����ʹ�������ط�����������Բ鿴��Դ���루���ص�ѹ�������У�  
		        SessionFactory factory = cfg.buildSessionFactory();  
		        
		        
		        MyFile file=new MyFile();
		        file.setFileName("�����ļ�");
		        file.setBusinessId(2);
		        file.setUserId(22233);
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
	public int save(MyFile file) {
		HibernateUtils.add(file);
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MyFile> getByBId(int id) {
		Session session=HibernateUtils.getSession();
		Query query=session.createQuery("from MyFile where businessId="+String.valueOf(id));
		
		if(query.list()==null||query.list().isEmpty()){
			return null;
		}
		else
			return query.list();
		
		// TODO Auto-generated method stub
	}

	@Override
	public List<MyFile> getByUId(int id) {
		Session session=HibernateUtils.getSession();
		Query query=session.createQuery("from MyFile where userId="+String.valueOf(id));
		if(query.list()==null||query.list().isEmpty()){
			return null;
		}
		else
			return query.list();
		
		// TODO Auto-generated method stub
	}

	@Override
	public int del(MyFile file) {  //file������id
		HibernateUtils.delete(file);
		return 0;
	}

/*ͨ��sql����ѯ
 * public static void deleteTwo(int id)//�ڶ�����������  
{  
   TUserDAO dao=new TUserDAO();  
   org.hibernate.Transaction tx=dao.getSession().beginTransaction();//��ʼһ������  
   Query query = dao.getSession().createQuery("delete from TUser where IId=?");  
  
//������������HQL���  
   query.setInteger(0, id);//�趨��������  
   query.executeUpdate();ִ�����  
   tx.commit();//�ύ����  
   dao.getSession().close();�ر�session  
    System.out.println("Delete");  
} 
 * 
 * 
 * 
 * */





	@Override
	public int save(File file, String fileName, int businessId, int userId) {
		// TODO Auto-generated method stub
		
		int n=fileName.length();
		for(int i=0;;i++){  //ȷ��û���ظ��ļ�
			if(getByFName(fileName)>=1){
				if(i==0){
					fileName=fileName+"0";
				}
				else{
					fileName=fileName.substring(n)+String.valueOf(i);
				}
			}
			else
			{
				break;
			}
		}
		MyFile mFile=new MyFile();
		mFile.setBusinessId(businessId);
		mFile.setFileName(fileName);
		mFile.setUserId(userId);
		Date date=new Date();
		mFile.setUploadDate(date);
		
		return 0;
	}







	public int getByFName(String fileName) {
		Session session=HibernateUtils.getSession();
		Query query=session.createSQLQuery("select * from file where fileName='"+fileName+"'");
		if(query.list()==null||query.list().isEmpty()){
			return 0;
		}
		else
			return query.list().size();
		// TODO Auto-generated method stub
	}







	@Override
	public int save(MyFile file, String fileName, int businessId, int userId) {
		// TODO Auto-generated method stub
		return 0;
	}







}
