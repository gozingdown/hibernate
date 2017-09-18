package com.zheng.spring.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USER_DETAILS") // Entity name is still userdetails, when writing HQL we need to use entity
								// name.
public class UserDetails {

	//use @EmbeddedId to replace all these annotations when you want to have an embedded object as the the PK.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "USER_NAME")
	// @Transient // this field will be ignored by hibernate
	private String userName;
	
	@ElementCollection//mark the collection object to be persisted by Hibernate as seperate table
	private Set<Address> listOfAddresses = new HashSet<>();

	public Set<Address> getListOfAddresses() {
		return listOfAddresses;
	}

	public void setListOfAddresses(Set<Address> listOfAddresses) {
		this.listOfAddresses = listOfAddresses;
	}

	@Temporal(TemporalType.DATE) // only save the date not the timestamp
	private Date joinedDate;

	@Lob // large object (clob/blob), here is clob (char lob)
	private String description;

//	@Embedded // this is not mandatory
//	@AttributeOverrides({ 
//			@AttributeOverride(name = "street", column = @Column(name = "HOME_STREET_NAME")),
//			@AttributeOverride(name = "city", column = @Column(name = "HOME_CITY_NAME")),
//			@AttributeOverride(name = "state", column = @Column(name = "HOME_STATE_NAME")),
//			@AttributeOverride(name = "pincode", column = @Column(name = "HOME_PINCODE_NAME")) 
//	})
//	private Address homeAddress;
//
//	@Embedded
//	@AttributeOverrides({ 
//		@AttributeOverride(name = "street", column = @Column(name = "OFFICE_STREET_NAME")),
//		@AttributeOverride(name = "city", column = @Column(name = "OFFICE_CITY_NAME")),
//		@AttributeOverride(name = "state", column = @Column(name = "OFFICE_STATE_NAME")),
//		@AttributeOverride(name = "pincode", column = @Column(name = "OFFICE_PINCODE_NAME")) 
//	})
//	private Address officeAddress;
	
//	public Address getHomeAddress() {
//		return homeAddress;
//	}
//
//	public void setHomeAddress(Address homeAddress) {
//		this.homeAddress = homeAddress;
//	}
//
//	public Address getOfficeAddress() {
//		return officeAddress;
//	}
//
//	public void setOfficeAddress(Address officeAddress) {
//		this.officeAddress = officeAddress;
//	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
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
