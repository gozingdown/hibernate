package com.zheng.spring.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.zheng.spring.dto.Address;
import com.zheng.spring.dto.UserDetails;
import com.zheng.spring.dto.Vehicle;

public class HibernateTest {

	public static void main(String[] args) {
		UserDetails user = new UserDetails();
		user.setUserName("First User");
		Vehicle vehicle1 = new Vehicle();
		vehicle1.setVehicleName("Car");
		Vehicle vehicle2 = new Vehicle();
		vehicle2.setVehicleName("Jeep");
		user.getVehicles().add(vehicle1);
		user.getVehicles().add(vehicle2);
		// SessionFactory is only created once per application (expensive)
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(user);//there's no need to save the vehicles now
		session.getTransaction().commit();
		session.close();// normally in a finally block

		user = null;
		session = sessionFactory.openSession();
		session.beginTransaction();
		user = (UserDetails) session.get(UserDetails.class, 1);
		System.out.println("User Name retrieved is " + user.getUserName());
		session.getTransaction().commit();
		session.close();
	}
}
