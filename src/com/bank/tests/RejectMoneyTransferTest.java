package com.bank.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.bank.bo.MoneyTransferBO;
import com.bank.bo.impl.MoneyTransferBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.MoneyTransfer;
import com.bank.to.types.MoneyTransferStatus;

class RejectMoneyTransferTest {

	@Test
	void test() {
		int transferId = 8;
		MoneyTransferBO transferBO = new MoneyTransferBOImpl();
		MoneyTransfer transfer = null;
		try {
			transfer = transferBO.rejectMoneyTransfer(transferId);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(transfer);
		assertEquals(false, transfer == null);
	}

}
