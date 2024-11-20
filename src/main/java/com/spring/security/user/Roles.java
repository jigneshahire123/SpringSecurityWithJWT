//package com.spring.security.user;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
//
//@Entity
//public class Roles {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
//	private long id;
//	private String name;
//	@ManyToOne
//	private UserInfo user;
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Roles(long id, String name) {
//		super();
//		this.id = id;
//		this.name = name;
//	}
//
//	public Roles() {
//	}
//
//	public UserInfo getUser() {
//		return user;
//	}
//
//	public void setUser(UserInfo user) {
//		this.user = user;
//	}
//}
