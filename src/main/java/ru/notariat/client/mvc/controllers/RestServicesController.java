package ru.notariat.client.mvc.controllers;

import java.sql.Timestamp;
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
import ru.notariat.client.db.objects.Document;
import ru.notariat.client.db.objects.ReceptionTimestamp;
import ru.notariat.client.mvc.objects.TestMVC;
import ru.notariat.client.mvc.objects.UserMVC;

@Controller
public class RestServicesController {

	private static final Logger logger = LoggerFactory.getLogger(RestServicesController.class);

	
	@Autowired
	private DocumentsDB documentsDAO; 

	@Autowired
	private ReceptionTimestampsDB receptionTimestampDAO; 
	
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
	
	@RequestMapping(value = "/put-json-new-record", method = RequestMethod.POST, consumes = "application/json") 
	@ResponseBody
	public String putNewRecordJson(@RequestBody TestMVC testMVC){
		
		try {
			logger.info(testMVC.getName() + " " + testMVC.getLastName());
			return "test";
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
