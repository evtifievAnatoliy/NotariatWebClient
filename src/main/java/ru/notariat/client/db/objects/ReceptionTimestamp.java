package ru.notariat.client.db.objects;

import java.sql.Timestamp;

public class ReceptionTimestamp {

	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	private String name;
	private Timestamp receptionTimestamp;
	
	public ReceptionTimestamp() {
		super();
	}
	public ReceptionTimestamp(Timestamp receptionTimestamp) {
		super();
		this.name = setName(receptionTimestamp);
		this.receptionTimestamp = receptionTimestamp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getReceptionTimestamp() {
		return receptionTimestamp;
	}
	public void setReceptionTimestamp(Timestamp receptionTimestamp) {
		this.receptionTimestamp = receptionTimestamp;
	}
	
	private String setName(Timestamp timestamp) {
		if (timestamp.getHours()>9 && timestamp.getMinutes()>9) {
			return timestamp.getHours() + ":" + timestamp.getMinutes();
		}
		else if (timestamp.getHours()<=9 && timestamp.getMinutes()>9) {
			return "0" + timestamp.getHours() + ":" + timestamp.getMinutes();
		}
		else if (timestamp.getHours()>9 && timestamp.getMinutes()<=9) {
			return timestamp.getHours() + ":0" + timestamp.getMinutes();
		}
		else {
			return "0" + timestamp.getHours() + ":0" + timestamp.getMinutes();	
		}
		
	}
	
	
}
