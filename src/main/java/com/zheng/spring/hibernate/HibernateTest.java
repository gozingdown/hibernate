package com.zheng.spring.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.zheng.spring.dto.Address;
import com.zheng.spring.dto.FourWheeler;
import com.zheng.spring.dto.TwoWheeler;
import com.zheng.spring.dto.UserDetails;
import com.zheng.spring.dto.Vehicle;

public class HibernateTest {

	public static void main(String[] args) {
		/** Object status changes:
		UserDetails user = new UserDetails();
		user.setUserName("Test User");//transient object
		
		// SessionFactory is only created once per application (expensive)
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		user = (UserDetails)session.get(UserDetails.class, 1);// the user object is persistent, so the updates later on will be monitored by Hibernate
//		session.save(user);// persistent object
		user.setUserName("Updated User1");
		user.setUserName("Updated User Again1");// this change is also in the database
		// because no matter what change you make before close, the last update before commit will go into the database.
		session.getTransaction().commit();
		session.close();// normally in a finally block
		// after close, the object become a detached object
		user.setUserName("Updated User After Session Close");
		
		// if you still want the above update to work:
		/*
		session  = sessionFactory.openSession();
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
		*/
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select userName from UserDetails");//a list of string will be returned instead of UserDetails
		query.setFirstResult(2);
		query.setMaxResults(1);
		List<String> userNames = (List<String>) query.list();
		
		session.getTransaction().commit();
		session.close();
		
		for(String userName: userNames) {
			System.out.println(userName);
		}
	}
}
