package com.bank.controllers.bankaccounts;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.bo.BankAccountTransactionBO;
import com.bank.bo.impl.BankAccountTransactionBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;
import com.bank.to.Transaction;
import com.google.gson.Gson;

/**
 * Servlet implementation class BankAccountDepositController
 */
@WebServlet("/deposit")
public class BankAccountDepositController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
			Transaction transaction = gson.fromJson(request.getReader(), Transaction.class);
			BankAccount account = new BankAccount();
			account.setAccountNumber(transaction.getAccountNumber());
			double amount = transaction.getAmount();
			BankAccountTransactionBO bankBO = new BankAccountTransactionBOImpl();
			try {
				BankAccount updatedAccount = bankBO.makeDeposit(account, amount);
				if(updatedAccount != null) {
					String jsonAccount = gson.toJson(updatedAccount);
					response.setContentType("application/json");
					response.setStatus(200);
					response.getWriter().print(jsonAccount);
				}else {
					throw new BusinessException("Sorry The Deposit Could Not Be Completed");
				}
			} catch (BusinessException e) {
				response.setContentType("text/plain");
				response.setStatus(500);
				response.getWriter().print(e.getMessage());
			}
		}
	}

}
