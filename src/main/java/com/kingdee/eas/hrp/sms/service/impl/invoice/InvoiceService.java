package com.kingdee.eas.hrp.sms.service.impl.invoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kingdee.eas.hrp.sms.dao.generate.InvoiceMapper;
import com.kingdee.eas.hrp.sms.model.Invoice;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.invoice.IInvoiceService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;
import com.kingdee.eas.hrp.sms.util.Environ;

@Service
public class InvoiceService extends BaseService implements IInvoiceService{

	@Override
	public Map<String, Object> print(String items) {
		
		
		ITemplateService templateService = Environ.getBean(ITemplateService.class);
		
		String[] split = items.split("\\,");
		
		ArrayList<Map<String, Object>> list = new ArrayList();
		
		Map<String, Object> entrys = new HashMap<>();
		
		Map<String, Object> ivoice = new HashMap<>();
		
		Map<String, Object> ret = new HashMap<>();
		
		for (int i = 0; i < split.length; i++) {

			Map<String, Object> purInvoice = templateService.getItemById(2020, split[i]);
			Map<String, Object> shipInvoice = (Map<String, Object>) purInvoice.get("entry");
			ArrayList<Object> arrayList = (ArrayList<Object>) shipInvoice.get("1");
			for (int j = 0; j < arrayList.size(); j++) {
				Map<String, Object> entry = new HashMap();
				HashMap<String, Object> invoiceEntry = (HashMap<String, Object>) arrayList.get(j);
				if(invoiceEntry.get("code")!=null&&!invoiceEntry.get("code").equals("")){
					entry.put("code",invoiceEntry.get("code"));
					entry.put("material_DspName", invoiceEntry.get("material_DspName"));
					entry.put("effectiveDate", invoiceEntry.get("effectiveDate"));
					entry.put("dyBatchNum", invoiceEntry.get("dyBatchNum"));
					entry.put("saleProxy_DspName", purInvoice.get("saleProxy_DspName"));
					list.add(entry);
				}
				   entrys.put("1", list);
				   ivoice.put("entry", entrys);
			}
			
		}
		
		return ivoice;
	}

 
}
