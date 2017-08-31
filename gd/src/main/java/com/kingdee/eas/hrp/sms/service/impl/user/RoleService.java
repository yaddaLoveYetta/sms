package com.kingdee.eas.hrp.sms.service.impl.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.dao.customize.RoleDaoMapper;
import com.kingdee.eas.hrp.sms.dao.generate.AccessControlMapper;
import com.kingdee.eas.hrp.sms.dao.generate.ObjectTypeMapper;
import com.kingdee.eas.hrp.sms.dao.generate.RoleMapper;
import com.kingdee.eas.hrp.sms.model.AccessControl;
import com.kingdee.eas.hrp.sms.model.AccessControlExample;
import com.kingdee.eas.hrp.sms.model.ObjectType;
import com.kingdee.eas.hrp.sms.model.Role;
import com.kingdee.eas.hrp.sms.model.RoleExample;
import com.kingdee.eas.hrp.sms.model.RoleExample.Criteria;
import com.kingdee.eas.hrp.sms.service.api.user.IRoleService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class RoleService extends BaseService implements IRoleService {

	@Override
	public Map<String, Object> getAccessByRole(String roleId) {

		Map<String, Object> accessMap = new HashMap<String, Object>();

		AccessControlMapper mapper = sqlSession.getMapper(AccessControlMapper.class);

		AccessControlExample example = new AccessControlExample();
		com.kingdee.eas.hrp.sms.model.AccessControlExample.Criteria criteria = example.createCriteria();

		criteria.andRoleIdEqualTo(roleId);


		example.setOrderByClause("objectType asc,objectId asc");

		List<AccessControl> list = mapper.selectByExample(example);

		for (AccessControl item : list) {

			accessMap.put(item.getObjectType() + "-" + item.getObjectId(), item.getAccessMask());
		}

		return accessMap;

	}

	@Override
	public Role getRole(String roleId) {

		RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);

		RoleExample example = new RoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);

		List<Role> list = mapper.selectByExample(example);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getRolePermissions(String type, String roleId) {

		List<Map<String, Object>> rolePermissions = new ArrayList<Map<String, Object>>();

		// 1:获取角色类别所有可用权限项
		List<Map<String, Object>> roleTypePermissions = getRoleTypePermissions(type);

		// 2:获取角色当前对各模块的权限掩码
		Map<String, AccessControl> roleAccess = getAccessByRoleID(roleId);

		// 3:构建角色权限树
		for (Map<String, Object> roleTypePermission : roleTypePermissions) {

			// Integer FTopClassID = (Integer) roleTypePermission.get("FTopClassID");
			// String FTopClassName = roleTypePermission.get("FTopClassName").toString();
			// Integer FSubSysID = (Integer) roleTypePermission.get("FSubSysID");

			Integer topClassId = (Integer) roleTypePermission.get("topClassId");
			Integer subSysId = (Integer) roleTypePermission.get("subSysId");
			String topClassName = roleTypePermission.get("topClassName").toString();
			String subSysName = (String) roleTypePermission.get("subSysName");

			String permissionName = (String) roleTypePermission.get("permissionName");
			Integer objectType = (Integer) roleTypePermission.get("objectType");
			Integer objectId = (Integer) roleTypePermission.get("objectId");
			Integer accessMask = (Integer) roleTypePermission.get("accessMask");
			Integer accessUse = (Integer) roleTypePermission.get("accessUse");
			Integer accessIndex = (Integer) roleTypePermission.get("accessIndex");

			int index = contains(rolePermissions, "topClassId", topClassId.toString());

			if (index >= 0) {
				// 已存在顶级菜单
				Map<String, Object> topMenuItem = rolePermissions.get(index);

				// 获取二级菜单列表
				List<Map<String, Object>> subSys;
				if (topMenuItem.containsKey("subSys")) {
					subSys = (List<Map<String, Object>>) topMenuItem.get("subSys");
				} else {
					subSys = new ArrayList<Map<String, Object>>();
				}

				int subMenuItemIndex = contains(subSys, "subSysId", subSysId.toString());

				if (subMenuItemIndex >= 0) {
					// 存在二级菜单
					Map<String, Object> subMenuItem = subSys.get(subMenuItemIndex);

					List<Map<String, Object>> accessTypes;

					if (subMenuItem.containsKey("accessTypes")) {
						accessTypes = (List<Map<String, Object>>) subMenuItem.get("accessTypes");
					} else {
						accessTypes = new ArrayList<Map<String, Object>>();
					}

					int accessTypesIndex = contains(accessTypes, "accessIndex", accessIndex.toString());

					if (accessTypesIndex >= 0) {
						// 存在明细权限项
					} else {
						// 不存在明细权限项
						Map<String, Object> accessTypesItem = new HashMap<String, Object>();
						accessTypesItem.put("permissionName", permissionName);
						accessTypesItem.put("accessMask", accessMask);
						accessTypesItem.put("accessUse", accessUse);
						accessTypesItem.put("accessIndex", accessIndex);
						// accessTypesItem.put("FEnable", hasAccess(roleAccess, FTopClassID, FSubSysID, FAccessMask));
						accessTypesItem.put("enable", hasAccess(roleAccess, objectType, objectId, accessMask));
						// 判断的是FTopClassID，FSubSysID对应的FObjectType, FObjectID的权限

						accessTypes.add(accessTypesItem);
					}

				} else {
					// 不存在二级菜单

					Map<String, Object> subSysItem = new HashMap<String, Object>(); // 二级菜单明细
					List<Map<String, Object>> accessTypes = new ArrayList<Map<String, Object>>(); // 二级菜单权限项列表
					Map<String, Object> accessTypesItem = new HashMap<String, Object>(); // 二级菜单权限项明细

					// 权限项明细
					accessTypesItem.put("permissionName", permissionName);
					accessTypesItem.put("accessMask", accessMask);
					accessTypesItem.put("accessUse", accessUse);
					accessTypesItem.put("accessIndex", accessIndex);
					accessTypesItem.put("enable", hasAccess(roleAccess, objectType, objectId, accessMask));
					accessTypes.add(accessTypesItem);

					// 二级菜单明细
					subSysItem.put("subSysId", subSysId);
					subSysItem.put("subSysName", subSysName);
					subSysItem.put("accessTypes", accessTypes);
					subSys.add(subSysItem);

				}

			} else {
				// 不存在顶级菜单--添加顶级权限节点菜单
				Map<String, Object> topMenuItem = new HashMap<String, Object>(); // 一级菜单明细
				List<Map<String, Object>> subSys = new ArrayList<Map<String, Object>>(); // 二级菜单列表
				Map<String, Object> subSysItem = new HashMap<String, Object>(); // 二级菜单明细
				List<Map<String, Object>> accessTypes = new ArrayList<Map<String, Object>>(); // 二级菜单权限项列表
				Map<String, Object> accessTypesItem = new HashMap<String, Object>(); // 二级菜单权限项明细

				// 权限项明细
				accessTypesItem.put("permissionName", permissionName);
				accessTypesItem.put("accessMask", accessMask);
				accessTypesItem.put("accessUse", accessUse);
				accessTypesItem.put("accessIndex", accessIndex);
				accessTypesItem.put("enable", hasAccess(roleAccess, objectType, objectId, accessMask));
				accessTypes.add(accessTypesItem);

				// 二级菜单明细
				subSysItem.put("subSysId", subSysId);
				subSysItem.put("subSysName", subSysName);
				subSysItem.put("accessTypes", accessTypes);
				subSys.add(subSysItem);

				topMenuItem.put("topClassId", topClassId);
				topMenuItem.put("topClassName", topClassName);
				topMenuItem.put("subSys", subSys);

				rolePermissions.add(topMenuItem);
			}

		}

		return rolePermissions;

	}

	private List<Map<String, Object>> getRoleTypePermissions(String type) {

		int roleControl = 3;// 两者都可用
		if (type.equals("Ro9iCuOsVEmznmE+YZSi7hAEEAQ=")) {
			// 系统角色
			roleControl = 1;
		} else if (type.equals("f1sGInqJq0aUNY5MmpKM8RAEEAQ=")) {
			// 供应商角色
			roleControl = 2;
		}
		
		RoleDaoMapper mapper = sqlSession.getMapper(RoleDaoMapper.class);

		return mapper.getRoleTypePermissions(roleControl);
	}

	private Map<String, AccessControl> getAccessByRoleID(String roleId) {

		Map<String, AccessControl> ret = new HashMap<String, AccessControl>();

		AccessControlMapper mapper = sqlSession.getMapper(AccessControlMapper.class);

		AccessControlExample example = new AccessControlExample();
		com.kingdee.eas.hrp.sms.model.AccessControlExample.Criteria criteria = example.createCriteria();

		criteria.andRoleIdEqualTo(roleId);

		List<AccessControl> accessControls = mapper.selectByExample(example);

		for (AccessControl accessControl : accessControls) {
			ret.put(accessControl.getObjectType() + "-" + accessControl.getObjectId(), accessControl);

		}
		return ret;
	}

	/**
	 * 判断list中是否包含指定key的Map子项
	 * 
	 * @param list
	 * @param key
	 *            list中map元素的key
	 * @param value
	 *            list中map元素的value
	 * @return
	 */
	private int contains(List<Map<String, Object>> list, String key, String value) {

		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			if (!map.containsKey(key)) {
				return -1;
			}
			if (map.get(key).toString().equalsIgnoreCase(value)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 判断角色是否具有某明细权限(对accessMask的权限)
	 * 
	 * @param accessMap
	 *            角色对所有模块的权限掩码
	 * @param topClassID
	 *            一级系统ID
	 * @param subSysID
	 *            二级系统ID
	 * @param accessMask
	 *            明细权限掩码
	 * @return
	 */
	private int hasAccess(Map<String, AccessControl> accessMap, int topClassID, int subSysID, int accessMask) {

		String subSysAccess = topClassID + "-" + subSysID;

		int userAccess = 0;
		if (accessMap.containsKey(subSysAccess)) {
			userAccess = accessMap.get(subSysAccess).getAccessMask();
		} else {
			return 0;
		}

		if ((userAccess & accessMask) == accessMask) {

			return 1;
		}

		return 0;
	}

	@Override
	@Transactional
	public void saveRolePermissions(JSONArray arry, String roleId) {

		List<AccessControl> list = new ArrayList<AccessControl>();

		// 将FTopClassID-FSubSysID 转成权限系统的FObjectType-FObjectID-
		Map<String, ObjectType> objectTypes = getObjectType();
		// 保存
		for (int i = 0; i < arry.size(); i++) {

			JSONObject obj = arry.getJSONObject(i);

			Integer topClassId = obj.getInteger("topClassId");
			Integer subSysId = obj.getInteger("subSysId");

			String key = String.format("%s-%s", topClassId, subSysId); // 已拼成FObjectType-FObjectID的形式方便取数

			if (objectTypes.containsKey(key)) {

				// 找到对应关系，即后台已配置业务系统与权限系统对应关系
				ObjectType objectTypeItem = objectTypes.get(key);

				Integer objectType = objectTypeItem.getObjectType();
				Integer objectId = objectTypeItem.getObjectId();

				// 检查重复
				boolean isExist = false;

				for (AccessControl accessControl : list) {

					if (accessControl.getObjectType() == objectType && accessControl.getObjectId() == objectId) {
						// 已存在
						isExist = true;
						break;
					}

				}
				if (!isExist) {
					AccessControl accessControl = new AccessControl();

					accessControl.setObjectType(objectType);
					accessControl.setObjectId(objectId);
					accessControl.setRoleId(roleId);
					accessControl.setAccessMask(obj.getInteger("accessMask"));

					list.add(accessControl);
				}

			} else {
				// 没有对应关系-忽略
			}

		}

		AccessControlMapper mapper = sqlSession.getMapper(AccessControlMapper.class);

		AccessControlExample example = new AccessControlExample();
		com.kingdee.eas.hrp.sms.model.AccessControlExample.Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);

		// 清除角色组权限信息
		mapper.deleteByExample(example);

		// 保存角色组权限信息
		for (AccessControl accessControl : list) {
			// TODO 改批量插入
			mapper.insert(accessControl);

		}

	}

	/**
	 * 获取菜单系统与权限系统的对应关系
	 * 
	 * @Title getObjectType
	 * @return Map<String,ObjectType>
	 * @date 2017-04-25 16:47:49 星期二
	 */
	private Map<String, ObjectType> getObjectType() {

		Map<String, ObjectType> ret = new HashMap<String, ObjectType>();

		ObjectTypeMapper mapper = sqlSession.getMapper(ObjectTypeMapper.class);

		List<ObjectType> objectTypes = mapper.selectByExample(null);

		for (ObjectType objectType : objectTypes) {

			ret.put(String.format("%s-%s", objectType.getTopClassId(), objectType.getSubSysId()), objectType);
		}
		return ret;
	}

}
