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
 * Servlet implementation class BankAccountDepositController
 */
@WebServlet("/deposit")
public class BankAccountDepositController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(BankAccountDepositController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankAccountDepositController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			Transaction transaction = gson.fromJson(request.getReader(), Transaction.class);
			BankAccount account = new BankAccount();
			account.setAccountNumber(transaction.getAccountNumber());
			double amount = transaction.getAmount();
			BankAccountTransactionBO bankBO = new BankAccountTransactionBOImpl();
			try {
				BankAccount updatedAccount = bankBO.makeDeposit(account, amount);
				if(updatedAccount != null) {
					String message = gson.toJson(new MessageResponse(String.valueOf(updatedAccount.getBalance())));
					response.setStatus(200);
					log.info("Sending Updated Account After Deposit: " + message);
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
			log.info("Invalid Session Redirecting To The Login Page");
			response.sendRedirect( request.getContextPath() +  "/");
		}
	}

}
