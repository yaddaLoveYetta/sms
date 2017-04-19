package com.kingdee.eas.hrp.sms.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 此工具类为service提供动态获取实例bean功能
 * 
 * @author Administrator
 *
 */
public class Environ implements ApplicationContextAware {

	private static ApplicationContext ctx;

	public static ApplicationContext getApplicationContext() {
		return ctx;
	}

	/**
	 * 获取bean
	 * 
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		try {
			return getApplicationContext().getBean(name);
		} catch (BeansException e) {
			return null;
		}
	}

	/**
	 * 获取指定类型对象
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		try {
			return getApplicationContext().getBean(clazz);
		} catch (BeansException e) {
			return null;
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		Environ.ctx = applicationContext;
	}
}