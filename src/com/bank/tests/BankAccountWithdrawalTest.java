package com.bank.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.bank.bo.BankAccountTransactionBO;
import com.bank.bo.impl.BankAccountTransactionBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;

class BankAccountWithdrawalTest {

	@Test
	void test() {
		BankAccountTransactionBO transBO = new BankAccountTransactionBOImpl();
		BankAccount account = new BankAccount();
		account.setAccountNumber(100);
		BankAccount updatedAccount = null; 
		try {
			updatedAccount =  transBO.makeWithdrawal(account, -10);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(updatedAccount);
		assertEquals(false, updatedAccount == null);
		
	}

}
