package com.jentrent.punchlist.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jentrent.punchlist.entity.Account;
import com.jentrent.punchlist.entity.Task;
import com.jentrent.punchlist.service.AccountException;
import com.jentrent.punchlist.service.AccountService;
import com.jentrent.punchlist.service.TaskException;
import com.jentrent.punchlist.service.SpringUtil;
import com.jentrent.punchlist.service.TaskService;

public class TaskServiceTest{

	private static AccountService accountService;
	private static TaskService taskService;
	private static List<Account> accountsToDelete;
	private static List<Task> tasksToDelete;

	@BeforeClass
	public static void setUp() throws AccountException{

		accountService = (AccountService) SpringUtil.getBean("accountService");
		taskService = (TaskService) SpringUtil.getBean("taskService");

		accountsToDelete = new ArrayList<Account>();
		tasksToDelete = new ArrayList<Task>();

	}

	@Test
	public void testCreateTaskWithValidData() throws AccountException, TaskException{

		Account account = createAccount();
		assertNotNull(createTask(account));
	}

	@Test
	public void testReadTask() throws AccountException, TaskException{

		Account account = createAccount();
		Task task = createTask(account);

		Task check = taskService.readTask(task.getTaskId());
		assertNotNull(check);
	}

	@Test
	public void testUpdateTask() throws AccountException, TaskException{

		Account account = createAccount();
		Task task = createTask(account);

		String newText = "Updated Task" + System.currentTimeMillis();
		int newPriority = 2;
		task.setText(newText);
		task.setPriority(newPriority);
		Task check = taskService.updateTask(task);
		assertTrue(check.getText().equals(newText));
		assertTrue(check.getPriority().equals(newPriority));
	}

	@Test
	public void testToggleCompletedTrue() throws AccountException, TaskException{

		Account account = createAccount();
		Task task = createTask(account);

		boolean isCompleted = true;
		task.setCompleted(isCompleted);
		Task check = taskService.updateTask(task);
		assertTrue(check.isCompleted());
	}

	@Test
	public void testToggleCompletedFalse() throws AccountException, TaskException{

		Account account = createAccount();
		Task task = createTask(account);

		boolean isCompleted = false;
		task.setCompleted(isCompleted);
		Task check = taskService.updateTask(task);
		assertTrue(!check.isCompleted());
	}

	@Test(expected = NoResultException.class)
	public void testDeleteTask() throws AccountException, TaskException{

		Account account = createAccount();
		Task task = createTask(account);

		Integer rowsUpdated = taskService.deleteTask(task.getTaskId());
		assertTrue(rowsUpdated.intValue() == 1);

		taskService.readTask(task.getTaskId());
	}

	@Test
	public void testListTasks() throws AccountException, TaskException{

		Account account = createAccount();

		List<Task> list = new ArrayList<Task>();
		Task task1 = createTask(account);
		Task task2 = createTask(account);
		Task task3 = createTask(account);
		list.add(task1);
		list.add(task2);
		list.add(task3);

		List<Task> check = taskService.listTasks(account.getAccountId());

		assertTrue(list.size() == check.size());

		for(Task l: list){

			boolean found = false;

			for(Task c: check){

				if(l.getAccountId().equals(c.getAccountId())){
					found = true;
				}

			}

			assertTrue(found);
		}

	}

	private Task createTask(Account account) throws TaskException{

		String seed = Long.toString(System.currentTimeMillis());
		Task task = new Task();
		task.setText("New Task" + seed);
		task.setPriority(3);
		task.setDue(new Date());
		task.setAlert(false);
		task.setCompleted(false);
		task.setCreated(new Date());
		task.setModified(new Date());
		task.setAccountId(account.getAccountId());

		tasksToDelete.add(task);

		return taskService.createTask(task);
	}

	private Account createAccount() throws AccountException{

		String seed = Long.toString(System.currentTimeMillis());
		Account account = new Account();
		account.setEmail(seed + "@test.com");
		account.setFirstName("firstName_" + seed);
		account.setLastName("lastName_" + seed);
		account.setPassword("pw_" + seed);

		accountsToDelete.add(account);

		return accountService.createAccount(account);
	}

	@AfterClass
	public static void tearDown(){

		try{

			for(Task t: tasksToDelete){
				taskService.deleteTask(t.getTaskId());
			}

			for(Account a: accountsToDelete){
				accountService.deleteAccount(a.getAccountId());
			}

		}catch(Exception e){
			throw new RuntimeException("Problem in tearDown(), not all accounts removed");
		}

	}

}
