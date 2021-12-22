package com.jentrent.punchlist.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.jentrent.punchlist.entity.Task;

public class TaskDAOImpl extends BaseDAO implements TaskDAO{

	public TaskDAOImpl(){

	}

	public Task createTask(Task task){

		Date d = new Date();
		task.setCreated(d);
		task.setModified(d);

		getEm().getTransaction().begin();
		getEm().persist(task);
		getEm().getTransaction().commit();

		return task;
	}

	public Task readTask(Integer taskId){

		Query q = getEm().createQuery("select t from Task t where t.taskId = :taskId");
		q.setParameter("taskId", taskId.intValue());

		return (Task) q.getSingleResult();
	}

	public Task readTask(Integer accountId, String text, Integer priority){

		Query q = getEm().createQuery("select t from Task t where t.accountId = :accountId and t.text = :text and t.priority = :priority");
		q.setParameter("accountId", accountId);
		q.setParameter("text", text);
		q.setParameter("priority", priority.intValue());

		try{
			return (Task) q.getSingleResult();
		}catch(NoResultException ignored){
			return null;
		}

	}

	public Task updateTask(Task task){

		task.setModified(new Date());
		getEm().getTransaction().begin();
		getEm().merge(task);
		getEm().getTransaction().commit();
		return readTask(task.getTaskId());
	}

	public Task toggleCompleted(Task task){

		task.setModified(new Date());
		getEm().getTransaction().begin();
		getEm().merge(task);
		getEm().getTransaction().commit();
		return readTask(task.getTaskId());
	}

	public Integer deleteTask(Integer taskId){

		getEm().getTransaction().begin();
		Query q = getEm().createQuery("delete from Task t where t.taskId = :taskId");
		q.setParameter("taskId", taskId.intValue());
		Integer rowsUpdated = q.executeUpdate();
		getEm().getTransaction().commit();
		return rowsUpdated;
	}

	public List<Task> listTasks(Integer accountId){

		getEm().clear();

		TypedQuery<Task> q = getEm().createQuery("select t from Task t where t.accountId = :accountId order by completed, priority, text",
				Task.class);
		q.setParameter("accountId", accountId);
		List<Task> res = q.getResultList();
		return res;
	}

}
