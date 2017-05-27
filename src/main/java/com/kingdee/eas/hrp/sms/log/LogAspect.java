package com.kingdee.eas.hrp.sms.log;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.kingdee.eas.hrp.sms.authority.Permission;
import com.kingdee.eas.hrp.sms.model.User;
import com.kingdee.eas.hrp.sms.service.api.sys.ILogService;
import com.kingdee.eas.hrp.sms.util.SessionUtil;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

/**
 * 系统日志统一处理
 * 
 * @ClassName LogAspect
 * @Description 日志记录切点类，记录操作日志
 * @author yadda
 * @date 2017-04-15 20:22:14 星期六
 */
@Aspect
@Component
@Order(2)
public class LogAspect {

	// 注入Service用于把日志保存数据库
	@Resource
	private ILogService logService;
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

	// Service层切点
	@Pointcut("@annotation(com.kingdee.eas.hrp.sms.log.ServiceLog)")
	public void serviceLogAspect() {
	}

	// Controller层切点
	@Pointcut("@annotation(com.kingdee.eas.hrp.sms.log.ControllerLog)")
	public void controllerLogAspect() {
	}

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 *            切点
	 * @throws ClassNotFoundException
	 * @throws NotFoundException
	 */
	@Before("controllerLogAspect()")
	public void doBefore(JoinPoint joinPoint) {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();

		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");

		Method method = getSourceMethod(joinPoint);// 当前调用的方法

		if (null == method) {
			// 没有该方法！
			return;
		}

		ControllerLog log = method.getAnnotation(ControllerLog.class);

		User user = null;

		if ("/user/login".equalsIgnoreCase(requestUrl)) {
			user = new User();
			user.setName(joinPoint.getArgs()[0].toString());
		} else {
			user = (User) session.getAttribute("user");
			user = SessionUtil.getUser();
		}
		// 读取session中的用户

		// 请求的IP

		try {
			// *========控制台输出=========*//
			System.out.println("=====前置通知开始=====");
			System.out.println("用户id:" + user.getUserId());
			System.out.println("用户名:" + user.getName());
			System.out.println("请求IP:" + request.getRemoteAddr());
			System.out.println("方法描述:" + getControllerMethodDesc(joinPoint));
			System.out.println("请求时间:" + new Date().toString());
			System.out.println("请求路径:" + joinPoint.getTarget().getClass().getName());
			System.out.println("请求方法:" + joinPoint.getSignature().getName());

			String userId = user.getUserId();
			String userName = user.getName();
			String ip = request.getRemoteAddr();
			String desc = getControllerMethodDesc(joinPoint);
			Date optTime = new Date();
			String clazz = joinPoint.getTarget().getClass().getName();
			String meth = joinPoint.getSignature().getName();

			// 操作日志记录到数据库中
			logService.add(userId, userName, ip, desc, optTime, clazz, meth);

		} catch (Exception e) {
			// 日志操作异常不处理-不影响业务流程
		}
	}

	/**
	 * 异常通知 用于拦截service层记录异常日志
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "serviceLogAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		// 读取session中的用户
		User user = (User) request.getSession().getAttribute("user");
		// 获取请求ip
		String ip = request.getRemoteAddr();
		// 获取用户请求方法的参数并序列化为JSON格式字符串
		String params = "";

		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				// params += JSONUtil.toJsonString(joinPoint.getArgs()[i]) +
				// ";";

				params += JSON.toJSONString(joinPoint.getArgs()[i]) + ";";
			}
		}

		try {

			/* ========控制台输出========= */
			System.out.println("=====异常通知开始=====");
			System.out.println("异常代码:" + e.getClass().getName());
			System.out.println("异常信息:" + e.getMessage());
			System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			System.out.println("方法描述:" + getServiceMthodDesc(joinPoint));
			System.out.println("请求人:" + user.getName());
			System.out.println("请求IP:" + ip);
			System.out.println("请求参数:" + params);

			/* ==========数据库日志========= */
			// Log log = SpringContextHolder.getBean("logxx");
			// log.setDescription(getServiceMthodDescription(joinPoint));
			// log.setExceptionCode(e.getClass().getName());
			// log.setType("1");
			// log.setExceptionDetail(e.getMessage());
			// log.setMethod(
			// (joinPoint.getTarget().getClass().getName() + "." +
			// joinPoint.getSignature().getName() + "()"));
			// log.setParams(params);
			// log.setCreateBy(user);
			// log.setCreateDate(DateUtil.getCurrentDate());
			// log.setRequestIp(ip);
			// // 保存数据库
			// logService.add(log);
			System.out.println("=====异常通知结束=====");

		} catch (Exception ex) {
			// 记录本地异常日志
			logger.error("==异常通知异常==");
			logger.error("异常信息:{}", ex.getMessage());
		}
		/* ==========记录本地异常日志========== */
		logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);

	}

	/**
	 * 获取注解中对方法的描述信息 用于service层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private static String getServiceMthodDesc(JoinPoint joinPoint) throws Exception {

		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();

		for (Method method : methods) {

			if (method.getName().equals(methodName)) {

				Class[] clazzs = method.getParameterTypes();

				if (clazzs.length == arguments.length) {

					return method.getAnnotation(ServiceLog.class).desc();

				}

			}
		}
		return "";
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private static String getControllerMethodDesc(JoinPoint joinPoint) throws Exception {

		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String desc = "";

		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					desc = method.getAnnotation(ControllerLog.class).desc();
					break;
				}
			}
		}
		return desc;
	}

	private Map<String, Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws NotFoundException {

		Map<String, Object> map = new HashMap<String, Object>();

		ClassPool pool = ClassPool.getDefault();
		// ClassClassPath classPath = new ClassClassPath(this.getClass());
		ClassClassPath classPath = new ClassClassPath(cls);
		pool.insertClassPath(classPath);

		CtClass cc = pool.get(clazzName);
		CtMethod cm = cc.getDeclaredMethod(methodName);
		MethodInfo methodInfo = cm.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		if (attr == null) {
			// exception
		}
		// String[] paramNames = new String[cm.getParameterTypes().length];
		int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
		for (int i = 0; i < cm.getParameterTypes().length; i++) {
			map.put(attr.variableName(i + pos), args[i]);// paramNames即参数名
		}

		// Map<>
		return map;
	}

	/**
	 * 获取当前调用的方法
	 * 
	 * @Title getSourceMethod
	 * @Description 获取当前调用的方法
	 * @param joinPoint
	 * @return Method
	 * @date 2017-04-15 21:20:51 星期六
	 */
	private Method getSourceMethod(JoinPoint joinPoint) {
		Method proxyMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
		try {
			return joinPoint.getTarget().getClass().getMethod(proxyMethod.getName(), proxyMethod.getParameterTypes());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
}
