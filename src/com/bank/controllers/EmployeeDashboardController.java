package com.bank.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bank.to.User;
import com.bank.to.types.UserType;

/**
 * Servlet implementation class EmployeeDashboardController
 */
@WebServlet("/empdash")
public class EmployeeDashboardController extends HttpServlet {
	private static final Logger log = Logger.getLogger(EmployeeDashboardController.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeDashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			User user = (User) session.getAttribute("user");
			if(user.getUserType() == UserType.Employee) {
				log.info("Redirecting Employee To Dashboard");
				response.sendRedirect(request.getContextPath() + "/employee-dashboard.html");
			}else {
				log.error("Invalid User Redirecting To Login");
				log.error("Ending User Session");
				session.invalidate();
				response.sendRedirect(request.getContextPath() + "/");
			}
		}else {
			log.info("No Valid Session Redirecting To Login");
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
