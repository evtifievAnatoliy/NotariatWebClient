package ru.notariat.client.db.dao.impls;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import ru.notariat.client.annotations.MyExeption;
import ru.notariat.client.db.interfaces.DocumentsDB;
import ru.notariat.client.db.objects.Document;
import ru.notariat.client.exeptions.MySQLExeptions;


@Component("documentsDAO")
public class DocumentsDAO implements DocumentsDB{

	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSourse(DataSource dataSource) {
		this.jdbcTemplate= new NamedParameterJdbcTemplate(dataSource);
	}
	
	@MyExeption
	@Override
	public void insertDocument(Document document) throws MySQLExeptions{
		try {
			String sql = "select record.id"
					+ "	from notarius.record_types record"
					+ "        where record.name = :recordName limit 1";
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("recordName", document.getRecord_type());
			params.addValue("docName", document.getName());
			int id = jdbcTemplate.queryForObject(sql, 
					params, Integer.class);
			params.addValue("recordID", id);
			sql = "insert into notarius.documents (name, record_types_id) "
					+ "values (:docName, :recordID)";
			jdbcTemplate.update(sql, params);
		}
		catch (Exception e) {
			throw new MySQLExeptions("Ошибка добавления Нового документа к таблице Документы. Error: " + e.getMessage());
		}
	}
	
	@MyExeption
	@Override
	public int deleteDocument(Document document) throws MySQLExeptions{
		try {
			String sql = "delete notarius.documents"
					+ "	from notarius.documents"
					+ "	inner join notarius.record_types"
					+ "		on notarius.documents.record_types_id = notarius.record_types.id"
					+ "	where notarius.documents.name = :docName and notarius.record_types.name = :recordName";
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("recordName", document.getRecord_type());
			params.addValue("docName", document.getName());
			int result = jdbcTemplate.update(sql, params);
			return result;
		}
		catch (Exception e) {
			throw new MySQLExeptions("Ошибка удаления документа в таблице Документы. Error: " + e.getMessage());
		}
	}
	
	@MyExeption
	@Override
	public List<Document> getDocumentListByType(String type) throws MySQLExeptions {
		try {
			String sql = "select doc.name as documentName, r_types.name as typeName from"
					+ "		notarius.documents doc"
					+ "	    inner join notarius.record_types r_types"
					+ "			on doc.record_types_id = r_types.id"
					+ "		where r_types.name = :recordType";
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("recordType", type);
			return jdbcTemplate.query(sql, params, new DocumentRowMapper());
		}
		catch (Exception e) {
			throw new MySQLExeptions("Ошибка получения списка из таблицы Документы. Error: " + e.getMessage());
		}
	}
	

	private static final class DocumentRowMapper implements RowMapper<Document>{
			
		public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
			Document document = new Document();
			document.setName(rs.getString("documentName"));
			document.setRecord_type(rs.getString("typeName"));
			return document;
		}
		
	}


	@Override
	public int getDocId(Document document) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		String sql = "select doc.id from notarius.documents doc"
				+ "	where doc.name = :docName";
		params.addValue("docName", document.getName());
		int docId = jdbcTemplate.queryForObject(sql, 
				params, Integer.class);
		return docId;
	}

}
