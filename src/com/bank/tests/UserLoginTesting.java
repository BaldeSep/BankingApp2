package com.bank.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.bank.dao.UserDAO;
import com.bank.dao.impl.UserDAOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.User;

public class UserLoginTesting {
	private UserDAO userDAO;
	private User validUser;
	private User invalidUser;
	@Before
	public void initiateClasses() {
		userDAO = new UserDAOImpl();
		validUser = new User();
		validUser.setUserName("admin");
		validUser.setPassword("admin");
		invalidUser = new User();
		invalidUser.setUserName("12kndscnkscnskdnkcndkjcnkjdcnksnckdnc");
		invalidUser.setPassword("kjnscnksdcnksdnckjsdcksdcjsnkcnsdkcsdkcndskc");
	}
	
	@Test
	public void validUserLogin() {
		boolean userIsValid = false;
		try {
			userIsValid = userDAO.validateUser(validUser);
		} catch (BusinessException e) {
			System.out.println(e);
		}
		assertEquals(true, userIsValid);
	}
	
	@Test
	public void invalidUserLogin() {
		boolean userIsValid = false;
		try {
			userIsValid = userDAO.validateUser(invalidUser);
		}catch(BusinessException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(false, userIsValid);
		
	}
}

