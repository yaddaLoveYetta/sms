package com.kingdee.eas.hrp.sms.exception;

/**
 * 日志错误
 * 
 * @ClassName LogErrorRuntimeException
 * @author yadda
 * @date 2017-05-27 13:47:37 星期六
 */
public class LogErrorRuntimeException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	int getErrCode() {
		return 600;
	}

	public LogErrorRuntimeException() {
		super();
	}

	public LogErrorRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LogErrorRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogErrorRuntimeException(String message) {
		super(message);
	}

	public LogErrorRuntimeException(Throwable cause) {
		super(cause);
	}

}
