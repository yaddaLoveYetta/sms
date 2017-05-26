package com.kingdee.eas.hrp.sms.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName: ServiceLog
 * @Description: 服务层日志记录
 * @author yadda
 * @date 2017年4月14日 下午2:24:15
 *
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLog {
	/**
	 * 
	 * @Title desc
	 * @Description 方法功能描述
	 * @param
	 * @return String
	 * @date 2017年4月14日 下午2:24:48
	 * 
	 */
	String desc() default "";
}
