package com.kingdee.eas.hrp.sms.util;

import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.StyledEditorKit.BoldAction;

import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.kingdee.eas.hrp.sms.authority.Permission;

@Service
public class ApiStore implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

	private static boolean isStart = false;// 多容器时控制ApplicationListener只被执行一次

	private ApplicationContext ctx;

	private ApplicationContext getApplicationContext() {
		return ctx;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// 值获取父容器
		if (applicationContext.getParent() == null) {
			// 不是root容器，例如是 spring mvc 初始化的spring-servlet容器
			this.ctx = applicationContext;
		}
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		// event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")
		if (isStart || event.getApplicationContext().getParent() != null) {
			// 不是root容器，例如是 spring mvc 初始化的spring-servlet容器
			return;
		}
		isStart = true;
		// 获取容器中所有带@Permission注解的bean
		Map<String, Object> beansWithAnnotation = ctx.getBeansWithAnnotation(Permission.class);

		Set<Entry<String, Object>> entrySet = beansWithAnnotation.entrySet();

		for (Entry<String, Object> entry : entrySet) {

			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}

	}

}
