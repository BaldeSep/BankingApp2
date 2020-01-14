package com.bank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.Date;

import com.bank.dao.BankAccountTransactionDAO;
import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;
import com.bank.util.OracleDBConnection;

public class BankAccountTransactionDAOImpl implements BankAccountTransactionDAO {

	@Override
	public BankAccount makeDeposit(BankAccount account, double amount) throws BusinessException {
		return makeTransaction(account, amount, "makeDeposit");
	}

	@Override
	public BankAccount makeWithdrawal(BankAccount account, double amount) throws BusinessException {
		return makeTransaction(account, amount, "makeWithdrawal");
	}
	
	private BankAccount makeTransaction(BankAccount account, double amount, String procedure) throws BusinessException {
		BankAccount updatedAccount = null;
		try(Connection conn = OracleDBConnection.getConnection()){
			String sql = "{call" + procedure + "(?,?,?,?,?,?)}";
			CallableStatement statement = conn.prepareCall(sql);
			statement.setInt(1, account.getAccountNumber());
			statement.setDouble(2, amount);
			statement.registerOutParameter(3, java.sql.Types.NUMERIC);
			statement.registerOutParameter(4, java.sql.Types.VARCHAR);
			statement.registerOutParameter(5, java.sql.Types.NUMERIC);
			statement.registerOutParameter(6, java.sql.Types.DATE);
			statement.executeUpdate();
			int accountNumber = statement.getInt(3);
			String holder = statement.getString(4);
			double balance = statement.getDouble(5);
			Date dateCreated = statement.getDate(6);
			updatedAccount = new BankAccount(accountNumber, holder, balance, dateCreated);
		}catch(SQLException | ClassNotFoundException e) {
			throw new BusinessException("Sorry There Was An Internal Server Error");
		}
		return updatedAccount;
	}

}
