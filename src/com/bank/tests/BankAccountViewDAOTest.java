package com.bank.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.bank.dao.BankAccountViewDAO;
import com.bank.dao.impl.BankAccountViewDAOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;
import com.bank.to.User;
import com.bank.to.types.UserType;

class BankAccountViewDAOTest {
	@Test
	void test() {
		BankAccountViewDAO bankDAO = new BankAccountViewDAOImpl();
		List<BankAccount> accounts = null;
		try{
			accounts = bankDAO.getBankAccounts(new User("balde", "123", UserType.Customer));
		}catch(BusinessException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(false, accounts.size() == 0);
		
	}

}
