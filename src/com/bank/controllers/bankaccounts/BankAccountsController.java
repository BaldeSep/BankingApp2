package com.bank.controllers.bankaccounts;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.bo.BankAccountViewBO;
import com.bank.bo.impl.BankAccountViewBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;
import com.bank.to.User;
import com.google.gson.Gson;

/**
 * Servlet implementation class ViewAllAccountsController
 */
@WebServlet("/bankaccounts")
public class BankAccountsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankAccountsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BankAccount> accounts;
		Gson gson = new Gson();
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.setStatus(401);
			response.sendRedirect(request.getContextPath() + "/");
		}else {
			BankAccountViewBO accountViewBO = new BankAccountViewBOImpl();
			User user = (User) session.getAttribute("user");
			try {
				accounts = accountViewBO.getBankAccounts(user);
				String accountsJson = gson.toJson(accounts);
				response.setStatus(200);
				response.setContentType("application/json");
				response.getWriter().print(accountsJson);
			} catch (BusinessException e) {
				response.setContentType("text/plain");
				response.setStatus(500);
				response.getWriter().print(e.getMessage());
			}			
		}
		
	}

}
