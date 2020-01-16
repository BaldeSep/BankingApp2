package com.bank.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.bank.bo.BankAccountApplicationBO;
import com.bank.bo.impl.BankAccountApplicationBOImpl;
import com.bank.exceptions.BusinessException;

class RejectApplicationTest {

	@Test
	void test() {
		boolean processed = false;
		BankAccountApplicationBO accountBO = new BankAccountApplicationBOImpl();
		try {
			processed = accountBO.denyBankAccount(2);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals(true, processed);
	}

}
