package com.jentrent.punchlist.api;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jentrent.punchlist.service.SpringUtil;
import com.jentrent.punchlist.service.TaskException;
import com.jentrent.punchlist.service.TaskService;

@Path("/tasks")
public class TaskInterface{

	private TaskService taskService;

	public TaskInterface(){

		taskService = (TaskService) SpringUtil.getBean("taskService");
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response listTasks(@HeaderParam("authorization")
	String authHeader) throws TaskException{

		return null;
	}

//	public Task createTask(Task task) throws TaskException;
//
//	public Task readTask(Integer taskId);
//
//	public Task updateTask(Task task) throws TaskException;
//
//	public Task toggleCompleted(Task task);
//
//	public Integer deleteTask(Integer taskId);
//
//	public List<Task> listTasks(Integer accountId);

}
