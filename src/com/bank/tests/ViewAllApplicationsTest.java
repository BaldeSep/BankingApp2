package com.bank.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.bank.bo.BankAccountApplicationBO;
import com.bank.bo.impl.BankAccountApplicationBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.Application;

class ViewAllApplicationsTest {

	@Test
	void test() {
		BankAccountApplicationBO bankBO = new BankAccountApplicationBOImpl();
		List<Application> applications = null;
		try {
			applications = bankBO.viewApplications();
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		for(Application app : applications) {
			System.out.println(app);
		}
		assertEquals(false, applications == null);
	}

}
