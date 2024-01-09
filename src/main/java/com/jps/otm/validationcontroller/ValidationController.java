package com.jps.otm.validationcontroller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jps.otm.protection.Validation;


public class ValidationController {

	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pgbank");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	public boolean signIn(Validation validation) {
		if (validation != null) {
			entityTransaction.begin();
			entityManager.persist(validation);
			entityTransaction.commit();
			return true;
		}
		return false;
	}
	
	public Validation findUser(String username , String password) {
		if (username != null && password != null){
			Validation findPerson = entityManager.find(Validation.class, username);

			if (username.equalsIgnoreCase(findPerson.getUsername())  && password.equalsIgnoreCase(findPerson.getUser_password())) {
				return findPerson;
			}
			
		}
		return null;
	}
}
