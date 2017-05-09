package com.kingdee.eas.hrp.sms.service.impl.invoice;

import org.springframework.stereotype.Service;

import com.kingdee.eas.hrp.sms.dao.generate.InvoiceMapper;
import com.kingdee.eas.hrp.sms.model.Invoice;
import com.kingdee.eas.hrp.sms.service.api.invoice.IInvoiceService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class InvoiceService extends BaseService implements IInvoiceService{

	@Override
	public void addInvoice(Invoice invoice) {
		InvoiceMapper invoiceMapper = sqlSession.getMapper(InvoiceMapper.class);
		invoiceMapper.insertSelective(invoice);
	}

}
