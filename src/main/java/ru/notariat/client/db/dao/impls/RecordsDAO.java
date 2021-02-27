package ru.notariat.client.db.dao.impls;

import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import ru.notariat.client.annotations.MyExeption;
import ru.notariat.client.db.interfaces.DocumentsDB;
import ru.notariat.client.db.interfaces.ReceptionTimestampsDB;
import ru.notariat.client.db.interfaces.RecordsDB;
import ru.notariat.client.db.interfaces.UsersDB;
import ru.notariat.client.db.objects.Document;
import ru.notariat.client.db.objects.Notarius;
import ru.notariat.client.db.objects.User;
import ru.notariat.client.db.objects.enums.StatusOfRecord;
import ru.notariat.client.exeptions.MySQLExeptions;

@Component("recordsDAO")
public class RecordsDAO implements RecordsDB{
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private UsersDB users;
	
	@Autowired
	private ReceptionTimestampsDB receptionTimestamps;
	
	@Autowired
	private DocumentsDB documents; 
	
	@Autowired
	public void setDataSourse(DataSource dataSource) {
		this.jdbcTemplate= new NamedParameterJdbcTemplate(dataSource);
	}
	
	@MyExeption
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,rollbackFor = Exception.class)
	@Override
	public int insetNewRecord(User user, int timestampId, Notarius notarius, List<Integer> documentsIdList) throws MySQLExeptions {
		try {
			
			System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
			MapSqlParameterSource params = new MapSqlParameterSource();
			
			int userId = users.getUserId(user);
			params.addValue("userId", userId);
			
			params.addValue("timestampId", timestampId);
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			params.addValue("notariusId", notarius.getId());
			params.addValue("recordStatus", StatusOfRecord.newRecord.name());
			String sql = "insert into notarius.records "
					+ "(records.notaries_id, records.reception_timestamps_id, records.users_id, records.status) "
					+ "values (:notariusId, :timestampId, :userId, :recordStatus)";
			jdbcTemplate.update(sql, params, keyHolder);
			int insertRecordId = keyHolder.getKey().intValue();
			params.addValue("insertRecordId", insertRecordId);
			
			for (Integer documentId: documentsIdList) {
				insertInRecords_has_documents(documentId, insertRecordId);
			}
			
			return insertRecordId;
		}
		catch (DataAccessException e) {
			throw new MySQLExeptions("Ошибка добавления Новой записи к таблице Records. Метод insetNewRecord. Error: " + e.getMessage());
		}
	}
	
	@MyExeption
	@Override
	public boolean changeRecordStaus(int id, String statusOfRecord) throws MySQLExeptions {
		try {
			String sql = "update notarius.records "
					+ "	set records.status = :status "
					+ "    where records.id = :recordId";
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("recordId", id);
			params.addValue("status", statusOfRecord);
			int sumChangedRecords = jdbcTemplate.update(sql, params);
			if (sumChangedRecords>0)
				return true;
			else
				return false;
			}
			catch (Exception e) {
				throw new MySQLExeptions("Ошибка изменения статуса записи к таблице Records. Метод changeRecordStaus. Error: " + e.getMessage());
			}
	}
	
	@MyExeption
	@Override
	public boolean changeTimeStampOfRecord(int id, Timestamp newTimestamp) throws MySQLExeptions {
		try {
			
			int timestampId = receptionTimestamps.getReceptionTimestampID(newTimestamp);
			
			String sql = "update notarius.records "
					+ "	set records.reception_timestamps_id = :timestampId "
					+ "    where records.id = :recordId";
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("recordId", id);
			params.addValue("timestampId", timestampId);
			int sumChangedRecords = jdbcTemplate.update(sql, params);
			if (sumChangedRecords>0)
				return true;
			else
				return false;
			}
			catch (Exception e) {
				throw new MySQLExeptions("Ошибка изменения времени записи к таблице Records. Метод changeTimeStampOfRecord. Error: " + e.getMessage());
			}
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertInRecords_has_documents (int docId, int recordId) {
		System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("insertRecordId", recordId);
		params.addValue("docId", docId);
		String sql = "insert into notarius.records_has_documents "
				+ "(records_has_documents.documents_id, records_has_documents.records_id)"
				+ "	value(:docId, :insertRecordId)";
		jdbcTemplate.update(sql, params);
	}
	
}
