package ru.notariat.client.db.objects;

public class Notarius {
	private int id;
	private String fio;
	private String address;
	private String license;
	
	public Notarius(int id, String fio) {
		super();
		this.id = id;
		this.fio = fio;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFio() {
		return fio;
	}
	public void setFio(String fio) {
		this.fio = fio;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	
	

}
