package com.bank.to;

import com.bank.to.types.UserType;

public class User {
	private String user_name;
	private String password;
	private UserType user_type;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String user_name, String password, UserType user_type) {
		super();
		this.user_name = user_name;
		this.password = password;
		this.user_type = user_type;
	}
	public String getUserName() {
		return user_name;
	}
	public void setUserName(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserType getUserType() {
		return user_type;
	}
	public void setUserType(UserType user_type) {
		this.user_type = user_type;
	}
	@Override
	public String toString() {
		return "User [user_name=" + user_name + ", user_type=" + user_type + "]";
	}
	
}
