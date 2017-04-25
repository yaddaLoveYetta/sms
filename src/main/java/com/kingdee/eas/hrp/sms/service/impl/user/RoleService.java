package com.kingdee.eas.hrp.sms.service.impl.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kingdee.eas.hrp.sms.dao.customize.RoleDaoMapper;
import com.kingdee.eas.hrp.sms.dao.generate.AccessControlMapper;
import com.kingdee.eas.hrp.sms.dao.generate.RoleMapper;
import com.kingdee.eas.hrp.sms.model.AccessControl;
import com.kingdee.eas.hrp.sms.model.AccessControlExample;
import com.kingdee.eas.hrp.sms.model.Role;
import com.kingdee.eas.hrp.sms.model.RoleExample;
import com.kingdee.eas.hrp.sms.model.RoleExample.Criteria;
import com.kingdee.eas.hrp.sms.service.api.user.IRoleService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class RoleService extends BaseService implements IRoleService {

	@Override
	public Map<String, Object> getAccessByRole(int roleId) {

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
	public Role getRole(int roleId) {

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

	@Override
	public List<Map<String, Object>> getRolePermissions(int type, int roleId) {

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

	private List<Map<String, Object>> getRoleTypePermissions(int type) {

		RoleDaoMapper mapper = sqlSession.getMapper(RoleDaoMapper.class);

		return mapper.getRoleTypePermissions(type);
	}

	private Map<String, AccessControl> getAccessByRoleID(int roleId) {

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

}
