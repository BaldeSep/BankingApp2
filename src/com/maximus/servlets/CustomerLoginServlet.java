package com.maximus.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerLoginServlet
 */
@WebServlet("/CustomerLogin")
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get User Name and Password From The Request Object
		String userName = request.getParameter("user-name");
		String password = request.getParameter("password");
		
		// Dummy Data For Testing
		String validUserName = "balde";
		String validPassword = "123abc";
		
		// For Printing Output For Testing
		PrintWriter out = response.getWriter();
		
		// Validate User Name and Password
		if(userName.equals(validUserName) && password.equals(validPassword)) {
			out.println("User " + userName + " is Valid..." );
		}else {
			out.println("User "+ userName + " is invalid...");
		}
		
		out.close();
	}

}