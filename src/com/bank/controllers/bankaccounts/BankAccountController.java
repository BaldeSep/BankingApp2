package com.bank.controllers.bankaccounts;

import java.io.IOException;
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
 * Servlet implementation class BankAccountController
 */
@WebServlet("/bankaccount")
public class BankAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankAccountController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			response.setContentType("application/json");
			Gson gson = new Gson();
			int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
			BankAccountViewBO viewBO = new BankAccountViewBOImpl();
			BankAccount queriedAccount = null;
			try {
				queriedAccount = viewBO.getBankAccount(accountNumber);
				if(queriedAccount != null) {
					String jsonAccount = gson.toJson(queriedAccount);
					response.setStatus(200);
					response.getWriter().print(jsonAccount);
				}else {
					throw new BusinessException("Sorry The Account Could not Be Found");
				}
			} catch (BusinessException e) {
				response.setStatus(500);
				String message = gson.toJson(new MessageResponse(e.getMessage()));
				response.getWriter().print(message);
			}
		}else {
			response.sendRedirect(request.getContextPath() + "/");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
