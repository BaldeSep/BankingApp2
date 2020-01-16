package com.bank.bo;

import java.util.List;

import com.bank.exceptions.BusinessException;
import com.bank.to.TransferLog;

public interface TransferLogViewBO {
	List<TransferLog> getLogs() throws BusinessException;
}
