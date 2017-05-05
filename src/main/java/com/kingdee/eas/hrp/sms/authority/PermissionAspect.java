package com.kingdee.eas.hrp.sms.authority;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kingdee.eas.hrp.sms.exception.PermissionDeniedRuntimeTimeException;
import com.kingdee.eas.hrp.sms.model.User;
import com.kingdee.eas.hrp.sms.service.api.sys.IPermissionService;

/**
 * 系统权限统一检验
 * 
 * @ClassName PermissionAspect
 * @Description 对标注@Permission的方法进行权限验证
 * @author yadda
 * @date 2017-04-15 20:22:50 星期六
 */
public class PermissionAspect {

	// 权限检验组件
	@Resource
	private IPermissionService permissionService;

	@Pointcut("@annotation(com.kingdee.eas.hrp.sms.access.Permission)")
	public void checkPermissionAspect() {
	}

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 *            切点
	 */
	@Before("checkPermissionAspect()")
	public void doBefore(JoinPoint joinPoint) {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();

		// 读取session中的用户
		User user = (User) session.getAttribute("user");

		// 请求的IP
		String ip = request.getRemoteAddr();

		// 权限校验

		System.out.println("log PermissionAspect Before method: " + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());

		Method method = getSourceMethod(joinPoint);// 验权的方法

		if (null == method) {
			// 没有该方法！
			return;
		}

		Permission permission = method.getAnnotation(Permission.class);

		if (permission != null && permission.checkPermission()) {

			// 需要验证权限
			int objectType = permission.objectType();
			int objectId = permission.objectId();
			int accessMask = permission.accessMask();
			String desc = permission.desc();

			boolean hasAuthority = permissionService.checkPermissionByUserId(user.getUserId(), objectType, objectId, accessMask);

			if (hasAuthority) {
				// 有权限
				return;
			}

			throw new PermissionDeniedRuntimeTimeException(String.format("您无[%s]的权限，请联系管理员!", desc));
		}

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
