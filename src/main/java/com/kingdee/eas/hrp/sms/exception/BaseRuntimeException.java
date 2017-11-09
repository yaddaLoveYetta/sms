package com.kingdee.eas.hrp.sms.exception;

import com.yadda.api.common.BaseException;

/**
 * 错误基础类
 * 
 * @ClassName BaseRuntimeException
 * @Description 增加系统错误代码
 * @author yadda
 * @date 2017-04-15 22:29:23 星期六
 */
public abstract class BaseRuntimeException extends BaseException {

	private static final long serialVersionUID = 1L;

	/**
	 * Returns the detail message code of this throwable.
	 * 
	 * @see com.kingdee.eas.hrp.sms.util.StatusCode
	 */
	abstract int getErrCode();

	public BaseRuntimeException() {
		super();
	}

	public BaseRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BaseRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseRuntimeException(String message) {
		super(message);
	}

	public BaseRuntimeException(Throwable cause) {
		super(cause);
	}

}
