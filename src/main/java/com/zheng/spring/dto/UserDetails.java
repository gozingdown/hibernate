package com.zheng.spring.dto;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="USER_DETAILS") //Entity name is still userdetails, when writing HQL we need to use entity name.
public class UserDetails {

	@Id
	@Column(name="USER_ID")
	private int userId;
	
	@Column(name="USER_NAME")
	//@Transient // this field will be ignored by hibernate
	private String userName;
	
	@Temporal(TemporalType.DATE) //only save the date not the timestamp
	private Date joinedDate;
	private String address;
	
	@Lob //large object (clob/blob), here is clob (char lob)
	private String description;
	
	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
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
