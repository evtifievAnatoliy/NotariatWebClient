package ru.notariat.client.db.interfaces;

import java.sql.Timestamp;
import java.util.List;

import ru.notariat.client.db.objects.ReceptionTimestamp;
import ru.notariat.client.exeptions.MySQLExeptions;

public interface ReceptionTimestampsDB {
	
	public int insertListReseptionTimestamps(List<ReceptionTimestamp> listReceptionTimestamps) throws MySQLExeptions;
	public List<ReceptionTimestamp> getListReseptionTimestamps(Timestamp firstStamp, Timestamp secondStamp) throws MySQLExeptions;
	public int getReceptionTimestampID(Timestamp timestamp);

}
