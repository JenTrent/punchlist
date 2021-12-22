package com.jentrent.punchlist.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jentrent.punchlist.dao.TaskDAO;
import com.jentrent.punchlist.entity.Task;

public class TaskServiceImpl implements TaskService{

	private TaskDAO taskDAO;

	public Task createTask(Task task) throws TaskException{

		if(StringUtils.isEmpty(task.getText())){

			throw new TaskException("Task text is required");
		}

		checkForDuplicateTask(task);
		return taskDAO.createTask(task);
	}

	public Task readTask(Integer taskId){

		return taskDAO.readTask(taskId);
	}

	public Task updateTask(Task task) throws TaskException{

		checkForDuplicateTask(task);
		return taskDAO.updateTask(task);
	}

	public Task toggleCompleted(Task task){

		return taskDAO.toggleCompleted(task);
	}

	public Integer deleteTask(Integer taskId){

		return taskDAO.deleteTask(taskId);
	}

	public List<Task> listTasks(Integer accountId){

		return taskDAO.listTasks(accountId);
	}

	private void checkForDuplicateTask(Task task) throws TaskException{

		Task check = taskDAO.readTask(task.getAccountId(), task.getText(), task.getPriority());

		if(check != null){

			if(task.getTaskId() != check.getTaskId()){
				throw new TaskException(
						"The following task is already present: " + task.getText() + ", " + task.getPriorityString() + " priority.");
			}

		}

	}

	public void setTaskDAO(TaskDAO taskDAO){

		this.taskDAO = taskDAO;
	}

}
