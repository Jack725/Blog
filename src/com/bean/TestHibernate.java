package com.bean;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestHibernate {
	@SuppressWarnings("unchecked")
	public void test(){
		System.out.println("enter TestHibernate");
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
//		Product p = new Product();
//		p.setName("iphone7");
//		p.setPrice(7000);
//		s.save(p);
		
		List<Product> products = new ArrayList();
		String stringQuery = "from Product product where product.name = :productName";
		Query q = s.createQuery(stringQuery)
				.setString("productName", "iphone7");
		System.out.println(q.getQueryString());
		products = q.list();
		System.out.println("size = " + products.size());
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
