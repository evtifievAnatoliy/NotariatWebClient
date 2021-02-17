package ru.notariat.client.db.objects;

import java.sql.Timestamp;

public class User {
	
	private String userName;
	private String email;
	private String userPassword;
	private Timestamp createTime;
	private String fio;
	private String telephone;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String email, String fio, String telephone) {
		super();
		this.email = email;
		this.fio = fio;
		this.telephone = telephone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getFio() {
		return fio;
	}
	public void setFio(String fio) {
		this.fio = fio;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	

}
