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
		String minUserId = "3";
		String userName = "test";
		
//		Query query = session.createQuery(" from UserDetails where userId > ? and userName = ?");
//		query.setInteger(0, Integer.parseInt(minUserId));
//		query.setString(1, userName);
		
//		Query query = session.createQuery(" from UserDetails where userId > :userId and userName = :userName");
//		query.setInteger("userId", Integer.parseInt(minUserId));
//		query.setString("userName", userName)
		
//		Query query = session.getNamedQuery("UserDetails.byId");
//		query.setInteger(0, 2);
		
//		Query query = session.getNamedQuery("UserDetails.byName");
//		query.setString(0, "test");
		
		//Query By Example:
		UserDetails exampleUser = new UserDetails();
		exampleUser.setUserId(3);//Hibernate ignores null-value properties, and primary-key properties, so 3 will be ignored.
		exampleUser.setUserName("test%");
		Example example = Example.create(exampleUser).enableLike();//.excludeProperty("userName");
		
		Criteria criteria = session.createCriteria(UserDetails.class);//kinda like a where clause
//		criteria.add(Restrictions.eq("userName", "test"))
//				.add(Restrictions.gt("userId", 3))
//				.add(Restrictions.like("userName", "%es%"))
//				.add(Restrictions.or(Restrictions.between("userId", 0, 3), Restrictions.between("userId", 4, 5)));//OR

		// Projections
		//criteria.setProjection(Projections.count("userId")) // if you set the projection, the end result will not be a list of UserDetails any more.
		
		// Add Order
//		criteria.addOrder(Order.desc("userId"));
		
		criteria.add(example);
		
		List<UserDetails> users = (List<UserDetails>) criteria.list();
		
		session.getTransaction().commit();
		session.close();
		
		for(UserDetails user: users) {
			System.out.println(user.getUserId() + " | " + user.getUserName());
		}
	}
}
