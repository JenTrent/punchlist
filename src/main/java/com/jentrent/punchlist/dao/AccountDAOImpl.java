package com.jentrent.punchlist.dao;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.jentrent.punchlist.entity.Account;

public class AccountDAOImpl extends BaseDAO implements AccountDAO{

	private static final Logger log = Logger.getLogger(AccountDAOImpl.class.getName());

	public AccountDAOImpl(){

	}

	public Account createAccount(Account account){

		account.setPassword(PasswordUtil.encrypt(account.getPassword()));
		Date d = new Date();
		account.setCreated(d);
		account.setModified(d);

		getEm().getTransaction().begin();
		getEm().persist(account);
		getEm().getTransaction().commit();

		return readAccount(account.getEmail());
	}

	public Account readAccount(Integer accountId){

		getEm().clear();

		Query q = getEm().createQuery("select a from Account a where a.accountId = :accountId");
		q.setParameter("accountId", accountId);

		Account account = (Account) q.getSingleResult();

		if(account != null){

			try{
				String pwClear = PasswordUtil.decrypt(account.getPassword());
				account.setPassword(pwClear);
			}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException(e);
			}

		}

		return account;
	}

	public Account readAccount(String email){

		getEm().clear();

		Query q = getEm().createQuery("select a from Account a where a.email = :email");
		q.setParameter("email", email);
		Account account = null;

		try{
			account = (Account) q.getSingleResult();
		}catch(NoResultException ignored){
		}

		if(account != null){

			try{
				account.setPassword(PasswordUtil.decrypt(account.getPassword()));
			}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException(e);
			}

		}

		return account;
	}

	public Account updateAccount(Account account){

		account.setPassword(PasswordUtil.encrypt(account.getPassword()));
		account.setModified(new Date());

		EntityManager em = getEm();
		em.getTransaction().begin();
		em.merge(account);
		em.flush();
		em.getTransaction().commit();

		return readAccount(account.getEmail());
	}

	public Integer deleteAccount(Integer accountId){

		getEm().getTransaction().begin();
		Query q = getEm().createQuery("delete from Account a where a.accountId = :accountId");
		q.setParameter("accountId", accountId);
		Integer rowsUpdated = q.executeUpdate();
		getEm().getTransaction().commit();

		return rowsUpdated;
	}

	public List<Account> listAccounts(){

		getEm().clear();

		Query q = getEm().createQuery("select a from Account a");

		return (List<Account>) q.getResultList();

	}

}
