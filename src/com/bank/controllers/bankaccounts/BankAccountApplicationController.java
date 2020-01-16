package com.bank.controllers.bankaccounts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

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
import com.bank.to.types.ApplicationStatus;
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
	
	// Used By Employees To View All Applications
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		if(session != null) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			List<Application> appliations = null;
			BankAccountApplicationBO accountBO = new BankAccountApplicationBOImpl();
			try {
				appliations = accountBO.viewApplications();
				if(appliations != null) {
					String jsonApplications = gson.toJson(appliations);
					response.setStatus(200);
					response.getWriter().print(jsonApplications);
				}else {
					throw new BusinessException("No Valid Applications Found");
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
	
	// For Accepting and Rejecting Accounts
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		if(session != null) {
			User user = (User) session.getAttribute("user");
			if(user != null && user.getUserType() == UserType.Employee) {
				Gson gson = new Gson();
				response.setContentType("application/json");
				response.setContentType("application/json");
				Map<String, String[]> req = request.getParameterMap();
				int applicationId = Integer.parseInt(req.get("applicationId")[0]);
				ApplicationStatus status = ApplicationStatus.fromInt( Integer.parseInt( req.get("status")[0] ) );
				BankAccountApplicationBO applicationBO = new BankAccountApplicationBOImpl();
				try {
					BankAccount approvedAccount = null;
					boolean accountRejected = false;
					if(status == null) {
						throw new BusinessException("Invalid Status Must Be Between 0 and 2");
					}
					switch(status) {
					case Accepted:
						approvedAccount = applicationBO.approveBankAccount(applicationId);
						if(approvedAccount != null) {
							String jsonAccount = gson.toJson(approvedAccount);
							response.setStatus(200);
							response.getWriter().print(jsonAccount);
						}else {
							throw new BusinessException("Application Could Not Be Found");
						}
						break;
					case Denied:
						accountRejected  = applicationBO.denyBankAccount(applicationId);
						if(accountRejected) {
							String message = gson.toJson(new MessageResponse("Account Successfully Rejected"));
							response.setStatus(200);
							response.getWriter().print(message);
						}else {
							throw new BusinessException("Application Could Not Be Rejected");
						}
						break;
					default:
						throw new BusinessException("Could Not Process Application");
					}
					
				}catch(BusinessException e) {
					response.setStatus(500);
					String message = gson.toJson(new MessageResponse(e.getMessage()));
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
