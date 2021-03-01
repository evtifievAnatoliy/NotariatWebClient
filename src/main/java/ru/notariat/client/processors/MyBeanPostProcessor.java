package ru.notariat.client.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import ru.notariat.client.mvc.interceptors.DeleteRecordInterceptor;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor{
	
	private static final Logger logger = LoggerFactory.getLogger(MyBeanPostProcessor.class);

	@Override
	public Object postProcessBeforeInitialization(Object object, String beanName) throws BeansException {
		logger.trace("postProcessBeforeInitialization(): " + object);
		return object;
	}

	@Override
	public Object postProcessAfterInitialization(Object object, String beanName) throws BeansException {
		logger.trace("postProcessAfterInitialization(): " + object);
		return object;
	}

}
