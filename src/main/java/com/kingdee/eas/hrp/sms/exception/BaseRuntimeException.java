package com.kingdee.eas.hrp.sms.exception;

public class BaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	/**
	 * 错误代码，参考com.kingdee.eas.hrp.sms.util.StatusCode
	 */
	private int errCode;

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public BaseRuntimeException() {
		super();
	}

	public BaseRuntimeException(int errCode) {
		this.errCode = errCode;
	}

	public BaseRuntimeException(int errCode, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errCode = errCode;
	}

	public BaseRuntimeException(int errCode, String message, Throwable cause) {
		super(message, cause);
		this.errCode = errCode;
	}

	public BaseRuntimeException(int errCode, String message) {
		super(message);
		this.errCode = errCode;
	}

	public BaseRuntimeException(int errCode, Throwable cause) {
		super(cause);
		this.errCode = errCode;
	}

}
