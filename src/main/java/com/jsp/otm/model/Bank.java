package com.jsp.otm.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity  
public class Bank {
	@Id
	private int id;
	private String bank_name;
	private String city_name;
	private int no_of_account;
	private String bank_IFSC_code;
	private double min_balance;

	@OneToMany(mappedBy = "bank")
	private List<Account> account;

	public Bank() {

	}

	public int getNo_of_account() {
		return no_of_account;
	}

	public void setNo_of_account(int no_of_account) {
		this.no_of_account = no_of_account;
	}

	public String getBank_IFSC_code() {
		return bank_IFSC_code;
	}

	public void setBank_IFSC_code(String bank_IFSC_code) {
		this.bank_IFSC_code = bank_IFSC_code;
	}

	public double getMin_balance() {
		return min_balance;
	}

	public void setMin_balance(double min_balance) {
		this.min_balance = min_balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}
}
