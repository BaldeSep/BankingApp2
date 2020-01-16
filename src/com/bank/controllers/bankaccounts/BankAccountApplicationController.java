package com.bank.controllers.bankaccounts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.bo.BankAccountApplicationBO;
import com.bank.bo.BankAccountViewBO;
import com.bank.bo.impl.BankAccountApplicationBOImpl;
import com.bank.bo.impl.BankAccountViewBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.Application;
import com.bank.to.BankAccount;
import com.bank.to.MessageResponse;
import com.bank.to.User;
import com.bank.to.types.UserType;
import com.google.gson.Gson;

/**
 * Servlet implementation class BankAccountApplicationController
 */
@WebServlet("/apply")
public class BankAccountApplicationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankAccountApplicationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			User user = (User) session.getAttribute("user");
			Application application = new Gson().fromJson(request.getReader(), Application.class);
			double initialBalance = application.getInitialBalance();
			BankAccountApplicationBO applicationBO = new BankAccountApplicationBOImpl();
			try {
				boolean success = applicationBO.applyForBankAccount(user, initialBalance);
				if(success) {
					response.setStatus(200);
					out.print("Application Sent");
				}else {
					response.setStatus(500);
					out.print("There Was An Error Processing Your Application");
				}
				
			} catch (BusinessException e) {
				response.setStatus(500);
				out.print(e.getMessage());
			}
		}
	}
	
	// Used By Employees To View Accounts For Specific Users
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		if(session != null) {
			User user = (User) session.getAttribute("user");
			if(user != null && user.getUserType() == UserType.Employee) {
				response.setContentType("application/json");
				Gson gson = new Gson();
				User customer = gson.fromJson(request.getReader(), User.class);
				BankAccountViewBO accountViewBO = new BankAccountViewBOImpl(); 
				List<BankAccount> accounts = null;
				try {
					accounts = accountViewBO.getBankAccounts(customer);
					if(accounts != null && accounts.size() > 0) {
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
