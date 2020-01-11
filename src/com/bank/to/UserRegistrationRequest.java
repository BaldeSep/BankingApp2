package com.bank.to;

public class UserRegistrationRequest {
	private String userName;
	private String passwordOne;
	private String passwordTwo;
	public UserRegistrationRequest() {
		// TODO Auto-generated constructor stub
	}
	
	public UserRegistrationRequest(String userName, String passwordOne, String passwordTwo) {
		super();
		this.userName = userName;
		this.passwordOne = passwordOne;
		this.passwordTwo = passwordTwo;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswordOne() {
		return passwordOne;
	}
	public void setPasswordOne(String passwordOne) {
		this.passwordOne = passwordOne;
	}
	public String getPasswordTwo() {
		return passwordTwo;
	}
	public void setPasswordTwo(String passwordTwo) {
		this.passwordTwo = passwordTwo;
	}
	@Override
	public String toString() {
		return "UserRegistrationRequest [userName=" + userName + ", passwordOne=" + passwordOne + ", passwordTwo="
				+ passwordTwo + "]";
	}
	
}
