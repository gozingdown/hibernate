package com.zheng.spring.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
// we are importing from javax.persistence because we are compliant with JPA standards
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate=true)// make update only if something has changed
@Table(name = "USER_DETAILS") // Entity name is still userdetails, when writing HQL we need to use entity name.
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private int userId;
	@Column(name = "USER_NAME")
	private String userName;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}



}
