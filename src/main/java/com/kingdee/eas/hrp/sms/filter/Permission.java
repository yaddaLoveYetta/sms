package com.kingdee.eas.hrp.sms.filter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 系统权限验证注解
 * 
 * @author Administrator
 *
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {
	/**
	 * 是否需要验证权限，默认true
	 * 
	 * @return
	 */
	boolean checkPermission() default true;

	/**
	 * 事务类型-通常按模块分的顶级模块代码
	 * 
	 * @return
	 */
	int objectType(); // 系统类别

	/**
	 * 明细功能模块,目前系统为二级模块划分，一级划分子系统，二级划分子系统下的模块
	 * 
	 * @return
	 */
	int objectID(); // 子系统类别

	/**
	 * 权限掩码，预置的各类权限项代码
	 * 
	 * @return
	 */
	int accessMask(); // 权限掩码

	/**
	 * 权限功能描述,对明细权限项的说明
	 * 
	 * @return
	 */
	String desc() default ""; // 描述
}
