package com.bank.dao;

import java.util.List;

import com.bank.exceptions.BusinessException;
import com.bank.to.TransactionLog;

public interface TransactionLogViewDAO {
	List<TransactionLog> getLogs() throws BusinessException;
}
