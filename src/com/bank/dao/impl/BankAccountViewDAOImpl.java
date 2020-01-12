package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.dao.BankAccountViewDAO;
import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;
import com.bank.to.User;
import com.bank.util.OracleDBConnection;

public class BankAccountViewDAOImpl implements BankAccountViewDAO {

	@Override
	public List<BankAccount> getBankAccounts(User user) throws BusinessException {
		List<BankAccount> accounts = new ArrayList<>();
		try(Connection conn = OracleDBConnection.getConnection()){
			String sql = "Select account_number, holder, balance, date_created From BankAccounts Where holder = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getUserName());
			ResultSet results = statement.executeQuery();
			BankAccount temp;
			while(results.next()) {
				temp = new BankAccount();
				temp.setAccountNumber(results.getInt("account_number"));
				temp.setBalance(results.getDouble("balance"));
				temp.setHolder(results.getString("holder"));
				temp.setDateCreated(results.getDate("date_created"));
				accounts.add(temp);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage());
		}
		return accounts;
	}

	@Override
	public BankAccount getBankAccount(int accountNumber) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
