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
import com.bank.to.MessageResponse;
import com.bank.to.User;
import com.bank.to.types.UserType;
import com.google.gson.Gson;

/**
 * Servlet implementation class EmployeeLogin
 */
@WebServlet("/emp")
public class EmployeeLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect( request.getContextPath() + "/employee-login.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		User user = gson.fromJson(request.getReader(), User.class);
		UserBO userBO = new UserBOImpl();
		try {
			User validatedUser = userBO.validateUser(user);
			if(validatedUser != null && validatedUser.getUserType() == UserType.Employee) {
				HttpSession session = request.getSession();
				session.setAttribute("user", validatedUser);
				response.sendRedirect(request.getContextPath() + "/empdash");
			}else if( validatedUser != null && validatedUser.getUserType() != UserType.Employee ) {
				response.setStatus(401);
				String message = gson.toJson(new MessageResponse("Only Employees Can Login Here..."));
				out.print(message);
			}
			else {
				response.setStatus(401);
				String message = gson.toJson(new MessageResponse("Employee Could Not Be Found"));
				out.print(message);
			}
		}catch(BusinessException e) {
			response.setStatus(401);
			String message = gson.toJson(new MessageResponse(e.getMessage()));
			out.print(message);
		}
	}

}
