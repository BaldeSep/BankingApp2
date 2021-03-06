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
		if(initialBalance < 0) {
			throw new BusinessException("Initial Balance Needs To Be Greater Than $0.00");
		}
		return new BankAccountApplicationDAOImpl().applyForBankAccount(user, initialBalance);
	}

	@Override
	public BankAccount approveBankAccount(int applicationId) throws BusinessException {
		return new BankAccountApplicationDAOImpl().approveBankAccount(applicationId);
	}

	@Override
	public boolean denyBankAccount(int applicationId) throws BusinessException {
		return new BankAccountApplicationDAOImpl().denyBankAccount(applicationId);
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

	@Override
	public List<Application> viewApplications() throws BusinessException {
		return new BankAccountApplicationDAOImpl().viewApplications();
	}

}
