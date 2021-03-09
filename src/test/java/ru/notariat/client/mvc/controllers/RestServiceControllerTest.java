package ru.notariat.client.mvc.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.Module.SetupContext;

import ru.notariat.client.db.objects.Document;
import ru.notariat.client.db.objects.ReceptionTimestamp;
import ru.notariat.client.mvc.objects.RequestNewRecordMVC;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/appServlet/servlet-context.xml")
public class RestServiceControllerTest {
	
	@Autowired
	private RestServicesController restServicesController;  
	
	@Before
	public void SetupContext() {
//		restServicesController = new RestServicesController();
		
	}
	
	@Test
	public void restServicesControllerGetDocumentsListJsonNotNull() {
		Object o = restServicesController.getDocumentsListJson("Realty");
		
		Assert.assertNotNull(o);
	}
	
	@Test
	public void restServicesControllerGetDocumentsListJsonNull() {
		List<Document> o = restServicesController.getDocumentsListJson(null);
		
		Assert.assertEquals(o, new ArrayList<Document>());
	}
	
	@Test
	public void restServicesControllerGetTimeStampsListJsonNotNull() {
		
		Timestamp timestampFirst =  Timestamp.valueOf(java.time.LocalDate.of(2021, 3, 9).atStartOfDay());
		timestampFirst.setTime(timestampFirst.getTime()+60000*60*10);
		Timestamp timestampSecond = Timestamp.valueOf(java.time.LocalDate.of(2021, 3, 9).atStartOfDay());
		timestampSecond.setTime(timestampSecond.getTime()+60000*60*18);
		List<ReceptionTimestamp> o = restServicesController.getTimeStampsListJson(Long.toString(timestampFirst.getTime()), Long.toString(timestampSecond.getTime()));
		
		Assert.assertNotNull(o);
	}
	
	@Test
	public void restServicesControllerGetTimeStampsListJsonNull() {
		
		Timestamp timestampFirst =  Timestamp.valueOf(java.time.LocalDate.of(2021, 3, 9).atStartOfDay());
		timestampFirst.setTime(timestampFirst.getTime()+60000*60*10);
		Timestamp timestampSecond = Timestamp.valueOf(java.time.LocalDate.of(2021, 3, 9).atStartOfDay());
		timestampSecond.setTime(timestampSecond.getTime()+60000*60*18);
		List<ReceptionTimestamp> o = restServicesController.getTimeStampsListJson(Long.toString(timestampFirst.getTime()), Long.toString(timestampSecond.getTime()));
		
		Assert.assertNotNull(o);
	}
	
//	@Test
//	public void restServicesControllerputNewRecordJsonNotNull() {
//		
//		RequestNewRecordMVC requestNewRecordMVC = new RequestNewRecordMVC();
//		requestNewRecordMVC.setTimeId(16677);
//		Integer[] array  = new Integer[] {13, 15};
//		//requestNewRecordMVC.setDocumentsId(array);
//		requestNewRecordMVC.setTelephone("+79219910012");
//		//requestNewRecordMVC.setAddress("TestAddress");
//		
//		String o = restServicesController.putNewRecordJson(requestNewRecordMVC);
//		
//		Assert.assertEquals(o, "66");
//	}

	@Test
	public void restServicesControllerputNewRecordJsonNull() {
		
		RequestNewRecordMVC requestNewRecordMVC = new RequestNewRecordMVC();
		requestNewRecordMVC.setTimeId(16677);
		Integer[] array  = new Integer[] {13, 15};
		//requestNewRecordMVC.setDocumentsId(array);
		requestNewRecordMVC.setTelephone("+79219910012");
		//requestNewRecordMVC.setAddress("TestAddress");
		
		String o = restServicesController.putNewRecordJson(null);
		
		Assert.assertNotNull(o);
	}
	
	@Test
	public void restServicesControllerDeleteRecordNotNull() {
		
		String o = restServicesController.deleteRecord("64","+792199100012");
		
		Assert.assertNotNull(o);
	}
	
	@Test
	public void restServicesControllerDeleteRecordTrue() {
		
		String o = restServicesController.deleteRecord("64","79219910012");
		
		Assert.assertEquals(o, "Запись " + "64" + " успешно удалена.");;
	}
	
	@Test
	public void changeTimestampOfRecordNotNull() {
		
		String o = restServicesController.changeTimestampOfRecord("16678", "64","79219910012");
		
		Assert.assertNotNull(o);
	}
	
	@Test
	public void changeTimestampOfRecord() {
		Timestamp timestampFirst =  Timestamp.valueOf(java.time.LocalDate.of(2021, 3, 9).atStartOfDay());
		timestampFirst.setTime(timestampFirst.getTime()+60000*60*11);
		String o = restServicesController.changeTimestampOfRecord("64","79219910012", Long.toString(timestampFirst.getTime()));
		
		Assert.assertEquals(o, "Запись " + "64" + " успешно изменена.");
	}
	
}
