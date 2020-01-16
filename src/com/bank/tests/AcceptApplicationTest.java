package com.bank.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.bank.bo.BankAccountApplicationBO;
import com.bank.bo.impl.BankAccountApplicationBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;

class AcceptApplicationTest {

	@Test
	void test() {
		BankAccount account = null;
		BankAccountApplicationBO accountBO = new BankAccountApplicationBOImpl();
		try {
			account = accountBO.approveBankAccount(100);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(account);
		assertEquals(false, account == null);
	}

}
