package com.bank.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.bank.bo.MoneyTransferBO;
import com.bank.bo.impl.MoneyTransferBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.MoneyTransfer;
import com.bank.to.types.MoneyTransferStatus;

class PostMoneyTransferTest {

	@Test
	void test() {
		MoneyTransferBO transBO = new MoneyTransferBOImpl();
		MoneyTransfer transfer = new MoneyTransfer(-1, 51, "balde", 81, null, 10, MoneyTransferStatus.Pending);
		MoneyTransfer addedTransfer = null;
		try {
			addedTransfer = transBO.postMoneyTransfer(transfer);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(addedTransfer);
		assertEquals(false, addedTransfer == null);
	}

}
