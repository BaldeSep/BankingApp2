package com.bank.bo;

import java.util.List;

import com.bank.exceptions.BusinessException;
import com.bank.to.TransactionLog;

public interface TransactionLogViewBO {
	List<TransactionLog> getLogs()throws BusinessException;
}
