package ru.notariat.client.mvc.interceptors;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ru.notariat.client.db.interfaces.UsersDB;
import ru.notariat.client.db.objects.User;
import ru.notariat.client.mvc.objects.UserMVC;

public class CheckUserInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private UsersDB usersDAO; 
	
	private static final Logger logger = LoggerFactory.getLogger(CheckUserInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}
	
	
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
	
		long startTime = Long.valueOf(request.getAttribute("startTime").toString());

		

		int totalTime = (int) ((System.currentTimeMillis() - startTime) / 1000) % 60;
		logger.info(handler + ":post handle method, totalTime passed: " + totalTime + "sec");
		
		if (request.getRequestURI().contains("check-user")) {
			UserMVC user = (UserMVC) modelAndView.getModel().get("user");
			User userDB = new User();
			userDB.setEmail(user.getName());
			userDB.setUserPassword(user.getPassword());
			
			try {
				if (!usersDAO.checkUserByEmailAndPass(userDB) || !usersDAO.chekUserGroup(userDB, "admin"))
					response.sendRedirect(request.getContextPath() + "/loginForm");
				logger.info(handler + ":Check user password " + usersDAO.checkUserByEmailAndPass(userDB));
				logger.info(handler + ":Check admin group " + usersDAO.chekUserGroup(userDB, "admin"));
			}
			catch(Exception ex) {
				logger.warn(ex.getMessage());
				response.sendRedirect(request.getContextPath() + "/loginForm");
			}
		}
	}

}
