package com.jsp.otm.test;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Insert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pgengine");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Are you a Banker Y/N");
		String answer = scanner.nextLine();
		
		String persontype =null;
		if (answer.equalsIgnoreCase("Y")) {
			persontype = "banker";
		} else if(answer.equalsIgnoreCase("N")) {
			persontype = "user";
		} else {
			System.out.println("Only use Y/N");
		}
		System.out.println(persontype);
	}

}
