package com.bank.bo.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.bank.bo.BankAccountTransactionBO;
import com.bank.bo.BankAccountViewBO;
import com.bank.bo.MoneyTransferBO;
import com.bank.dao.impl.MoneyTransferDAOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;
import com.bank.to.MoneyTransfer;
import com.bank.to.User;
import com.bank.util.OracleDBConnection;

public class MoneyTransferBOImpl implements MoneyTransferBO {

	@Override
	public List<MoneyTransfer> getMoneyTransfers(User user) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MoneyTransfer postMoneyTransfer(MoneyTransfer transfer) throws BusinessException {
		if(transfer.getAmount() < 0) {
			throw new BusinessException("Invalid Amount, Must Be Greater Than $0.00");
		}
		BankAccountViewBO viewBO = new BankAccountViewBOImpl();
		BankAccount sourceBankAccount = viewBO.getBankAccount(transfer.getSourceAccount());
		if(sourceBankAccount.getBalance() - transfer.getAmount() < 0) {
			throw new BusinessException("Cannot Post Transfer Source Account Lacks Sufficient Funds!!!");
		}
		BankAccount destinationAccount = viewBO.getBankAccount(transfer.getDestinationAccount());
		transfer.setDestinationUserName(destinationAccount.getHolder());
		return new MoneyTransferDAOImpl().postMoneyTransfer(transfer);
	}

	@Override
	public MoneyTransfer acceptMoneyTransfer(int transferId) throws BusinessException {
		// Get Money Transfer
		MoneyTransfer transfer = new MoneyTransferDAOImpl().acceptMoneyTransfer(transferId);
		
		// Get all accounts and amount to complete transfer
		BankAccountViewBO viewBO = new BankAccountViewBOImpl();
		BankAccount sourceAccount = viewBO.getBankAccount(transfer.getSourceAccount());
		BankAccount destinationAccount = viewBO.getBankAccount(transfer.getDestinationAccount());
		double amount = transfer.getAmount();
		
		// Making Transfer
		BankAccountTransactionBO transactionBO = new BankAccountTransactionBOImpl();
		transactionBO.makeWithdrawal(sourceAccount, amount);
		transactionBO.makeDeposit(destinationAccount, amount);
		
		// Return transfer when done
		return transfer;
	}

	@Override
	public MoneyTransfer rejectMoneyTransfer(int transferId) throws BusinessException {
		return new MoneyTransferDAOImpl().rejectMoneyTransfer(transferId);
	}

	@Override
	public MoneyTransfer deleteMoneyTransfer(int transferId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
