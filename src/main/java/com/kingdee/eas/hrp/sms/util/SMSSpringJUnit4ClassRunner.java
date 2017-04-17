package com.kingdee.eas.hrp.sms.util;

import java.io.FileNotFoundException;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

@SuppressWarnings("deprecation")
public class SMSSpringJUnit4ClassRunner extends SpringJUnit4ClassRunner {

	public SMSSpringJUnit4ClassRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}

	static {
		String log4jLocation = "classpath:/config/log4j.xml";
		try {
			Log4jConfigurer.initLogging(log4jLocation);
		} catch (FileNotFoundException ex) {
			System.err.println("Cannot Initialize log4j at location: " + log4jLocation);
		}
	}
}
