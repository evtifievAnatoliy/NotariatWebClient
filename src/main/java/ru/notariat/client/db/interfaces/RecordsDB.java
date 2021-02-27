package ru.notariat.client.db.interfaces;

import java.sql.Timestamp;
import java.util.List;

import ru.notariat.client.db.objects.Document;
import ru.notariat.client.db.objects.Notarius;
import ru.notariat.client.db.objects.User;
import ru.notariat.client.db.objects.enums.StatusOfRecord;
import ru.notariat.client.exeptions.MySQLExeptions;

public interface RecordsDB {
	
	public int insetNewRecord(User user, int timestampId, Notarius notarius, List<Integer> documentsIdList) throws MySQLExeptions; 
	public boolean changeRecordStaus(int id, String statusOfRecord) throws MySQLExeptions;
	boolean changeTimeStampOfRecord(int id, Timestamp newTimestamp) throws MySQLExeptions;

}
