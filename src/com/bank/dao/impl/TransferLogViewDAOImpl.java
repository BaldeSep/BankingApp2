package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bank.dao.TransferLogViewDAO;
import com.bank.exceptions.BusinessException;
import com.bank.to.TransferLog;
import com.bank.util.OracleDBConnection;

public class TransferLogViewDAOImpl implements TransferLogViewDAO {

	@Override
	public List<TransferLog> getLogs() throws BusinessException {
		List<TransferLog> logs = null;
		try(Connection conn = OracleDBConnection.getConnection()){
			String sql = "Select * From TransferLogs";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			int transactionId, 
				sourceAccount, 
				destinationAccount;
			String sourceHolder, 
				destinationHolder;
			double amount;
			Date dateOfPosting;
			while(results.next()) {
				if(logs == null) {
					logs = new ArrayList<>();
				}
				transactionId = results.getInt("transaction_id");
				sourceAccount = results.getInt("source_account");
				destinationAccount = results.getInt("destination_account");
				sourceHolder = results.getString("source_holder");
				destinationHolder = results.getString("destination_holder");
				amount = results.getDouble("amount");
				dateOfPosting = results.getDate("date_of_posting");
				logs.add(new TransferLog(
						transactionId, 
						sourceAccount, 
						destinationAccount, 
						sourceHolder, 
						destinationHolder, 
						amount, 
						dateOfPosting));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("There Was An Internal Error");
		}
		return logs;
	}

}
