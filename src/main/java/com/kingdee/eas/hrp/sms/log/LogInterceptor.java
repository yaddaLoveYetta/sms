package com.kingdee.eas.hrp.sms.log;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 系统日志处理类
 * 
 * @ClassName: LogInterceptor
 * @Description: 操作日志统一处理
 * @author yadda
 * @date 2017年4月14日 下午2:04:20
 *
 */
public class LogInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 该方法在目标方法调用之后，记录日志
	 * 
	 * @date 2017年4月14日 下午2:12:53
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
	}

}
