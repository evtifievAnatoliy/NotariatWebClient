package ru.notariat.client.mvc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class GetJsonTimeStampsListIntercepter extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(GetJsonDocumentsListIntercepter.class);
	
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		
		logger.info("/get-json-timeStampsList, method = RequestMethod.GET " + "/firstStam=" + request.getParameter("firstStamp") + " secondStamp=" + request.getParameter("secondStamp"));
		
	}

}