package com.kingdee.eas.hrp.sms.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于控制层的日志注解
 * 
 * @ClassName: ControllerLog
 * @Description: 控制层日志记录
 * @author yadda
 * @date 2017年4月14日 下午2:17:29
 *
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLog {

	/**
	 * 
	 * @Title desc
	 * @Description 方法功能描述
	 * @param @return
	 * @return String
	 * @throws @date
	 *             2017年4月14日 下午2:18:56
	 */
	String desc() default "";
}
