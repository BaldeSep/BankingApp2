package com.bank.dao;

import java.util.List;

import com.bank.exceptions.BusinessException;
import com.bank.to.Application;
import com.bank.to.BankAccount;
import com.bank.to.User;

public interface BankAccountApplicationDAO {
	boolean applyForBankAccount(User user, double initialBalance) throws BusinessException;
	BankAccount approveBankAccount(int applicationId) throws BusinessException;
	BankAccount denyBankAccount(int applicationId) throws BusinessException;
	List<Application> viewApplications(User user) throws BusinessException;
	Application viewApplication(int applicationId) throws BusinessException;
}
