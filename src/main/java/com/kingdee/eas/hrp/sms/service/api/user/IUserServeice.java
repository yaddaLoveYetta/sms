package com.kingdee.eas.hrp.sms.service.api.user;

import java.util.List;

import com.kingdee.eas.hrp.sms.model.User;

public interface IUserServeice {

	/**
	 * 
	 * @Title getUsers
	 * @Description 分页获取系统用户
	 * @param @param
	 *            pageNum 当前页码
	 * @param @param
	 *            pageSize 分页大小
	 * @param @return
	 * @return List<User>
	 * @throws @date
	 *             2017年4月15日 上午12:01:39
	 */
	List<User> getUserList(int pageNum, int pageSize);
}
