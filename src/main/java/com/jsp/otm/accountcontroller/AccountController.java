package com.jsp.otm.accountcontroller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.jsp.otm.model.Account;
import com.jsp.otm.model.Bank;

public class AccountController {

	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pgbank");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	
	public Account findAccount(int id) {

		return entityManager.find(Account.class, id);
	}

	public List<Account> allAccount() {
		String jpql = "SELECT acc FROM Account acc";

		TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);

		return query.getResultList();
	}
	
	public boolean updateAccount(int account_id, double updated_balance) {
		Account account = entityManager.find(Account.class, account_id);
		if (account != null) {
			account.setBalance(updated_balance);
			entityTransaction.begin();
			entityManager.merge(account);
			entityTransaction.commit();
			return true;
		}
		return false;
	}
}
