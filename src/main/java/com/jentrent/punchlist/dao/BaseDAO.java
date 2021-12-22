package com.jentrent.punchlist.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class BaseDAO{

	private EntityManager em;

	public BaseDAO(){

		em = Persistence.createEntityManagerFactory("punchlist_pu").createEntityManager();
	}

	protected EntityManager getEm(){

		return em;
	}

}
