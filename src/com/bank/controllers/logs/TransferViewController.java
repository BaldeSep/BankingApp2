package com.bank.controllers.logs;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bank.bo.TransactionLogViewBO;
import com.bank.bo.TransferLogViewBO;
import com.bank.bo.impl.TransactionLogViewBOImpl;
import com.bank.bo.impl.TransferLogViewBOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.MessageResponse;
import com.bank.to.TransactionLog;
import com.bank.to.TransferLog;
import com.bank.to.User;
import com.bank.to.types.UserType;
import com.google.gson.Gson;

/**
 * Servlet implementation class TransferViewController
 */
@WebServlet("/transferlogs")
public class TransferViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(TransferViewController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session != null) {
			User user = (User) session.getAttribute("user");
			if(user != null && user.getUserType() == UserType.Employee ) {
				response.setContentType("application/json");
				Gson gson = new Gson();
				TransferLogViewBO transBO = new TransferLogViewBOImpl();
				try {
					List<TransferLog> logs = transBO.getLogs();
					if(logs != null && !logs.isEmpty()) {
						String jsonLogs = gson.toJson(logs);
						log.info("Sending Logs For Transfers: " + jsonLogs);
						response.setStatus(200);
						response.getWriter().print(jsonLogs);
					}else{
						throw new BusinessException("No Valid Logs Found");
					}
				} catch (BusinessException e) {
					log.error(e);
					response.setStatus(500);
					String message = gson.toJson(new MessageResponse(e.getMessage()));
					response.getWriter().print(message);
				}
			}else {
				log.error("User Cannot View This Page");
				log.info("Redirecting To Loin Page");
				session.invalidate();
				response.sendRedirect(request.getContextPath() + "/");
			}
		}else {
			log.error("Invalid Session Redirecting To Login Page");
			response.sendRedirect( request.getContextPath() +  "/");
		}
	}

}
