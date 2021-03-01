package ru.notariat.client.mvc.controllers;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.notariat.client.db.interfaces.DocumentsDB;
import ru.notariat.client.db.interfaces.ReceptionTimestampsDB;
import ru.notariat.client.db.interfaces.RecordsDB;
import ru.notariat.client.db.interfaces.UsersDB;
import ru.notariat.client.db.objects.Document;
import ru.notariat.client.db.objects.Notarius;
import ru.notariat.client.db.objects.ReceptionTimestamp;
import ru.notariat.client.db.objects.User;
import ru.notariat.client.db.objects.enums.StatusOfRecord;
import ru.notariat.client.mvc.objects.RequestNewRecordMVC;
import ru.notariat.client.mvc.objects.UserMVC;

@Controller
public class RestServicesController {

	private static final Logger logger = LoggerFactory.getLogger(RestServicesController.class);

	
	
	@Autowired
	private DocumentsDB documentsDAO; 

	@Autowired
	private ReceptionTimestampsDB receptionTimestampDAO; 
	
	@Autowired
	private RecordsDB recordsDAO; 
	
	@Autowired
	private UsersDB usersDAO; 
	
	@RequestMapping(value = "/get-json-documentsList", method = RequestMethod.GET, produces = "application/json; charset=UTF-8") 
	@ResponseBody
	public List<Document> getDocumentsListJson(@RequestParam("documentType") String type){
		
		try {
			return documentsDAO.getDocumentListByType(type);
		}
		catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value = "/get-json-timeStampsList", method = RequestMethod.GET, produces = "application/json; charset=UTF-8") 
	@ResponseBody
	public List<ReceptionTimestamp> getTimeStampsListJson(@RequestParam("firstStamp") String firstStamp, @RequestParam("secondStamp") String secondStamp){
		
		try {
			Timestamp firstTimestamp = new Timestamp(Long.parseLong(firstStamp));
			Timestamp secondTimestamp = new Timestamp(Long.parseLong(secondStamp));
			return receptionTimestampDAO.getListReseptionTimestamps(firstTimestamp, secondTimestamp);
		}
		catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value = "/put-json-new-record", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8", produces = "text/plain; charset=UTF-8") 
	@ResponseBody
	public String putNewRecordJson(@RequestBody RequestNewRecordMVC newRecordMVC){
		try {
			logger.info("Запрос на добавление записи: " +newRecordMVC.getTelephone() + " " +Arrays.toString(newRecordMVC.getDocumentsId()) + " " + newRecordMVC.getTimeId() + " " + newRecordMVC.getAddress());
			if(newRecordMVC.getDocumentsId()==null ||
					newRecordMVC.getTimeId()==0 ||
					newRecordMVC.getTelephone() == null) {
				return "Ошибка обработки запроса, не хватает входных данных.";
			}
			return String.valueOf(recordsDAO.insetNewRecord(new User("client", newRecordMVC.getTelephone()), 
					newRecordMVC.getTimeId(), 
					new Notarius(1, "TestNotarius"), Arrays.asList(newRecordMVC.getDocumentsId())));
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "/delete-record", method = RequestMethod.DELETE, produces = "text/plain; charset=UTF-8") 
	@ResponseBody
	public String deleteRecord(@RequestParam("recordId") String recordId, @RequestParam("userTelephone") String userTelephone){
		
		try {
			logger.info("Запрос на удаление записи: " + recordId + " " + userTelephone);
			if (recordId == "") {
				return "Ведите номер записи"; 
			}
			if (userTelephone == "") {
				return "Ведите номер телефона"; 
			}
			try {
				int recordIdInt = Integer.parseInt(recordId);
			}
			catch (Exception e) {
				return "Номер записи должен содержать только цифры"; 
			}
			if(!usersDAO.checkUserByTelephoneAndRecordId(userTelephone, Integer.parseInt(recordId))) {
				return "Номер записи не соответствует номеру телефона.";
			}
			recordsDAO.changeRecordStaus(Integer.parseInt(recordId), StatusOfRecord.canceledRecord.name());
			return "Запись " + recordId + " успешно удалена."; 
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "/change-timestamp-record", method = RequestMethod.POST, produces = "text/plain; charset=UTF-8") 
	@ResponseBody
	public String changeTimestampOfRecord(@RequestParam("recordId") String recordId, @RequestParam("userTelephone") String userTelephone, @RequestParam("timestamp") String timestamp){
		
		try {
			logger.info("Запрос на удаление записи: " + recordId + " " + userTelephone);
			if (recordId == "") {
				return "Ведите номер записи"; 
			}
			if (userTelephone == "") {
				return "Ведите номер телефона"; 
			}
			try {
				int recordIdInt = Integer.parseInt(recordId);
			}
			catch (Exception e) {
				return "Номер записи должен содержать только цифры"; 
			}
			if(!usersDAO.checkUserByTelephoneAndRecordId(userTelephone, Integer.parseInt(recordId))) {
				return "Номер записи не соответствует номеру телефона.";
			}
			
			Timestamp newTimestamp = new Timestamp(Long.parseLong(timestamp));
			
			recordsDAO.changeTimeStampOfRecord(Integer.parseInt(recordId), newTimestamp);
			return "Запись " + recordId + " успешно удалена."; 
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
