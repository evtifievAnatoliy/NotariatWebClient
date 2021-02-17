package ru.notariat.client.db.objects;

import java.sql.Timestamp;

public class File {
	private int id;
	private String name;
	private String context;
	private Timestamp timestamp;
	
	
	public File(String name, String context, Timestamp timestamp) {
		super();
		this.name = name;
		this.context = context;
		this.timestamp = timestamp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
