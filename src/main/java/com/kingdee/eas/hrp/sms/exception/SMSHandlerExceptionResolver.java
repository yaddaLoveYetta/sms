package com.kingdee.eas.hrp.sms.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName: SMSHandlerExceptionResolver
 * @Description: 异常统一处理类
 * @author yadda
 * @date 2017年4月13日 下午2:14:39
 *
 */
public class SMSHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		// 根据不同错误转向不同页面

		return null;
	}

	/**
	 * @Title: test
	 * @Description: TODO(描述一下这个方法的作用)
	 * @param
	 * @return void
	 * @throws @date
	 *             2017年4月13日 下午2:23:15
	 */
	public void test() {

	}

}
