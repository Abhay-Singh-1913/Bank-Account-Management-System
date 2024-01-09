package com.jsp.otm.bankcontroller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.jsp.otm.model.Account;
import com.jsp.otm.model.Bank;

public class BankController {

	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pgbank");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	public boolean addBankAndAccounts(Bank bank, List<Account> accounts) {
		if (bank != null && accounts != null) {
			entityTransaction.begin();
			entityManager.persist(bank);
			for (Account account : accounts) {
				entityManager.persist(account);
			}
			entityTransaction.commit();
			return true;
		}
		return false;
	}
	public Bank findBank(int id) {

		return entityManager.find(Bank.class, id);
	}
	
	public boolean updateBank(int bank_id, String updatedName) {
		Bank bank = entityManager.find(Bank.class, bank_id);
		if (bank != null) {
			bank.setBank_name(updatedName);
			entityTransaction.begin();
			entityManager.merge(bank);
			entityTransaction.commit();
			return true;
		}
		return false;
	}
	public boolean removeBank(int bank_id) {
		Bank bank = entityManager.find(Bank.class, bank_id);
		if (bank != null) {
			List<Account> accounts = bank.getAccount();
			entityTransaction.begin();
			entityManager.remove(bank);
			for (Account account : accounts) {
				entityManager.remove(account);
			}
			entityTransaction.commit();
			return true;
		}
		return false;
	}
	
	public boolean removeAccounts(int bank_id, int accountIdToDelete) {
		Bank bank = entityManager.find(Bank.class, bank_id);
		if (bank != null) {
			List<Account> accounts = bank.getAccount();
			if (accounts == null) {
				//Accounts are not present  
				return false;
			}else{
				Account accountToRemove = null;
				for (Account account : accounts) {
					if (account.getId() == accountIdToDelete) {
						accountToRemove = account;
						break;
					}
				}
				if (accountToRemove != null) {
					entityTransaction.begin();
					accounts.remove(accountToRemove);
					entityTransaction.commit();

					entityTransaction.begin();
					entityManager.remove(accountToRemove);
					entityTransaction.commit();
					
					return true;
				} else {
					// Account id does not exit
					return false;
				}
			}
		}
		// bank not present 
		return false;
	}
	
	public List<Account> allAccount() {
		String jpql = "SELECT acc FROM Account acc";

		TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);

		return query.getResultList();
	}
	
}
