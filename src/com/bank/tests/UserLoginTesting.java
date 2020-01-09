package com.bank.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.bank.bo.UserBO;
import com.bank.bo.impl.UserBOImpl;
import com.bank.dao.UserDAO;
import com.bank.dao.impl.UserDAOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.User;

public class UserLoginTesting {
	private UserDAO userDAO;
	private User validUser;
	private User invalidUser;
	private User invalidUser2;
	
	private UserBO userBO;
	@Before
	public void initiateClasses() {
		userDAO = new UserDAOImpl();
		validUser = new User();
		validUser.setUserName("admin");
		validUser.setPassword("admin");
		invalidUser = new User();
		invalidUser.setUserName("12kndscnkscnskdnkcndkjcnkjdcnksnckdnc");
		invalidUser.setPassword("kjnscnksdcnksdnckjsdcksdcjsnkcnsdkcsdkcndskc");
		userBO = new UserBOImpl();
		invalidUser2 = new User();
		invalidUser2.setUserName("bal de");
		invalidUser2.setPassword("a c");
	}
	
	@Test
	public void validUserLogin() {
		User userIsValid = null;
		try {
			userIsValid = userDAO.validateUser(validUser);
		} catch (BusinessException e) {
			System.out.println(e);
		}
		assertEquals(true, userIsValid != null);
	}
	
	@Test
	public void invalidUserLogin() {
		User userIsValid = null;
		try {
			userIsValid = userDAO.validateUser(invalidUser);
		}catch(BusinessException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(false, userIsValid != null);
		
	}
	
	@Test
	public void validUserBO() {
		User userIsValid = null;
		try {
			userIsValid = userBO.validateUser(validUser);
		} catch (BusinessException e) {
			System.out.println(e);
		}
		assertEquals(true, userIsValid != null);
	}
	
	@Test
	public void invalidUserBO() {
		User userIsValid = null;
		try {
			userIsValid = userBO.validateUser(invalidUser2);
		} catch (BusinessException e) {
			System.out.println(e);
		}
		assertEquals(false, userIsValid != null);
	}
	
	
}

