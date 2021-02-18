package ru.notariat.client.mvc.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import ru.notariat.client.db.interfaces.DocumentsDB;
import ru.notariat.client.db.objects.User;
import ru.notariat.client.mvc.objects.UserMVC;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main(HttpSession session) {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
		return new ModelAndView("clientRealtyForm");
	}
	
	
	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public ModelAndView loginForm(HttpSession session) {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
		return new ModelAndView("loginForm", "user", new UserMVC());
	}

	@RequestMapping(value = "/check-user", method = RequestMethod.POST) 
	public ModelAndView checkUser(@ModelAttribute("user") UserMVC user){
		
		return new ModelAndView("main", "user", user);
	}
	
	
	
	
}
