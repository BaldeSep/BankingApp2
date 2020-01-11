package com.bank.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.bo.UserBO;
import com.bank.bo.impl.UserBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.User;
import com.bank.to.UserRegistrationRequest;
import com.bank.to.types.UserType;
import com.google.gson.Gson;

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
		Gson gson = new Gson();
 		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		UserRegistrationRequest userReg = gson.fromJson(request.getReader(), UserRegistrationRequest.class);
		String userName = userReg.getUserName();
		String passwordOne = userReg.getPasswordOne();
		String passwordTwo = userReg.getPasswordTwo();
		User user = new User(userName, passwordOne, UserType.Customer);
		UserBO userBO = new UserBOImpl();
		try {
			User registeredUser = userBO.registerUser(user, passwordOne, passwordTwo);
			if(registeredUser != null) {
				response.setStatus(200);
				response.sendRedirect( request.getContextPath() + "/");
			}else {
				response.setStatus(401);
				out.print("There Was Issue Registering With That User Name And Password");
			}
		} catch (BusinessException e) {
			response.setStatus(401);
			out.print(e.getMessage());
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.sendRedirect(req.getContextPath() + "/register-customer.html");
	}
	
	

}
