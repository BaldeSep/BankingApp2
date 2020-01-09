package com.bank.to;

import com.bank.to.types.UserType;

public class User {
	private String username;
	private String password;
	private UserType user_type;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String username, String password, UserType user_type) {
		super();
		this.username = username;
		this.password = password;
		this.user_type = user_type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserType getUser_type() {
		return user_type;
	}
	public void setUser_type(UserType user_type) {
		this.user_type = user_type;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", user_type=" + user_type + "]";
	}
	
}
