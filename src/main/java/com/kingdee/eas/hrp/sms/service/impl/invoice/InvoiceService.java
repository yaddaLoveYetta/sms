package com.kingdee.eas.hrp.sms.service.impl.invoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kingdee.eas.hrp.sms.dao.customize.InvoiceDaoMapper;
import com.kingdee.eas.hrp.sms.dao.generate.InvoiceMapper;
import com.kingdee.eas.hrp.sms.model.Invoice;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.invoice.IInvoiceService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;
import com.kingdee.eas.hrp.sms.util.Environ;

@Service
public class InvoiceService extends BaseService implements IInvoiceService{

	@Override
	public List<Map<String, Object>> getCode(String items) {
		
		InvoiceDaoMapper invoiceDaoMapper = sqlSession.getMapper(InvoiceDaoMapper.class);
		List list = new ArrayList();
		String[] split = items.split("\\,");
		for (int i = 0; i < split.length; i++) {
			String id = split[i];
			list.add(id);
		}
		
			List<Map<String, Object>> map = invoiceDaoMapper.selectInvoiceById(list);
			return map;
		
		
	} 
}
