package com.bank.controllers.transfers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.bo.MoneyTransferBO;
import com.bank.bo.impl.MoneyTransferBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.MessageResponse;
import com.bank.to.MoneyTransfer;
import com.bank.to.MoneyTransferJSONRequest;
import com.bank.to.MoneyTransferJSONResponse;
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
		HttpSession session = request.getSession();
		if(session != null) {
			User user = (User) session.getAttribute("user");
			Gson gson = new Gson();
			MoneyTransferBO transferBO = new MoneyTransferBOImpl();
			List<MoneyTransferJSONResponse> transfers = null;
			try {
				transfers = transferBO.getMoneyTransfers(user);
				if(transfers != null) {
					String jsonTransfers = gson.toJson(transfers);
					response.setContentType("application/json");
					response.setStatus(200);
					response.getWriter().print(jsonTransfers);
				}else {
					throw new BusinessException("The MoneyTransfers Could Not Be Retrieved");
				}
			} catch (BusinessException e) {
				response.setContentType("application/json");
				String message = gson.toJson(new MessageResponse(e.getMessage()));
				response.setStatus(500);
				response.getWriter().print(message);
			}
		}
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
					String message = gson.toJson(new MessageResponse("Transfer Completed"));
					response.setStatus(200);
					response.setContentType("application/json");
					response.getWriter().print(message);
				}
			} catch (BusinessException e) {
				String message = gson.toJson(new MessageResponse(e.getMessage()));
				response.setContentType("application/json");
				response.setStatus(500);
				response.getWriter().print(message);
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
