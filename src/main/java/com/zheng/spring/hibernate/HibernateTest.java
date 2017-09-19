package com.zheng.spring.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.zheng.spring.dto.Address;
import com.zheng.spring.dto.UserDetails;

public class HibernateTest {

	public static void main(String[] args) {
		UserDetails user = new UserDetails();
		user.setUserName("First User");
		user.setJoinedDate(new Date());
		user.setDescription("Description of the user goes here");
	
		Address homeAddress = new Address();
		homeAddress.setStreet("First Street Name");
		homeAddress.setCity("First City Name");
		homeAddress.setState("First State");
		homeAddress.setPincode("100001");
		
		Address officeAddress = new Address();
		officeAddress.setStreet("Second Street Name");
		officeAddress.setCity("Second City Name");
		officeAddress.setState("Second State");
		officeAddress.setPincode("200002");
		
		user.getListOfAddresses().add(homeAddress);
		user.getListOfAddresses().add(officeAddress);
		
		
		// SessionFactory is only created once per application (expensive)
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();// normally in a finally block

		user = null;
		session = sessionFactory.openSession();
		session.beginTransaction();
		user = (UserDetails) session.get(UserDetails.class, 1);
		System.out.println("User Name retrieved is " + user.getUserName());
		//session.close();
		System.out.println(user.getListOfAddresses().size());
		session.getTransaction().commit();
		session.close();
	}
}
