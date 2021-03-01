package ru.notariat.client.mvc.interceptors;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PutJsonNewRecordIntercepter extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(PutJsonNewRecordIntercepter.class);
	
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		
		logger.info("/put-json-new-record, method = RequestMethod.POST " + "/");
		
	}

}