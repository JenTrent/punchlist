package com.jentrent.punchlist.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jentrent.punchlist.dao.AccountDAO;
import com.jentrent.punchlist.entity.Account;

public class AccountServiceImpl implements AccountService{

	private AccountDAO accountDAO;

	public Account validateUser(String email, String password) throws AccountException{

		Account account = readAccount(email);

		if(account != null && account.getPassword().equals(password)){
			return account;
		}else{
			throw new AccountException("User ID and/or password is incorrect");
		}

	}

	public Account createAccount(Account account) throws AccountException{

		checkAllFieldsArePresent(account);
		checkForDuplicateAccount(account);
		return accountDAO.createAccount(account);
	}

	public Account readAccount(Integer id){

		return accountDAO.readAccount(id);
	}

	public Account readAccount(String email){

		return accountDAO.readAccount(email);
	}

	public Account updateAccount(Account updatedAccount) throws AccountException{

		checkAllFieldsArePresent(updatedAccount);

		Account checkEmailAccount = readAccount(updatedAccount.getAccountId());

		updatedAccount.setCreated(checkEmailAccount.getCreated());

		if(updatedAccount.getEmail().equals(checkEmailAccount.getEmail())){
			return accountDAO.updateAccount(updatedAccount);
		}else{
			checkForDuplicateAccount(updatedAccount);
			return accountDAO.updateAccount(updatedAccount);
		}

	}

	public Integer deleteAccount(Integer id){

		return accountDAO.deleteAccount(id);
	}

	private void checkAllFieldsArePresent(Account account) throws AccountException{

		if(StringUtils.isEmpty(account.getFirstName()) || StringUtils.isEmpty(account.getLastName())
				|| StringUtils.isEmpty(account.getEmail()) || StringUtils.isEmpty(account.getPassword())){
			throw new AccountException("All fields are required");
		}

	}

	private void checkForDuplicateAccount(Account account) throws AccountException{

		Account check = accountDAO.readAccount(account.getEmail());

		if(check != null && !check.getAccountId().equals(account.getAccountId())){
			throw new AccountException("The following email is already associated with an existing account: " + account.getEmail());
		}

	}

	public void setAccountDAO(AccountDAO accountDAO){

		this.accountDAO = accountDAO;
	}

	public List<Account> listAccounts(){

		return accountDAO.listAccounts();
	}

}
