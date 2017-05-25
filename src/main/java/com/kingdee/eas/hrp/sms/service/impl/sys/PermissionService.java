package com.kingdee.eas.hrp.sms.service.impl.sys;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kingdee.eas.hrp.sms.dao.generate.AccessControlMapper;
import com.kingdee.eas.hrp.sms.exception.PermissionDeniedRuntimeTimeException;
import com.kingdee.eas.hrp.sms.model.AccessControl;
import com.kingdee.eas.hrp.sms.model.AccessControlExample;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
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
	public boolean checkPermissionByRole(Integer classId, String roleId, int objectType, int objectId, int accessMask) {

		int ac = getAccessMask(classId, roleId);
		if ((ac & accessMask) == accessMask)
			return true;
		return true;
	}

	public int getAccessMask(Integer classId, String roleId) {

		int accessMask;
		AccessControlMapper mapper = sqlSession.getMapper(AccessControlMapper.class);

		AccessControlExample example = new AccessControlExample();
		com.kingdee.eas.hrp.sms.model.AccessControlExample.Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);

		List<AccessControl> selectByExample = mapper.selectByExample(example);
		if (selectByExample.size() > 0) {
			AccessControl accessControl = selectByExample.get(0);
			accessMask = accessControl.getAccessMask();
		} else {
			throw new PermissionDeniedRuntimeTimeException("该业务还没分配权限，请联系管理员!");
		}
		return accessMask;
	}

}
