package com.bank.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.bank.bo.BankAccountViewBO;
import com.bank.bo.impl.BankAccountViewBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;

class BankAccountViewSingleAccountTest {

	@Test
	void test() {
		int account = 100;
		BankAccountViewBO viewBO = new BankAccountViewBOImpl();
		BankAccount found = null;
		try {
			found = viewBO.getBankAccount(account);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(found);
		assertEquals(false, found == null);
	}

}
