package com.bank.dao;

import java.util.List;

import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;
import com.bank.to.User;

public interface BankAccountViewDAO {
	List<BankAccount> getBankAccounts(User user) throws BusinessException;
	BankAccount getBankAccount(int accountNumber) throws BusinessException;
}
