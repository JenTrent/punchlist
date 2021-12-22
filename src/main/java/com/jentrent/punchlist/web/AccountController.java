package com.jentrent.punchlist.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jentrent.punchlist.entity.Account;
import com.jentrent.punchlist.service.AccountException;
import com.jentrent.punchlist.service.AccountService;
import com.jentrent.punchlist.service.SpringUtil;

public class AccountController extends HttpServlet{

	private AccountService accountService;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		String cmd = req.getParameter("cmd");
		String id = req.getParameter("id");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		Account account = null;

		if(cmd == null){
			sendToLogin(req, resp);
			return;
		}

		switch (cmd){

			case "attemptLogin":

				try{
					account = accountService.validateUser(email, password);
					req.getSession().setAttribute("account", account);
					sendToManageTasks(req, resp);
				}catch(AccountException e){
					req.setAttribute("error", "Invalid email or password.");
					sendToLogin(req, resp);
				}
				break;

			case "attemptLogout":

				req.getSession().removeAttribute("account");
				req.getSession().invalidate();
				sendToLogin(req, resp);
				break;

			case "createAccount":

				try{
					account = new Account();
					account.setEmail(email);
					account.setFirstName(firstName);
					account.setLastName(lastName);
					account.setPassword(password);
					account = accountService.createAccount(account);
					req.getSession().setAttribute("account", account);
					sendToManageTasks(req, resp);

				}catch(AccountException e){
					req.setAttribute("error", e.getMessage());
					sendToCreateAccount(req, resp);
				}

				break;

			case "updateAccount":
				account = (Account) req.getSession().getAttribute("account");

				try{
					account.setEmail(email);
					account.setFirstName(firstName);
					account.setLastName(lastName);
					account.setPassword(password);

					account = accountService.updateAccount(account);
					req.getSession().setAttribute("account", account);
					sendToManageTasks(req, resp);

				}catch(AccountException e){
					req.setAttribute("error", e.getMessage());
					sendToManageAccount(req, resp);
				}

				break;

			case "deleteAccount":
				accountService.deleteAccount(Integer.parseInt(id));
				break;

		}

	}

	private void sendToLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
		dispatcher.forward(req, resp);
	}

	private void sendToCreateAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/createAccount.jsp");
		dispatcher.forward(req, resp);
	}

	private void sendToManageAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/manageAccount.jsp");
		dispatcher.forward(req, resp);
	}

	private void sendToManageTasks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/TaskController");
		dispatcher.forward(req, resp);
	}

	public void init(ServletConfig config) throws ServletException{

		super.init(config);
		accountService = (AccountService) SpringUtil.getBean("accountService");
	}

}
