package com.jps.otm.protection;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Validation {
	@Id
	private String username;
	private String password;
	private String person;
	public Validation() {
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUser_password() {
		return password;
	}
	public void setUser_password(String user_password) {
		this.password = user_password;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	
}
