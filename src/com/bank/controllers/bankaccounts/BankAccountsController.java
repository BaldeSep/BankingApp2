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
import com.bank.to.MessageResponse;
import com.bank.to.User;
import com.bank.to.types.UserType;
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
    }
	
	// Used By Employees And Customers To View Accounts For Specific Users
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		if(session != null) {
			User user = (User) session.getAttribute("user");
			if(user != null) {
				response.setContentType("application/json");
				Gson gson = new Gson();
				User customer = null;
				if(user.getUserType() == UserType.Employee) {
					customer = new User();
					customer.setUserName(request.getParameter("user_name"));
				}else if(user.getUserType() == UserType.Customer) {
					customer = user;
				}
				if(customer == null) {
					session.invalidate();
					response.sendRedirect(request.getContextPath() + "/");
				}
				BankAccountViewBO accountViewBO = new BankAccountViewBOImpl(); 
				List<BankAccount> accounts = null;
				try {
					accounts = accountViewBO.getBankAccounts(customer);
					if(accounts != null && !accounts.isEmpty()) {
						String jsonAccounts = gson.toJson(accounts);
						response.setStatus(200);
						response.getWriter().print(jsonAccounts);
					}else {
						throw new BusinessException("No Valid Accounts Could Be Found");
					}
				} catch (BusinessException e) {
					String message = gson.toJson(new MessageResponse(e.getMessage()));
					response.setStatus(500);
					response.getWriter().print(message);
				}
			}else {
				session.invalidate();
				response.sendRedirect(request.getContextPath() + "/");
			}
		}else {
			response.sendRedirect(request.getContextPath() + "/");
		}
	}

}
