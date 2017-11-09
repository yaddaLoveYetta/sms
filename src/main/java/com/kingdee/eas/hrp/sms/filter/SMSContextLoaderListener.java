package com.kingdee.eas.hrp.sms.filter;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.util.Log4jWebConfigurer;

/**
 * @author yadda
 */
public class SMSContextLoaderListener extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		Log4jWebConfigurer.initLogging(event.getServletContext()); // 初始化log4j配置
		super.contextInitialized(event); // 初始化spring容器
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		Log4jWebConfigurer.shutdownLogging(event.getServletContext());
	}
}
