package ru.notariat.client.db.objects;

public class Document {
	
	private int id;
	private String name;
	private String record_type;
	
	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Document(String name, String record_type) {
		super();
		this.name = name;
		this.record_type = record_type;
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
	public String getRecord_type() {
		return record_type;
	}
	public void setRecord_type(String record_type) {
		this.record_type = record_type;
	}
	

}
