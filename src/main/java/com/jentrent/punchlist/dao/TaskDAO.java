package com.jentrent.punchlist.dao;

import java.util.List;

import com.jentrent.punchlist.entity.Task;

public interface TaskDAO{

	Task createTask(Task task);

	Task readTask(Integer taskId);

	Task readTask(Integer accountId, String text, Integer priority);

	Task updateTask(Task task);

	Task toggleCompleted(Task task);

	Integer deleteTask(Integer taskId);

	List<Task> listTasks(Integer sessionId);

}
