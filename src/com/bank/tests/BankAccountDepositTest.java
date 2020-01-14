package com.bank.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.bank.bo.BankAccountTransactionBO;
import com.bank.bo.impl.BankAccountTransactionBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;

class BankAccountDepositTest {

	@Test
	void test() {
		BankAccountTransactionBO  transactionBO = new BankAccountTransactionBOImpl();
		BankAccount account = new BankAccount(51, "balde", 130, null);
		BankAccount updatedAccount = null;
		try {
			updatedAccount = transactionBO.makeDeposit(account, -30.00);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(updatedAccount);
		assertEquals(false, updatedAccount == null);
	}

}
