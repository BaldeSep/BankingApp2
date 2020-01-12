package com.bank.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.bank.dao.BankAccountApplicationDAO;
import com.bank.dao.impl.BankAccountApplicationDAOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.User;
import com.bank.to.types.UserType;

class ApplyForBankAccountTest {

	@Test
	void test() {
		BankAccountApplicationDAO bankAddDAO = new BankAccountApplicationDAOImpl();
		boolean success = false;
		try {
			success = bankAddDAO.applyForBankAccount(new User("balde", "123", UserType.Customer), 100);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals(true, success);
		
	}

}
