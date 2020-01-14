package com.bank.bo.impl;

import com.bank.bo.BankAccountTransactionBO;
import com.bank.dao.impl.BankAccountTransactionDAOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;

public class BankAccountTransactionBOImpl implements BankAccountTransactionBO {

	@Override
	public BankAccount makeWithdrawal(BankAccount account, double amount) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
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
