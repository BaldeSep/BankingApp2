package com.bank.dao;

import java.util.List;

import com.bank.exceptions.BusinessException;
import com.bank.to.TransferLog;

public interface TransferLogViewDAO {
	List<TransferLog> getLogs() throws BusinessException;
}
