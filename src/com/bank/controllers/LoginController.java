package com.bank.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.bo.UserBO;
import com.bank.bo.impl.UserBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.User;
import com.bank.to.types.UserType;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		
		UserBO userBO = new UserBOImpl();
		try {
			User validatedUser = userBO.validateUser(user);
			if(validatedUser != null) {
				validatedUser.setPassword("");
				HttpSession session = request.getSession();
				session.setAttribute("user", validatedUser);
				if(validatedUser.getUserType() == UserType.Customer) {					
					response.sendRedirect(request.getContextPath() +  "/dashboard");
				}else {
					response.sendRedirect(request.getContextPath() +  "/");
				}
			}else {
				response.sendRedirect(request.getContextPath() +  "/login-error.html");
			}
		}catch(BusinessException e) {
			response.sendRedirect(request.getContextPath() +  "/login-error.html");
		}
	}

}
