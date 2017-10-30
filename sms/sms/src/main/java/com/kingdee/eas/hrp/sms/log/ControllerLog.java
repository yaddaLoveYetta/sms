package com.kingdee.eas.hrp.sms.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于控制层的日志注解
 * 
 * @ClassName ControllerLog
 * @Description 控制层日志记录
 * @author yadda
 * @date 2017年4月14日 下午2:17:29
 *
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLog {

	/**
	 * 日志描述信息
	 * 
	 * @Title desc
	 * @return
	 * @return String
	 * @date 2017-05-27 17:01:55 星期六
	 */
	String desc();

	/**
	 * getItem等通过模板去操作的公共接口补充<br/>
	 * annotation中若有classId=0，最终的描述会是通过classId查询到t_sms_objectType表中的 desc()+name<br/>
	 * 
	 * @Title classId
	 * @return
	 * @return int
	 * @date 2017年4月14日 下午2:18:56
	 */
	int classId() default -1;
}
