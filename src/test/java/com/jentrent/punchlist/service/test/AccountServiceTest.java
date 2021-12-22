package com.jentrent.punchlist.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.annotation.WebServlet;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jentrent.punchlist.entity.Account;
import com.jentrent.punchlist.service.AccountException;
import com.jentrent.punchlist.service.AccountService;
import com.jentrent.punchlist.service.SpringUtil;

public class AccountServiceTest{

	private static AccountService accountService;

	private static List<Account> accountsToDelete;

	@BeforeClass
	public static void setUp(){

		accountService = (AccountService) SpringUtil.getBean("accountService");

		accountsToDelete = new ArrayList<Account>();
	}

	@Test
	public void testCreateAccountWithValidData() throws AccountException{

		assertNotNull(createAccount());
	}

	@Test
	public void testCreateAccountWithInValidData(){

		Account notAllFields = new Account();

		try{
			accountService.createAccount(notAllFields);
		}catch(AccountException e){
			assertTrue(e.getMessage().equals("All fields are required"));
		}

	}

	@Test
	public void testValidateUserWithValidCredentials() throws AccountException{

		Account account = createAccount();
		Account check = accountService.validateUser(account.getEmail(), account.getPassword());
		assertNotNull(check.getAccountId());
	}

	@Test(expected = AccountException.class)
	public void testValidateUserWithInvalidCredentials() throws AccountException{

		Account account = createAccount();
		accountService.validateUser(account.getEmail(), System.currentTimeMillis() + "");
	}

	@Test
	public void testReadAccountInteger() throws AccountException{

		Account account = createAccount();

		Account check = accountService.readAccount(account.getAccountId());
		assertNotNull(check);
	}

	@Test
	public void testReadAccountString() throws AccountException{

		Account check = accountService.readAccount(createAccount().getEmail());
		assertNotNull(check);
	}

	@Test
	public void testUpdateAccount() throws AccountException{

		Account account = createAccount();
		String newEmail = System.currentTimeMillis() + "updated_email.com";
		account.setEmail(newEmail);
		Account check = accountService.updateAccount(account);
		assertTrue(check.getEmail().equals(newEmail));
	}

	@Test(expected = NoResultException.class)
	public void testDeleteAccount() throws AccountException{

		Account account = createAccount();

		Integer rowsUpdated = accountService.deleteAccount(account.getAccountId());
		assertTrue(rowsUpdated.intValue() == 1);

		accountService.readAccount(account.getAccountId());
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

			for(Account a: accountsToDelete){
				accountService.deleteAccount(a.getAccountId());
			}

		}catch(Exception e){
			throw new RuntimeException("Problem in tearDown(), not all accounts removed");
		}

	}

}
