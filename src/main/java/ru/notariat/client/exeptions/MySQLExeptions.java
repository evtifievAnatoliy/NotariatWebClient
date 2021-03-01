package ru.notariat.client.exeptions;

import java.sql.SQLException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.notariat.client.mvc.controllers.RestServicesController;

public class MySQLExeptions extends Exception{

	private static final Logger logger = LoggerFactory.getLogger(RestServicesController.class);
	
	public MySQLExeptions(String message) {
		super(message);
		logger.error("Ошибка SQLDAO методов. Error: " + message);
		
		// TODO Auto-generated constructor stub
	}

	
	

}
