package com.kingdee.eas.hrp.sms.service.impl.sys;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kingdee.eas.hrp.sms.dao.generate.AccessControlMapper;
import com.kingdee.eas.hrp.sms.dao.generate.ObjectTypeMapper;
import com.kingdee.eas.hrp.sms.model.AccessControl;
import com.kingdee.eas.hrp.sms.model.AccessControlExample;
import com.kingdee.eas.hrp.sms.model.ObjectType;
import com.kingdee.eas.hrp.sms.model.ObjectTypeExample;
import com.kingdee.eas.hrp.sms.model.ObjectTypeExample.Criteria;
import com.kingdee.eas.hrp.sms.service.api.sys.IPermissionService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class PermissionService extends BaseService implements IPermissionService {

	@Override
	public boolean checkPermissionByUserId(String userId, int objectType, int objectId, int accessMask) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean checkPermissionByRole(String roleId, int objectType, int objectId, int accessMask) {

		int mask = getAccessMask(objectType, objectId, roleId);

		if ((mask & accessMask) == accessMask) {
			// 有权限
			return true;
		}

		return false;
	}

	private int getAccessMask(int objectType, int objectId, String roleId) {

		AccessControlMapper mapper = sqlSession.getMapper(AccessControlMapper.class);

		AccessControlExample example = new AccessControlExample();
		com.kingdee.eas.hrp.sms.model.AccessControlExample.Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		criteria.andObjectTypeEqualTo(objectType);
		criteria.andObjectIdEqualTo(objectId);

		List<AccessControl> selectByExample = mapper.selectByExample(example);

		if (null != selectByExample && selectByExample.size() > 0) {
			AccessControl accessControl = selectByExample.get(0);
			return accessControl.getAccessMask();
		}

		return 0;
	}

	@Override
	public ObjectType getAccessType(int classId) {

		ObjectTypeMapper mapper = sqlSession.getMapper(ObjectTypeMapper.class);
		ObjectTypeExample example = new ObjectTypeExample();
		Criteria criteria = example.createCriteria();

		criteria.andClassIdEqualTo(classId);

		List<ObjectType> selectByExample = mapper.selectByExample(example);

		if (null != selectByExample && !selectByExample.isEmpty()) {
			return selectByExample.get(0);
		}

		return null;
	}

}
