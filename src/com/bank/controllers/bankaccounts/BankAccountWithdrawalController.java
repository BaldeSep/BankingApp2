package com.bank.controllers.bankaccounts;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bank.bo.BankAccountTransactionBO;
import com.bank.bo.impl.BankAccountTransactionBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;
import com.bank.to.MessageResponse;
import com.bank.to.Transaction;
import com.google.gson.Gson;

/**
 * Servlet implementation class BankAccountWithdrawalController
 */
@WebServlet("/withdrawal")
public class BankAccountWithdrawalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(BankAccountWithdrawalController.class); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankAccountWithdrawalController() {
        super();
    }

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			response.setContentType("application/json");
			Gson gson = new Gson();
			Transaction transaction = gson.fromJson(request.getReader(), Transaction.class);
			BankAccount account = new BankAccount();
			account.setAccountNumber(transaction.getAccountNumber());
			double amount = transaction.getAmount();
			BankAccountTransactionBO bankBO = new BankAccountTransactionBOImpl();
			try {
				BankAccount updatedAccount = bankBO.makeWithdrawal(account, amount);
				if(updatedAccount != null) {
					log.info("Account: " + account + " Updated By: " + amount);
					String message = gson.toJson(new MessageResponse(String.valueOf(amount)));
					response.setStatus(200);
					response.getWriter().print(message);
				}else {
					throw new BusinessException("Sorry The Deposit Could Not Be Completed");
				}
			} catch (BusinessException e) {
				log.error(e);
				String message = gson.toJson(new MessageResponse(e.getMessage()));
				response.setStatus(500);
				response.getWriter().print(message);
			}
		}else {
			log.error("Invalid Session Redirecting To The Login Page");
			response.sendRedirect( request.getContextPath() +  "/");
		}
	}

}
