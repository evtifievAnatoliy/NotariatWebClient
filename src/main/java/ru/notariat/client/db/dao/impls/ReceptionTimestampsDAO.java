package ru.notariat.client.db.dao.impls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import ru.notariat.client.annotations.MyExeption;
import ru.notariat.client.db.interfaces.ReceptionTimestampsDB;
import ru.notariat.client.db.objects.Document;
import ru.notariat.client.db.objects.ReceptionTimestamp;
import ru.notariat.client.exeptions.MySQLExeptions;

@Component("receptionTimestampsDAO")
public class ReceptionTimestampsDAO implements ReceptionTimestampsDB{
	
private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSourse(DataSource dataSource) {
		this.jdbcTemplate= new NamedParameterJdbcTemplate(dataSource);
	}
	
	@MyExeption
	@Override
	public int insertListReseptionTimestamps(List<ReceptionTimestamp> listReceptionTimestamps) throws MySQLExeptions {
		try {
			String sql = "insert into notarius.reception_timestamps (name, reception_timestamp)"
					+ "	value (:name, :receptionTimestamp)";
			SqlParameterSource[] params = new SqlParameterSource[listReceptionTimestamps.size()];
			int i = 0;
			for (ReceptionTimestamp item: listReceptionTimestamps) {
				MapSqlParameterSource p = new MapSqlParameterSource();
				p.addValue("name", item.getName());
				p.addValue("receptionTimestamp", item.getReceptionTimestamp());
				params[i]=p;
				i++;
			}
			int[] updateCounts = jdbcTemplate.batchUpdate(sql, params);
			return updateCounts.length;
		}
		catch (Exception e) {
			throw new MySQLExeptions("Ошибка добавления списка точек времени к таблице reception_timestamps. Error: " + e.getMessage());
		}
	}
	

	@MyExeption
	@Override
	public List<ReceptionTimestamp> getListReseptionTimestamps(Timestamp firstStamp, Timestamp secondStamp) throws MySQLExeptions {
		try {
			String sql = "select timeStamps.name as timeStampName, timeStamps.reception_timestamp as receptionTimestamp"
						+ " from notarius.reception_timestamps timeStamps"
						+ "		where timeStamps.reception_timestamp"
						+ "			between str_to_date(:firstStamp, '%Y-%m-%d %H:%i:%s') \r\n"
						+ "        		AND str_to_date(:secondStamp, '%Y-%m-%d %H:%i:%s')";
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("firstStamp", firstStamp);
			params.addValue("secondStamp", secondStamp);
			return jdbcTemplate.query(sql, params, new ReceptionTimestampsRowMapper());
		}
		catch (Exception e) {
			throw new MySQLExeptions("Ошибка получения списка из таблицы reception_timestampsы. Error: " + e.getMessage());
		}
	}
	
	private static final class ReceptionTimestampsRowMapper implements RowMapper<ReceptionTimestamp>{
		
		public ReceptionTimestamp mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReceptionTimestamp receptionTimestamp = new ReceptionTimestamp();
			receptionTimestamp.setName(rs.getString("timeStampName"));
			receptionTimestamp.setReceptionTimestamp(rs.getTimestamp("receptionTimestamp"));
			return receptionTimestamp;
		}
		
	}

	@Override
	public int getReceptionTimestampID(Timestamp timestamp) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("recordTimestamp", timestamp);
		
		String sql = "select timeStamps.id from notarius.reception_timestamps timeStamps"
				+ "	where timeStamps.reception_timestamp = str_to_date(:recordTimestamp, '%Y-%m-%d %H:%i:%s')";
		int timestampId = jdbcTemplate.queryForObject(sql, 
				params, Integer.class);
		
		return timestampId;
	}
}
