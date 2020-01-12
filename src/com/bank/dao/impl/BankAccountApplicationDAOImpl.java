package com.bank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bank.dao.BankAccountApplicationDAO;
import com.bank.exceptions.BusinessException;
import com.bank.to.Application;
import com.bank.to.BankAccount;
import com.bank.to.User;
import com.bank.util.OracleDBConnection;

public class BankAccountApplicationDAOImpl implements BankAccountApplicationDAO {

	@Override
	public boolean applyForBankAccount(User user, double initialBalance) throws BusinessException {
		boolean applicationSuccess = false;
		try(Connection conn = OracleDBConnection.getConnection()){
			String sql = "{ call applyforbankaccount(?,?,?) }";
			CallableStatement statement = conn.prepareCall(sql);
			statement.setString(1, user.getUserName());
			statement.setDouble(2, initialBalance);
			statement.setDate(3, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
			int updateCount = statement.executeUpdate();
			if(updateCount == 1) {
				applicationSuccess = true;
			}
		}catch(ClassNotFoundException | SQLException | NullPointerException e) {
			throw new BusinessException("There Was An Internal Error");
		}
		return applicationSuccess;
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
