package ru.notariat.client.db.dao.impls;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import ru.notariat.client.annotations.MyExeption;
import ru.notariat.client.db.interfaces.UsersDB;
import ru.notariat.client.db.objects.Document;
import ru.notariat.client.db.objects.User;
import ru.notariat.client.db.objects.enums.StatusOfRecord;
import ru.notariat.client.exeptions.MySQLExeptions;

@Component("usersDAO")
public class UsersDAO implements UsersDB{

	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSourse(DataSource dataSource) {
		this.jdbcTemplate= new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public int getUserId(User user) {
		String sql = "select users.id "
				+ "	from notarius.users users "
				+ "		where upper(users.telephone) = upper(:userTelephone) limit 1";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userEmail", user.getEmail());
		params.addValue("userFio", user.getFio());
		params.addValue("userTelephone", user.getTelephone().replaceAll("[-() ]", ""));
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int userId;
		try {
			userId = jdbcTemplate.queryForObject(sql, 
				params, Integer.class);
		}
		catch (Exception e) {
			sql = "insert into notarius.users "
					+ "(users.email, users.fio, users.telephone, users.user_groups_id) "
					+ "value (:userEmail,:userFio, :userTelephone,'1')";
			jdbcTemplate.update(sql, params, keyHolder);
			userId = keyHolder.getKey().intValue();
		}
		return userId;
	}
	
	@MyExeption
	@Override
	public boolean checkUserByTelephoneAndRecordId(String userTelephone, int recordId) throws MySQLExeptions {
		try {
			
			String correctUserTelephoneString = "+" + userTelephone.replaceAll("[-() ]", "");
			String sql = "select users.id "
					+ "	from notarius.users users "
					+ "inner join notarius.records records on users.id = records.users_id"
					+ "		where upper(users.telephone) = upper(:userTelephone) "
					+ "and records.id = :recordId limit 1";
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("userTelephone", correctUserTelephoneString);
			params.addValue("recordId", recordId);
			try {
				int userIdFromDB = jdbcTemplate.queryForObject(sql, 
					params, Integer.class);
				return true;
			}
			catch (Exception e) {
				return false;
			}
		}
		catch (DataAccessException e) {
			throw new MySQLExeptions("Ошибка проверки пользователя в таблице Users (метод checkUserByTelephone). Error: " + e.getMessage());
		}
		
	}

	
	@MyExeption
	@Override
	public boolean checkUserByEmailAndPass(User user) throws MySQLExeptions {
		try {
			
			String sql = "select users.password "
					+ "	from notarius.users users "
					+ "		where upper(users.email) = upper(:userEmail) limit 1";
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("userEmail", user.getEmail());
			String userPasswordFromDB = jdbcTemplate.queryForObject(sql, 
					params, String.class);
			
			if (userPasswordFromDB.equals(user.getUserPassword()))
				return true;
			
			return false;
		}
		catch (DataAccessException e) {
			throw new MySQLExeptions("Ошибка проверки пользователя в таблице Users (метод checkUserByNameAndPass). Error: " + e.getMessage());
		}
		
	}

	@MyExeption
	@Override
	public boolean chekUserGroup(User user, String groupType) throws MySQLExeptions {
		try {
			
			String sql = "select gr.type from user_groups gr"
					+ "	inner join users u on gr.id = u.user_groups_id"
					+ "    where u.email = :userEmail"
					+ " limit 1";
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("userEmail", user.getEmail());
			String userGroupFromDB = jdbcTemplate.queryForObject(sql, 
					params, String.class);
			
			if (userGroupFromDB.equals(groupType))
				return true;
			
			return false;
		}
		catch (DataAccessException e) {
			throw new MySQLExeptions("Ошибка проверки пользователя в таблице user_groups (метод chekUserGroup). Error: " + e.getMessage());
		}
		
	}

}
