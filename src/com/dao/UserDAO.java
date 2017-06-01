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
	 * 插入新用户(作者)
	 * @param user
	 */
	public void add(User user){
		//进行预处理
		//添加用户类型(作者)
		user.setType(1);
		//设置用户状态(正常)
		user.setState(0);
		//设置用户注册时间
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
	 * 根据用户昵称查找用户
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
	 * 根据用户登录名查找用户
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
	 * 根据用户id查找用户信息
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
