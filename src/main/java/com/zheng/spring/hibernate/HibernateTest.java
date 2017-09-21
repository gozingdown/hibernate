package com.zheng.spring.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.zheng.spring.dto.Address;
import com.zheng.spring.dto.FourWheeler;
import com.zheng.spring.dto.TwoWheeler;
import com.zheng.spring.dto.UserDetails;
import com.zheng.spring.dto.Vehicle;

public class HibernateTest {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		UserDetails user = (UserDetails) session.get(UserDetails.class, 1);
		
		session.getTransaction().commit();
		session.close();
		
		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		
		UserDetails user2 = (UserDetails) session2.get(UserDetails.class, 1);
		
		session2.getTransaction().commit();
		session2.close();
	}
}
