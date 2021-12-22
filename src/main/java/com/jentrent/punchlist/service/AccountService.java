package com.jentrent.punchlist.service;

import java.util.List;

import com.jentrent.punchlist.entity.Account;

public interface AccountService{

	public Account validateUser(String email, String password) throws AccountException;

	public Account createAccount(Account account) throws AccountException;

	public Account readAccount(Integer id);

	public Account readAccount(String email);

	public Account updateAccount(Account updatedAccount) throws AccountException;

	public Integer deleteAccount(Integer id);

	public List<Account> listAccounts();

}
