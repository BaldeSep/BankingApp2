package com.bank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bank.dao.MoneyTransferDAO;
import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;
import com.bank.to.MoneyTransfer;
import com.bank.to.User;
import com.bank.to.types.MoneyTransferStatus;
import com.bank.util.OracleDBConnection;

public class MoneyTransferDAOImpl implements MoneyTransferDAO {

	@Override
	public List<MoneyTransfer> getMoneyTransfers(User user) throws BusinessException {
		return null;
	}

	@Override
	public MoneyTransfer postMoneyTransfer(MoneyTransfer transfer) throws BusinessException {
		MoneyTransfer addedTransfer = null;
		try(Connection conn = OracleDBConnection.getConnection()){
			String insertTransfer = "{ call postmoneytransfer(?,?,?,?,?,?,?,?,?,?,?) }";
			CallableStatement insertStatement = conn.prepareCall(insertTransfer);
			insertStatement.setInt(1, transfer.getSourceAccount());
			insertStatement.setString(2, transfer.getSourceUserName());
			insertStatement.setInt(3, transfer.getDestinationAccount());
			insertStatement.setString(4, transfer.getDestinationUserName());
			insertStatement.setDouble(5, transfer.getAmount());
			
			insertStatement.registerOutParameter(6, java.sql.Types.NUMERIC);
			insertStatement.registerOutParameter(7, java.sql.Types.NUMERIC);
			insertStatement.registerOutParameter(8, java.sql.Types.VARCHAR);
			insertStatement.registerOutParameter(9, java.sql.Types.NUMERIC);
			insertStatement.registerOutParameter(10, java.sql.Types.VARCHAR);
			insertStatement.registerOutParameter(11, java.sql.Types.DOUBLE);
			
			int transferId, sourceAccount, destinationAccount;
			String sourceHolder, destinationHolder;
			double amount;
			
			int updateCount = insertStatement.executeUpdate();
			if(updateCount == 1) {
				transferId = insertStatement.getInt(6);
				sourceAccount = insertStatement.getInt(7);
				sourceHolder = insertStatement.getString(8);
				destinationAccount= insertStatement.getInt(9);
				destinationHolder= insertStatement.getString(10);
				amount = insertStatement.getDouble(11);
				addedTransfer = new MoneyTransfer(
						transferId, 
						sourceAccount, 
						sourceHolder, 
						destinationAccount, 
						destinationHolder, 
						amount, 
						MoneyTransferStatus.Pending);
			}else {
				throw new BusinessException("Sorry The Transfer Could Not Be Posted...");
			}
			
		}catch( SQLException | ClassNotFoundException e) {
			throw new BusinessException("Internal Error");
		}
		return addedTransfer;
	}

	@Override
	public MoneyTransfer acceptMoneyTransfer(int transferId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MoneyTransfer rejectMoneyTransfer(int transferId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MoneyTransfer deleteMoneyTransfer(int transferId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
