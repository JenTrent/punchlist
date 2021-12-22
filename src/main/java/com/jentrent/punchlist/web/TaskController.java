package com.jentrent.punchlist.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jentrent.punchlist.entity.Account;
import com.jentrent.punchlist.entity.Task;
import com.jentrent.punchlist.service.SpringUtil;
import com.jentrent.punchlist.service.TaskException;
import com.jentrent.punchlist.service.TaskService;

public class TaskController extends HttpServlet{

	private TaskService taskService;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		String cmd = req.getParameter("cmd");
		String taskId = req.getParameter("taskId");
		String text = req.getParameter("text");
		String priority = req.getParameter("priority");
		Boolean completed = Boolean.valueOf(req.getParameter("completed"));

		Account account = new Account();
		account = (Account) req.getSession().getAttribute("account");
		String accountId = account.getAccountId() + "";

		Task task = null;

		if(cmd == null){
			cmd = "listTask";
		}

		switch (cmd){

			case "listTasks":

				break;

			case "createTask":

				task = new Task();
				task.setAccountId(Integer.parseInt(accountId));
				task.setText(text);
				task.setPriority(Integer.parseInt(priority));
				task.setCompleted(completed);
				task.setDue(new Date());

				try{
					taskService.createTask(task);
				}catch(TaskException e){
					req.setAttribute("error", e.getMessage());
				}

				break;

			case "editTask":

				req.setAttribute("editedTaskID", taskId);

				break;

			case "updateTask":

				task = taskService.readTask(Integer.parseInt(taskId));

				task.setText(text);
				task.setPriority(Integer.parseInt(priority));
				task.setCompleted(completed);

				try{
					taskService.updateTask(task);
				}catch(TaskException e){
					req.setAttribute("error", e.getMessage());
				}

				break;

			case "toggleCompleted":

				task = taskService.readTask(Integer.parseInt(taskId));
				task.setCompleted(!completed);
				task = taskService.toggleCompleted(task);

				break;

			case "deleteTask":

				taskService.deleteTask(Integer.parseInt(taskId));

				break;
		}

		List<Task> tasks = taskService.listTasks(Integer.parseInt(accountId));

		req.setAttribute("tasks", tasks);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/manageTasks.jsp");
		dispatcher.forward(req, resp);

	}

	public void init(ServletConfig config) throws ServletException{

		super.init(config);
		taskService = (TaskService) SpringUtil.getBean("taskService");
	}

}
