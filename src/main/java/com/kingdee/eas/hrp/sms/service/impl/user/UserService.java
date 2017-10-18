package com.kingdee.eas.hrp.sms.service.impl.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.ExtendedSSLSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingdee.eas.hrp.sms.dao.customize.SysDaoMapper;
import com.kingdee.eas.hrp.sms.dao.generate.UserMapper;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.model.Role;
import com.kingdee.eas.hrp.sms.model.User;
import com.kingdee.eas.hrp.sms.model.UserExample;
import com.kingdee.eas.hrp.sms.model.UserExample.Criteria;
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
		//System.out.println(pageInfo.getOrderBy());
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getSysMenu(String type, String userId) {

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		IRoleService roleService = Environ.getBean(IRoleService.class);

		// 获取用户角色
		Role role = roleService.getRole(getUser(userId).getRole());
		if (null == role) {
			throw new BusinessLogicRunTimeException("用户角色错误，请先给该用户绑定角色！");
		}

		String roleId = role.getRoleId();

		// 获取该角色所有的权限
		Map<String, Object> accessMap = roleService.getAccessByRole(roleId);

		// 根据用户类别获取所有系统菜单
		// 平台用户-t_DataFlowSubSystem中配置为1,供应链用户-t_DataFlowSubSystem中配置为2

		SysDaoMapper mapper = sqlSession.getMapper(SysDaoMapper.class);

		int roleControl = 3;// 两者都可用
		if (role.getType().equals("Ro9iCuOsVEmznmE+YZSi7hAEEAQ=")) {
			// 系统角色
			roleControl = 1;
		} else if (role.getType().equals("f1sGInqJq0aUNY5MmpKM8RAEEAQ=")) {
			// 供应商角色
			roleControl = 2;
		}
		List<Map<String, Object>> menuList = mapper.getSysMenu(roleControl);

		// 根据用户权限过滤菜单
		for (Map<String, Object> menu : menuList) {
			String topClassId = menu.get("topClassId").toString();
			String objectType = menu.get("objectType").toString(); // 菜单对应的objectType - 即菜单要验证的权限类型
			String objectId = menu.get("objectId").toString(); // 菜单对应的objectID- 即菜单要验证的权限明细
			int accessMask = Integer.parseInt(menu.get("accessMask").toString()); // 系统定义的菜单项权限

			String subSysAccess = objectType + "-" + objectId;

			// 用户对subSysAccess的权限
			int userAccess = 0;
			if (accessMap.containsKey(subSysAccess)) {
				userAccess = Integer.parseInt(accessMap.get(subSysAccess).toString());
			}
			// userAccess > 0 处理没有配置子系统权限的情况 ，accessMask > 0 处理没有配置index=0进入页面权限的情况. 0 & any==0
			if (userAccess > 0 && accessMask > 0 && (userAccess & accessMask) == accessMask) {
				// 有权限
				int index = contains(result, topClassId);
				if (index >= 0) {
					// 已存在顶级菜单
					Map<String, Object> item = result.get(index);
					Map<String, Object> items = new HashMap<String, Object>();
					items.put("topClassId", menu.get("topClassId"));
					items.put("subSysId", menu.get("subSysId"));
					items.put("name", menu.get("name"));
					items.put("url", menu.get("url"));
					items.put("icon", menu.get("subIcon"));

					if (item.containsKey("items")) {
						((List<Map<String, Object>>) item.get("items")).add(items);
					} else {
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						list.add(items);
						item.put("items", list);
					}
				} else {
					Map<String, Object> item = new HashMap<String, Object>();

					item.put("topClassId", menu.get("topClassId"));
					item.put("name", menu.get("topClassName"));
					item.put("icon", menu.get("icon"));

					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

					Map<String, Object> items = new HashMap<String, Object>();
					items.put("topClassId", menu.get("topClassId"));
					items.put("subSysId", menu.get("subSysId"));
					items.put("name", menu.get("name"));
					items.put("url", menu.get("url"));
					items.put("icon", menu.get("subIcon"));

					list.add(items);
					item.put("items", list);
					result.add(item);
				}
			}
		}

		return result;

	}

	@Override
	public User getUser(String userId) {

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

	/**
	 * 判断list中是否包含指定key的Map子项
	 * 
	 * @param list
	 * @param id
	 * @return
	 */
	private int contains(List<Map<String, Object>> list, String id) {

		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			if (map.get("topClassId").toString().equalsIgnoreCase(id)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	@Transactional
	public boolean editPwd(String userId, String oldpwd, String newpwd) {

		User user = null;

		UserMapper mapper = sqlSession.getMapper(UserMapper.class);

		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andPasswordEqualTo(oldpwd);
		criteria.andUserIdEqualTo(userId);

		List<User> list = mapper.selectByExample(example);

		if (!list.isEmpty()) {
			user = list.get(0);
		}

		if (null == user){
			throw new BusinessLogicRunTimeException("原密码错误");}

		if (user.getPassword().equals(newpwd)){
			throw new BusinessLogicRunTimeException("新密码不可与原密码相同");}

		user.setPassword(newpwd);

		mapper.updateByPrimaryKey(user);

		return true;
	}

	
	@Override
	public User getUserByToken(String token) {
		
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);

		UserExample example = new UserExample();
		com.kingdee.eas.hrp.sms.model.UserExample.Criteria criteria = example.createCriteria();

		criteria.andTokenEqualTo(token);

		List<User> list = mapper.selectByExample(example);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

}
