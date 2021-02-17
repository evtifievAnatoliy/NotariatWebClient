package ru.notariat.client.processors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object object, String beanName) throws BeansException {
		System.out.println("postProcessBeforeInitialization(): " + object);
		return object;
	}

	@Override
	public Object postProcessAfterInitialization(Object object, String beanName) throws BeansException {
		//System.out.println("postProcessAfterInitialization(): " + object);
		return object;
	}

}
