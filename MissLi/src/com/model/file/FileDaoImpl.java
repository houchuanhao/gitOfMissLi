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
		 
		//这里千万注意，不能写成Configuration cfg = new Configuration();否则会报Hibernate Dialect must be explicitly set 错误信息将我们误导  
		//；实际上前面我们已经配置了mysql的方言；  
		        Configuration cfg = new Configuration().configure();//对于configure()方法，我们可以引入其源代码进行查看，其会在类路  
		//径下自动去加载一个默认的hibernate.cfg.xml文件；如果我们想换其他名字，可以使用其重载方法，具体可以查看其源代码（下载的压缩包中有）  
		        SessionFactory factory = cfg.buildSessionFactory();  
		        
		        
		        MyFile file=new MyFile();
		        file.setFileName("测试文件");
		        file.setBusinessId(2);
		        file.setUserId(22233);
		        Date date=new Date();
		        file.setUploadDate(new Date());
		        Session session = null;  
		        Transaction tx = null;  
		        try {  
		            session = factory.openSession();  
		            tx = session.beginTransaction();//开启事务  
		            session.save(file);//进行保存  
		            tx.commit();//提交事务  
		            
		        } catch (HibernateException e) {  
		            if(tx!=null){  
		                tx.rollback();//回滚事务  
		            }  
		            throw e;//必须抛出异常  
		        }finally{  
		            if(session!=null){  
		                session.close();//关闭session  
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
	public int del(MyFile file) {  //file必须有id
		HibernateUtils.delete(file);
		return 0;
	}

/*通过sql语句查询
 * public static void deleteTwo(int id)//第二个操作方法  
{  
   TUserDAO dao=new TUserDAO();  
   org.hibernate.Transaction tx=dao.getSession().beginTransaction();//开始一个事务  
   Query query = dao.getSession().createQuery("delete from TUser where IId=?");  
  
//跟据条件生成HQL语句  
   query.setInteger(0, id);//设定条件参数  
   query.executeUpdate();执行语句  
   tx.commit();//提交事务  
   dao.getSession().close();关闭session  
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
		for(int i=0;;i++){  //确保没有重复文件
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
