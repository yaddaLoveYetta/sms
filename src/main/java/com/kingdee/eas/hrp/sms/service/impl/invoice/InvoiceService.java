package com.kingdee.eas.hrp.sms.service.impl.invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kingdee.eas.hrp.sms.dao.customize.InvoiceDaoMapper;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.service.api.invoice.IInvoiceService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

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
			if(map==null || map.size()==0){
				throw new BusinessLogicRunTimeException("发货单无个体码数据");
			}
			return map;
		
		
	} 
}
