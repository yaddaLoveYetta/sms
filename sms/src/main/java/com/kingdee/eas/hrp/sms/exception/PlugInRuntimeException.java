package com.kingdee.eas.hrp.sms.exception;

import com.kingdee.eas.hrp.sms.util.StatusCode;

/**
 * 插件逻辑阻止业务继续运行错误类
 * 
 * @author Administrator
 *
 */
public class PlugInRuntimeException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	int getErrCode() {
		return StatusCode.PLUGIN_ERROR;
	}

	public PlugInRuntimeException() {
		super("插件错误！");
	}

	public PlugInRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlugInRuntimeException(String message) {
		super(message);
	}

	public PlugInRuntimeException(Throwable cause) {
		super(cause);
	}
}
