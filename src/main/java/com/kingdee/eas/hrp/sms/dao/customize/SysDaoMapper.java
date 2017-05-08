package com.kingdee.eas.hrp.sms.dao.customize;

import java.util.List;
import java.util.Map;

public interface SysDaoMapper {
	/**
	 * 根据类别获取菜单数据
	 * 
	 * @Title getSysMenu
	 * @param type
	 *            1 系统用户可用菜单 2：供应商用户可用菜单 3：两者都可用
	 * @return List<Map<String,Object>>
	 * @date 2017-04-18 14:50:39 星期二
	 */
	List<Map<String, Object>> getSysMenu(int type);

	/**
	 * 新增单据时获取单据主键</br>
	 * 该方法不可重复调用，即任何情况下两次调用的结果都不一致
	 * 
	 * <P>
	 * 参考EAS单据内码产生规则
	 * </p>
	 * 
	 * @Title getId
	 * @param bosType
	 *            formClass中定义的bostype 长度必须为4或8,建议统一用8位，否则返回空
	 * @return String 32位唯一字符串
	 * @date 2017-05-06 14:42:39 星期六
	 */
	String getId(String bosType);
}
