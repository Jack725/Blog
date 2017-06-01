package com.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bean.User;

public class UserDAO {
	/**
	 * by Bwang on 2017/5/31
	 * �������û�(����)
	 * @param user
	 */
	public void add(User user){
		//����Ԥ����
		//����û�����(����)
		user.setType(1);
		//�����û�״̬(����)
		user.setState(0);
		//�����û�ע��ʱ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(new Date());
		user.setRegTime(date);
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		
		s.save(user);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	
	/**
	 * by Bwang on 2017/5/31
	 * �����û��ǳƲ����û�
	 * @param userName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getByUserName(String userName){
		List<User> results = new ArrayList();
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		
		String queryString = "from User user "
						+	"where user.userName = :userName";
		results = s.createQuery(queryString)
				.setString("userName", userName)
				.list();
		
		s.getTransaction().commit();
		s.close();
		sf.close();
		
		return results;
	}
	
	
	/**
	 * by Bwang on 2017/5/31
	 * �����û���¼�������û�
	 * @param loginName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getByLoginName(String loginName){
		List<User> results = new ArrayList();
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		
		String queryString = "from User user where user.loginName = :loginName";
		Query q = s.createQuery(queryString)
				.setString("loginName", loginName);
		
		results = q.list();
		
		s.getTransaction().commit();
		s.close();
		sf.close();
		
		return results;
	}
	
	/**
	 * by Bwang on 2017/5/31
	 * �����û�id�����û���Ϣ
	 * @param userID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getByID(int userID){
		List<User> results = new ArrayList();
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		
		String queryString = "from User user "
						+	"where user.userID = :userID";
		results = s.createQuery(queryString)
				.setLong("userID", userID)
				.list();
		
		s.getTransaction().commit();
		s.close();
		sf.close();
		
		return results;
	}
}
