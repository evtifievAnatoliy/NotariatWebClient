package ru.notariat.client.db.objects;

import java.sql.Timestamp;
import java.util.List;

import ru.notariat.client.db.objects.enums.StatusOfRecord;

public class Record {
	
	private int id;
	private Timestamp createTime;
	private Timestamp receptionTimestamp;
	private User user;
	private String addressOfObject;
	private List <Document> documentsList;
	private List <File> filesList; 
	private StatusOfRecord statusOfRecord;
	
	public Record(Timestamp createTime, Timestamp receptionTimestamp, User user, List<Document> documentsList, StatusOfRecord statusOfRecord) {
		super();
		this.createTime = createTime;
		this.receptionTimestamp = receptionTimestamp;
		this.user = user;
		this.documentsList = documentsList;
		this.statusOfRecord = statusOfRecord;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getReceptionTimestamp() {
		return receptionTimestamp;
	}
	public void setReceptionTimestamp(Timestamp receptionTimestamp) {
		this.receptionTimestamp = receptionTimestamp;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAddressOfObject() {
		return addressOfObject;
	}
	public void setAddressOfObject(String addressOfObject) {
		this.addressOfObject = addressOfObject;
	}
	public List<Document> getDocumentsList() {
		return documentsList;
	}
	public void setDocumentsList(List<Document> documentsList) {
		this.documentsList = documentsList;
	}
	public List<File> getFilesList() {
		return filesList;
	}
	public void setFilesList(List<File> filesList) {
		this.filesList = filesList;
	}
	public StatusOfRecord getStatusOfRecord() {
		return statusOfRecord;
	}
	public void setStatusOfRecord(StatusOfRecord statusOfRecord) {
		this.statusOfRecord = statusOfRecord;
	}
	
	

}
