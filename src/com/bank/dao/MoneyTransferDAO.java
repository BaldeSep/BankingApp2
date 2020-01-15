package com.bank.dao;

import java.util.List;

import com.bank.exceptions.BusinessException;
import com.bank.to.MoneyTransfer;
import com.bank.to.User;

public interface MoneyTransferDAO {
	List<MoneyTransfer> getMoneyTransfers(User user) throws BusinessException;
	MoneyTransfer postMoneyTransfer(MoneyTransfer transfer) throws BusinessException;
	MoneyTransfer acceptMoneyTransfer(int transferId) throws BusinessException;
	MoneyTransfer rejectMoneyTransfer(int transferId) throws BusinessException;
	MoneyTransfer deleteMoneyTransfer(int transferId) throws BusinessException;
}
