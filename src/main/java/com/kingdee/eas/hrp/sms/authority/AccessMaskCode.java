package com.kingdee.eas.hrp.sms.authority;

/**
 * 全局权限掩码说明,业务特殊权限掩码禁忌占用以下编码
 * 
 * @ClassName: AccessMaskCode
 * @Description: 全局权限掩码说明：<br>
 *               <br>
 *               1 查看-特指进入页面的权限<br>
 *               2 新增<br>
 *               4 修改<br>
 *               8 删除<br>
 *               16 审核<br>
 *               32 反审核<br>
 *               64 禁用<br>
 *               128 反禁用<br>
 *               256 待定<br>
 *               512 待定<br>
 *               1024 待定<br>
 *               2048 待定<br>
 *               4096 待定<br>
 *               8192 待定<br>
 *               16384 待定<br>
 *               32768 待定<br>
 * @author yadda
 * @date 2017年4月15日 下午8:07:49
 *
 */
public class AccessMaskCode {

	private AccessMaskCode() {
		super();
	}

	/**
	 * 查看权限-特指进入页面的权限
	 */
	public static final int MASK_VIEW = 1;
	/**
	 * 新增
	 */
	public static final int MASK_ADD = 2;
	/**
	 * 修改
	 */
	public static final int MASK_EDIT = 4;
	/**
	 * 删除
	 */
	public static final int MASK_DELETE = 8;
	/**
	 * 审核
	 */
	public static final int MASK_CHECK = 16;
	/**
	 * 反审核
	 */
	public static final int MASK_UNCHECK = 32;
	/**
	 * 禁用
	 */
	public static final int MASK_DISABLED = 64;
	/**
	 * 反禁用
	 */
	public static final int MASK_ENABLE = 128;

	// 各模块特殊特殊的自定义的权限值============================================Begin===============================

	// 各模块特殊特殊的自定义的权限值============================================End===============================
}
