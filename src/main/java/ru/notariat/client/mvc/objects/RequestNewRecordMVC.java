package ru.notariat.client.mvc.objects;

import java.util.List;

public class RequestNewRecordMVC {
	
	private Integer[] documentsId;
	private int timeId;
	private String address;
	private String telephone;
	
	public Integer[] getDocumentsId() {
		return documentsId;
	}
	public void setDocumentsId(Integer[] documentsId) {
		this.documentsId = documentsId;
	}
	public int getTimeId() {
		return timeId;
	}
	public void setTimeId(int timeId) {
		this.timeId = timeId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	

}
