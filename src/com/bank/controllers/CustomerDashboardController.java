package com.bank.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bank.bo.BankAccountViewBO;
import com.bank.bo.impl.BankAccountViewBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.BankAccount;
import com.bank.to.User;
import com.google.gson.Gson;

/**
 * Servlet implementation class UserDashboardController
 */
@WebServlet("/dashboard")
public class CustomerDashboardController extends HttpServlet {
	private static final Logger log = Logger.getLogger(CustomerDashboardController.class); 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerDashboardController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		if(session != null) {
			log.info("Redirecting User To Dashboard");
			response.sendRedirect(request.getContextPath() + "/customer-dashboard.html");
		}else {
			log.info("Redirecting User To Login");
			response.sendRedirect(request.getContextPath() + "/");
		}
	}

}
