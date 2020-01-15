package com.bank.controllers.transfers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.bo.MoneyTransferBO;
import com.bank.bo.impl.MoneyTransferBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.MoneyTransfer;
import com.bank.to.User;
import com.google.gson.Gson;

/**
 * Servlet implementation class MoneyTransferController
 */
@WebServlet("/transfers")
public class MoneyTransferController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoneyTransferController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			User user = (User) session.getAttribute("user");
			Gson gson = new Gson();
			MoneyTransfer transfer = gson.fromJson(request.getReader(), MoneyTransfer.class);
			transfer.setSourceUserName(user.getUserName());
			MoneyTransferBO transferBO = new MoneyTransferBOImpl();
			try {
				MoneyTransfer addedTransfer = transferBO.postMoneyTransfer(transfer);
				if(addedTransfer == null) {
					throw new BusinessException("Transfer Could Not Be Completed");
				}else {
					String transferJson = gson.toJson(addedTransfer);
					response.setStatus(200);
					response.setContentType("application/json");
					response.getWriter().print(transferJson);
				}
			} catch (BusinessException e) {
				response.setContentType("text/plain");
				response.setStatus(500);
				response.getWriter().print(e.getMessage());
			}
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}

}
