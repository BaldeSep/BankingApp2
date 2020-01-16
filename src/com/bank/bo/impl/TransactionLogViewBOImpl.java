package com.bank.bo.impl;

import java.util.List;

import com.bank.bo.TransactionLogViewBO;
import com.bank.dao.impl.TransactionLogViewDAOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.TransactionLog;

public class TransactionLogViewBOImpl implements TransactionLogViewBO {

	@Override
	public List<TransactionLog> getLogs() throws BusinessException {
		return new TransactionLogViewDAOImpl().getLogs();
	}

}
