package com.kingdee.eas.hrp.sms.service.api.supplier;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface IFileService {

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
	void saveUrlToDb(int classId, String itemId, List<String> urls);

	/**
	 * 审核证件资质附件
	 * 
	 * @Title checkAttachment
	 * @param classId
	 * @param id
	 * @param entryId
	 * @param type
	 * @return
	 * @return boolean
	 * @date 2017-09-29 15:26:07 星期五
	 */
	boolean checkAttachment(int classId, String id, String entryId, int type);

}