package com.kingdee.eas.hrp.sms.dao.customize;

import java.util.List;
import java.util.Map;

public interface CUserDaoMapper {
	/**
	 * 根据类别获取菜单数据
	 * 
	 * @Title getSysMenu
	 * @param type
	 * @return List<Map<String,Object>>
	 * @date 2017-04-18 14:50:39 星期二
	 */
	List<Map<String, Object>> getSysMenu(int type);
}
