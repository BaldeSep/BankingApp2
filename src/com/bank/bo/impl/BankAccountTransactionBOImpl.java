package com.bank.bo.impl;

import com.bank.bo.BankAccountTransactionBO;
import com.bank.dao.impl.BankAccountTransactionDAOImpl;
import com.bank.dao.impl.BankAccountViewDAOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;

public class BankAccountTransactionBOImpl implements BankAccountTransactionBO {

	@Override
	public BankAccount makeWithdrawal(BankAccount account, double amount) throws BusinessException {
		BankAccount dbAccount = new BankAccountViewDAOImpl().getBankAccount(account.getAccountNumber());
		if(amount < 0.00) {
			throw new BusinessException("Amount Must Be Greater Than $0.00");
		}else if((dbAccount.getBalance() - amount) < 0) {
			throw new BusinessException("Could Not Withdrawal Insufficient Funds.");
		}
		return new BankAccountTransactionDAOImpl().makeWithdrawal(dbAccount, amount);
	}

	@Override
	public BankAccount makeDeposit(BankAccount account, double amount) throws BusinessException {
		if(amount > 0.0) {
			return new BankAccountTransactionDAOImpl().makeDeposit(account, amount);
		}else {
			throw new BusinessException("Invalid Amount Must Be Greater Than $0.00");
		}
	}

}
