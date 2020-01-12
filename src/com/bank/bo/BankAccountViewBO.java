package com.bank.bo;

import java.util.List;

import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;
import com.bank.to.User;

public interface BankAccountViewBO {
	List<BankAccount> getBankAccounts(User user) throws BusinessException; 
	BankAccount getBankAccount(int accountNumber) throws BusinessException;
}
