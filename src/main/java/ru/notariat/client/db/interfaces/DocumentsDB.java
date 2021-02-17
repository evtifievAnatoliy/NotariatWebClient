package ru.notariat.client.db.interfaces;

import java.util.List;

import ru.notariat.client.db.objects.Document;
import ru.notariat.client.exeptions.MySQLExeptions;

public interface DocumentsDB {

	public void insertDocument(Document document) throws MySQLExeptions;
	public int deleteDocument(Document document) throws MySQLExeptions;
	public List<Document> getDocumentListByType(String type) throws MySQLExeptions;
	public int getDocId(Document document);
}
