package com.bank.bo;

import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;

public interface BankAccountTransactionBO {
	BankAccount makeWithdrawal(BankAccount account, double amount) throws BusinessException;
	BankAccount makeDeposit(BankAccount account, double amount) throws BusinessException;
}
