package com.bank.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.bank.bo.TransferLogViewBO;
import com.bank.bo.impl.TransferLogViewBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.TransferLog;

class TransferLogViewTest {

	@Test
	void test() {
		TransferLogViewBO transBO = new TransferLogViewBOImpl();
		List<TransferLog> logs = null;
		try {
			logs = transBO.getLogs();
		}catch(BusinessException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(logs);
		assertEquals(false, logs == null);	
	}

}
