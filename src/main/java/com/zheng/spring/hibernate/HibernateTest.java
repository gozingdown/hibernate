package com.zheng.spring.hibernate;

import java.util.Date;

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
		UserDetails user = new UserDetails();
		user.setUserName("Test User");//transient object
		
		// SessionFactory is only created once per application (expensive)
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(user);// persistent object
		user.setUserName("Updated User");
		user.setUserName("Updated User Again");// this change is also in the database
		// because no matter what change you make before close, the last update before commit will go into the database.
		session.getTransaction().commit();
		session.close();// normally in a finally block
		// after close, the object become a detached object
		user.setUserName("Updated User After Session Close");

	}
}
