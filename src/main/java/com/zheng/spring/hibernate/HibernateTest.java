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
		vehicle1.getUserList().add(user);
		vehicle2.getUserList().add(user);
		user.getVehicles().add(vehicle1);
		user.getVehicles().add(vehicle2);
		// SessionFactory is only created once per application (expensive)
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.save(vehicle1);
		session.save(vehicle2);
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
