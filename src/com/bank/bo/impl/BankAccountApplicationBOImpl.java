package com.bank.bo.impl;

import java.util.List;

import com.bank.bo.BankAccountApplicationBO;
import com.bank.dao.impl.BankAccountApplicationDAOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.Application;
import com.bank.to.BankAccount;
import com.bank.to.User;

public class BankAccountApplicationBOImpl implements BankAccountApplicationBO {

	@Override
	public boolean applyForBankAccount(User user, double initialBalance) throws BusinessException {
		return new BankAccountApplicationDAOImpl().applyForBankAccount(user, initialBalance);
	}

	@Override
	public BankAccount approveBankAccount(int applicationId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount denyBankAccount(int applicationId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Application> viewApplications(User user) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Application viewApplication(int applicationId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
