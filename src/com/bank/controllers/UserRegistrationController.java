package com.bank.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.bo.UserBO;
import com.bank.bo.impl.UserBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.User;
import com.bank.to.types.UserType;

/**
 * Servlet implementation class UserRegistrationController
 */
@WebServlet("/register")
public class UserRegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistrationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password= request.getParameter("password-1");
		User user = new User(userName, password, UserType.Customer);
		UserBO userBO = new UserBOImpl();
		try {
			User registeredUser = userBO.registerUser(user);
			if(registeredUser != null) {
				response.sendRedirect( request.getContextPath() + "/");
			}else {
				response.sendRedirect( request.getContextPath() + "registration-error.html");
			}
		} catch (BusinessException e) {
			response.sendRedirect(request.getContextPath() + "registration-error.html");
		}
	}

}
