package com.jentrent.punchlist.service;

import java.util.List;

import com.jentrent.punchlist.entity.Task;

public interface TaskService{

	public Task createTask(Task task) throws TaskException;

	public Task readTask(Integer taskId);

	public Task updateTask(Task task) throws TaskException;

	public Task toggleCompleted(Task task);

	public Integer deleteTask(Integer taskId);

	public List<Task> listTasks(Integer accountId);

}
