package com.bank.bo.impl;

import java.util.List;

import com.bank.bo.TransferLogViewBO;
import com.bank.dao.impl.TransferLogViewDAOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.TransferLog;

public class TransferLogViewBOImpl implements TransferLogViewBO {

	@Override
	public List<TransferLog> getLogs() throws BusinessException {
		return new TransferLogViewDAOImpl().getLogs();
	}

}
