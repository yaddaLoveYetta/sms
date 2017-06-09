package com.kingdee.eas.hrp.sms.service.impl.supplier;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kingdee.eas.hrp.sms.service.api.supplier.IFileUploadService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class FileUploadService extends BaseService implements IFileUploadService {

	@Override
	public void saveUrlToDb(int classId, String itemId, List<String> url) {
		// TODO Auto-generated method stub

	}

}
