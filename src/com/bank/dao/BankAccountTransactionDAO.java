package com.bank.dao;

import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;

public interface BankAccountTransactionDAO {
	BankAccount makeDeposit( BankAccount account, double amount ) throws BusinessException;
	BankAccount makeWithdrawal( BankAccount account, double amount ) throws BusinessException;
}
