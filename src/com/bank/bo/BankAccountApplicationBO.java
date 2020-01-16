package com.bank.bo;

import java.util.List;

import com.bank.exceptions.BusinessException;
import com.bank.to.Application;
import com.bank.to.BankAccount;
import com.bank.to.User;

public interface BankAccountApplicationBO {
	boolean applyForBankAccount( User user, double initialBalance ) throws BusinessException;
	BankAccount approveBankAccount(int applicationId) throws BusinessException; 
	boolean denyBankAccount(int applicationId) throws BusinessException;
	List<Application> viewApplications(User user) throws BusinessException;
	List<Application> viewApplications() throws BusinessException;
	Application viewApplication(int applicationId) throws BusinessException;
}
