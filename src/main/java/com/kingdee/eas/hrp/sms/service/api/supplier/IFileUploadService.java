package com.kingdee.eas.hrp.sms.service.api.supplier;

import java.util.List;

public interface IFileUploadService {

	/**
	 * 保存图片映射到数 据库
	 * 
	 * @Title saveUrlToDb
	 * @param classId
	 *            事务类型
	 * @param itemId
	 *            内码
	 * @param url
	 *            映射集合
	 * @return void
	 * @date 2017-06-09 11:49:54 星期五
	 */
	void saveUrlToDb(int classId, String itemId, List<String> url);

}