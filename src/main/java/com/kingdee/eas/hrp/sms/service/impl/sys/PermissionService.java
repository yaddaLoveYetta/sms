package com.kingdee.eas.hrp.sms.service.impl.sys;

import org.springframework.stereotype.Service;

import com.kingdee.eas.hrp.sms.service.api.sys.IPermissionService;

@Service
public class PermissionService implements IPermissionService {

	@Override
	public boolean checkPermissionByUserId(String userId, int objectType, int objectId, int accessMask) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkPermissionByRole(int roleId, int objectType, int objectId, int accessMask) {
		// TODO Auto-generated method stub
		return false;
	}

}
