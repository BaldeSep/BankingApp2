package com.bank.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.bank.bo.MoneyTransferBO;
import com.bank.bo.impl.MoneyTransferBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.MoneyTransferJSONResponse;
import com.bank.to.User;

class ViewMoneyTransfersTest {

	@Test
	void test() {
		MoneyTransferBO transferBO = new MoneyTransferBOImpl();
		User user = new User();
		user.setUserName("killian");
		List<MoneyTransferJSONResponse> transfers = null;
		try {
			transfers = transferBO.getMoneyTransfers(user);
		} catch (BusinessException e) {
			System.out.println(e);
		}
		
		for(MoneyTransferJSONResponse res : transfers) {
			System.out.println(res);
		}
		assertEquals(false, transfers == null);
	}

}
