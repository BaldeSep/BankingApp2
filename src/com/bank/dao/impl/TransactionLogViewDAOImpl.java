package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bank.dao.TransactionLogViewDAO;
import com.bank.exceptions.BusinessException;
import com.bank.to.TransactionLog;
import com.bank.to.types.TransactionType;
import com.bank.util.OracleDBConnection;

public class TransactionLogViewDAOImpl implements TransactionLogViewDAO {

	@Override
	public List<TransactionLog> getLogs() throws BusinessException {
		List<TransactionLog> logs = null;
		try(Connection conn = OracleDBConnection.getConnection()){
			String sql = "Select * From transactionlogs";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			int transactionId, 
				accountNumber,
				type;
			String accountHolder;
			double amount;
			Date dateOfTransaction;
			TransactionLog log;
			while(results.next()) {
				transactionId = results.getInt("transaction_id");
				accountNumber = results.getInt("account_number");
				accountHolder = results.getString("account_holder");
				amount = results.getDouble("amount");
				dateOfTransaction = results.getDate("date_of_transaction");
				type = results.getInt("type");
				if(logs == null) {
					logs = new ArrayList<>();
				}
				log = new TransactionLog(
						transactionId,
						accountNumber, 
						accountHolder, 
						amount, 
						dateOfTransaction,
						TransactionType.fromInt(type));
				logs.add(log);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("There Was An Internal Error");
		}
		return logs;
	}

}
