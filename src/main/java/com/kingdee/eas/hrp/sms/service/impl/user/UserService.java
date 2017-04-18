package com.kingdee.eas.hrp.sms.service.impl.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingdee.eas.hrp.sms.dao.generate.UserMapper;
import com.kingdee.eas.hrp.sms.model.Role;
import com.kingdee.eas.hrp.sms.model.RoleExample;
import com.kingdee.eas.hrp.sms.model.RoleExample.Criteria;
import com.kingdee.eas.hrp.sms.model.User;
import com.kingdee.eas.hrp.sms.model.UserExample;
import com.kingdee.eas.hrp.sms.service.api.user.IRoleService;
import com.kingdee.eas.hrp.sms.service.api.user.IUserService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;
import com.kingdee.eas.hrp.sms.util.Environ;

@Service
public class UserService extends BaseService implements IUserService {

	@Override
	public List<User> getUserList(int pageNum, int pageSize) {

		Page<User> page = PageHelper.startPage(pageNum, pageSize, true);

		UserMapper mapper = sqlSession.getMapper(UserMapper.class);

		UserExample example = new UserExample();
		// example.setOrderByClause("userId");
		example.setOrderByClause("userId desc ,name asc");

		// Criteria criteria = example.createCriteria();
		// criteria.andUserIdEqualTo(2);

		List<User> list = mapper.selectByExample(example);

		System.out.println(page.getTotal());
		System.out.println(list.size());

		PageInfo<User> pageInfo = new PageInfo<>(list);

		// assertEquals(1, page.getPageNum());

		// Assert.assertEquals(1, pageInfo.getPageNum());
		// Assert.assertEquals(2, pageInfo.getPageSize());
		// Assert.assertEquals(3, pageInfo.getTotal());
		System.out.println(pageInfo.getOrderBy());
		System.out.println(JSON.toJSONString(list));

		// //用PageInfo对结果进行包装
		// PageInfo page = new PageInfo(list);
		// //测试PageInfo全部属性
		// //PageInfo包含了非常全面的分页属性
		// assertEquals(1, page.getPageNum());
		// assertEquals(10, page.getPageSize());
		// assertEquals(1, page.getStartRow());
		// assertEquals(10, page.getEndRow());
		// assertEquals(183, page.getTotal());
		// assertEquals(19, page.getPages());
		// assertEquals(1, page.getFirstPage());
		// assertEquals(8, page.getLastPage());
		// assertEquals(true, page.isFirstPage());
		// assertEquals(false, page.isLastPage());
		// assertEquals(false, page.isHasPreviousPage());
		// assertEquals(true, page.isHasNextPage());

		return null;
	}

	@Override
	public List<Map<String, Object>> getSysMenu(int type, int userId) {

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

//		IRoleService roleService = Environ.getBean(IRoleService.class);
//
//		// 获取用户角色
//		int roleId = roleService.getRole(userId).getRoleId();
//
//		// 获取该角色所有的权限
//		Map<String, Object> accessMap = roleService.getAccessByRole(roleId);
//
//		// 根据用户类别获取所有系统菜单
//		// 平台用户-t_DataFlowSubSystem中配置为1,供应链用户-t_DataFlowSubSystem中配置为2
//		int ownerType = type == 50801 ? 1 : type == 50802 ? 2 : 3;
//
//		List<Map<String, Object>> menuList = userService.getSysMenu(ownerType);
//		// 根据用户权限过滤菜单
//		for (Map<String, Object> menu : menuList) {
//			String topClassID = menu.get("FTopClassID").toString();
//			String objectType = menu.get("FObjectType").toString(); // 菜单对应的objectType - 即菜单要验证的权限类型
//			String objectID = menu.get("FObjectID").toString(); // 菜单对应的objectID- 即菜单要验证的权限明细
//			int accessMask = Integer.parseInt(menu.get("FAccessMask").toString()); // 系统定义的菜单项权限
//			String subSysAccess = objectType + "-" + objectID;
//
//			// 用户对subSysAccess的权限
//			int userAccess = 0;
//			if (accessMap.containsKey(subSysAccess)) {
//				userAccess = Integer.parseInt(accessMap.get(subSysAccess).toString());
//			}
//			// userAccess > 0 处理没有配置子系统权限的情况 ，accessMask > 0 处理没有配置index=0进入页面权限的情况. 0 & any==0
//			if (userAccess > 0 && accessMask > 0 && (userAccess & accessMask) == accessMask) {
//				// 有权限
//				int index = contains(result, topClassID);
//				if (index >= 0) {
//					// 已存在顶级菜单
//					Map<String, Object> item = result.get(index);
//					Map<String, Object> items = new HashMap<String, Object>();
//					items.put("topClassID", menu.get("FTopClassID"));
//					items.put("subSysID", menu.get("FSubSysID"));
//					items.put("name", menu.get("FName"));
//					items.put("url", menu.get("FUrl"));
//					items.put("icon", menu.get("FIcon"));
//
//					if (item.containsKey("items")) {
//						((List<Map<String, Object>>) item.get("items")).add(items);
//					} else {
//						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//						list.add(items);
//						item.put("items", list);
//					}
//				} else {
//					Map<String, Object> item = new HashMap<String, Object>();
//
//					item.put("topClassID", menu.get("FTopClassID"));
//					item.put("name", menu.get("FTopClassName"));
//					item.put("icon", menu.get("FIcon"));
//
//					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//
//					Map<String, Object> items = new HashMap<String, Object>();
//					items.put("topClassID", menu.get("FTopClassID"));
//					items.put("subSysID", menu.get("FSubSysID"));
//					items.put("name", menu.get("FName"));
//					items.put("url", menu.get("FUrl"));
//					items.put("icon", menu.get("FIcon"));
//
//					list.add(items);
//					item.put("items", list);
//					result.add(item);
//				}
//			}
//		}

		return result;

	}

	@Override
	public User getUser(int userId) {

		UserMapper mapper = sqlSession.getMapper(UserMapper.class);

		UserExample example = new UserExample();
		com.kingdee.eas.hrp.sms.model.UserExample.Criteria criteria = example.createCriteria();

		criteria.andUserIdEqualTo(userId);

		List<User> list = mapper.selectByExample(example);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

}
