package com.bank.bo.impl;

import java.util.List;

import com.bank.bo.BankAccountTransactionBO;
import com.bank.bo.BankAccountViewBO;
import com.bank.bo.MoneyTransferBO;
import com.bank.dao.impl.MoneyTransferDAOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;
import com.bank.to.MoneyTransfer;
import com.bank.to.User;

public class MoneyTransferBOImpl implements MoneyTransferBO {

	@Override
	public List<MoneyTransfer> getMoneyTransfers(User user) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MoneyTransfer postMoneyTransfer(MoneyTransfer transfer) throws BusinessException {
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
