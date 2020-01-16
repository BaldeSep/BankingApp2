package com.bank.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.bank.bo.TransactionLogViewBO;
import com.bank.bo.impl.TransactionLogViewBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.TransactionLog;

class TransactionLogViewsTest {

	@Test
	void test() {
		List<TransactionLog> logs = null;
		TransactionLogViewBO logBO = new TransactionLogViewBOImpl();
		try {
			logs = logBO.getLogs();
		}catch(BusinessException e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals(false, logs == null);
	}

}
