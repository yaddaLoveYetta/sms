package com.kingdee.eas.hrp.sms.service.impl.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

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

}
