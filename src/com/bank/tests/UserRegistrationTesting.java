package com.bank.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.bank.bo.UserBO;
import com.bank.bo.impl.UserBOImpl;
import com.bank.dao.UserDAO;
import com.bank.dao.impl.UserDAOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.User;
import com.bank.to.types.UserType;

public class UserRegistrationTesting {
	private UserDAO userDAO;
	private UserBO userBO;
	private User invalidUser;
	private User validUser;
	@Before
	public void initValues() {
		userBO = new UserBOImpl();
		userDAO = new UserDAOImpl();
		invalidUser = new User();
		invalidUser.setUserName("balde");
		invalidUser.setPassword("123");
		invalidUser.setUserType(UserType.Customer);
		validUser = new User("newuser", "abc123", UserType.Customer);
	}
	
	
	@Test
	public void testValidUserRegistration() {
		User registeredUser = null;
		String passwordOne,passwordTwo;
		passwordOne = "abc";
		passwordTwo = "abc";
		try {
			registeredUser = userBO.registerUser(validUser, passwordOne, passwordTwo);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(true, registeredUser != null);
	}

}
