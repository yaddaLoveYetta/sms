package com.kingdee.eas.hrp.sms.filter;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.kingdee.eas.hrp.sms.util.SessionUtil;

/**
 * HttpSession属性监听器
 * 
 * @ClassName SessionAttributeListener
 * @author yadda
 * @date 2017-05-28 13:13:10 星期日
 */
public class SessionAttributeListener implements HttpSessionAttributeListener {
	/**
	 * 添加Session
	 */
	public void attributeAdded(HttpSessionBindingEvent event) {

		SessionUtil.set(event.getName(), event.getValue());
	}

	/**
	 * 销毁Session
	 */
	public void attributeRemoved(HttpSessionBindingEvent event) {
		SessionUtil.remove(event.getName());
	}

	/**
	 * 重置Session
	 */
	public void attributeReplaced(HttpSessionBindingEvent event) {

		SessionUtil.set(event.getName(), event.getValue());
	}

}
