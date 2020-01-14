package com.bank.bo.impl;

import java.util.List;

import com.bank.bo.BankAccountViewBO;
import com.bank.dao.impl.BankAccountViewDAOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;
import com.bank.to.User;

public class BankAccountViewBOImpl implements BankAccountViewBO {

	@Override
	public List<BankAccount> getBankAccounts(User user) throws BusinessException {
		return new BankAccountViewDAOImpl().getBankAccounts(user);
	}

	@Override
	public BankAccount getBankAccount(int accountNumber)throws BusinessException {
		return new BankAccountViewDAOImpl().getBankAccount(accountNumber);
	}

}
