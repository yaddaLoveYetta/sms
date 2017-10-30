package com.kingdee.eas.hrp.sms.exception;

import com.kingdee.eas.hrp.sms.util.StatusCode;

public class SessionLostRuntimeException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	int getErrCode() {

		return StatusCode.SESSION_LOST;
	}

	public SessionLostRuntimeException() {
		super();
	}

	public SessionLostRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SessionLostRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public SessionLostRuntimeException(String message) {
		super(message);
	}

	public SessionLostRuntimeException(Throwable cause) {
		super(cause);
	}

}
