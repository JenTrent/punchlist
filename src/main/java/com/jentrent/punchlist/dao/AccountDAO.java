package com.jentrent.punchlist.dao;

import java.util.List;

import com.jentrent.punchlist.entity.Account;

public interface AccountDAO{

	public Account createAccount(Account account);

	public Account readAccount(Integer accountId);

	public Account readAccount(String email);

	public Account updateAccount(Account account);

	public Integer deleteAccount(Integer accountId);

	public List<Account> listAccounts();

}
