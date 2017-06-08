package com.kingdee.eas.hrp.sms.service.api.system;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface IFileUploadService {

	/**
	 * 
	 * @param request
	 * @return List<String>
	 * @throws IOException
	 *
	 * 2017年6月8日下午5:02:02
	 */
	List<String> upload(HttpServletRequest request, Integer classId) throws IOException;

}