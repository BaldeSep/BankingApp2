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
import com.bank.to.MoneyTransferJSONRequest;
import com.bank.to.User;
import com.bank.to.types.MoneyTransferStatus;
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
		HttpSession session = request.getSession(false);
		if(session != null) {
			Gson gson = new Gson();
			MoneyTransferJSONRequest transfer = gson.fromJson(request.getReader(), MoneyTransferJSONRequest.class);
			MoneyTransferBO transferBO = new MoneyTransferBOImpl();
			MoneyTransferStatus status = MoneyTransferStatus.fromInt(transfer.getStatus());
			int transferId = transfer.getTransferId();
			MoneyTransfer updatedTransfer = null;
			try {
				switch(status) {
				case Accepted:
					updatedTransfer = transferBO.acceptMoneyTransfer(transferId);
					break;
				case Rejected:
					updatedTransfer = transferBO.rejectMoneyTransfer(transferId);
					break;
				default:
					break;
				}
				if(updatedTransfer != null) {
					String jsonTransfer = gson.toJson(updatedTransfer);
					response.setContentType("application/json");
					response.setStatus(200);
					response.getWriter().print(jsonTransfer);
				}else {
					throw new BusinessException("Money Transfer Could Not Be Processed");
				}
			}catch(BusinessException e) {
				response.setStatus(500);
				response.getWriter().print(e.getMessage());
			}
		}
	}

}
