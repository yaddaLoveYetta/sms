package com.kingdee.eas.hrp.sms.service.order;



import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.kingdee.eas.hrp.sms.BaseJunitTest;
import com.kingdee.eas.hrp.sms.service.api.order.IOrderService;
import com.kingdee.eas.hrp.sms.util.Environ;

public class OrderServiceTest extends BaseJunitTest {
	@Test
	public void orderTest() {
		String ss = "[{'saleProxy': 2,'settlementType': 'e09a62cd-00fd-1000-e000-0b32c0a8100dE96B2B8E','isQuicken': 0,'bizDate': 1480867200000,'paymentType': '2fa35444-5a23-43fb-99ee-6d4fa5f260da6BCA0AB5','totalTax': 0,'number': 'PO-201612-11270','totalAmount': 300,'entries': [{'actualTaxPrice': 30,'discountRate': 0,'parent': '5DwH8qtqSgi57JS4URzj4zFxv60=','discountAmount': 0,'tax': 0,'taxRate': 0,'unit': 'nWKvsM31R4SmscGMTKKayVuCXFc=','material': 'yImt5r7HRT++avkb6soMmkQJ5/A=','price': 30,'qty': 10,'taxPrice': 30,'id': '7jgkY8b3SkSIrZllHnGFpiYEHMU=','deliveryDate': 1480867200000,'seq': 1,'localAmount': 300}],'createTime': 1480905589345,'supplier': 'UtHfWE4qS8e7QPK1zKA/mjfGffw=','currency': 'dfd38d11-00fd-1000-e000-1ebdc0a8100dDEB58FDC','id': 'i3un0apzTfCxfqHvpONGpjFxv60=','totalTaxAmount': 300,'isInTax': 1}],[{'saleProxy': 2,'settlementType': 'e09a62cd-00fd-1000-e000-0b32c0a8100dE96B2B8E','isQuicken': 0,'bizDate': 1480867200000,'paymentType': '2fa35444-5a23-43fb-99ee-6d4fa5f260da6BCA0AB5','totalTax': 0,'number': 'PO-201612-11270','totalAmount': 300,'entries': [{'actualTaxPrice': 30,'discountRate': 0,'parent': '5DwH8qtqSgi57JS4URzj4zFxv60=','discountAmount': 0,'tax': 0,'taxRate': 0,'unit': 'nWKvsM31R4SmscGMTKKayVuCXFc=','material': 'yImt5r7HRT++avkb6soMmkQJ5/A=','price': 30,'qty': 10,'taxPrice': 30,'id': '/YUvWdfQStmN5pvMGY4W/yYEHMU=','deliveryDate': 1480867200000,'seq': 1,'localAmount': 300}],'createTime': 1480905589345,'supplier': 'UtHfWE4qS8e7QPK1zKA/mjfGffw=','currency': 'dfd38d11-00fd-1000-e000-1ebdc0a8100dDEB58FDC','id': '5DwH8qtqSgi57JS4URzj4zFxv60=','totalTaxAmount': 300,'isInTax': 1}]";
		IOrderService mapper = Environ.getBean(IOrderService.class);
		JSONArray json = JSONArray.parseArray(ss);
		mapper.order(json);
		
	}
	
	/*@Test
	public void updateOrderTest(){
		IOrderService mapper = Environ.getBean(IOrderService.class);
		SimpleDateFormat sft = new SimpleDateFormat('yyyyMMddHHmmss');
		Order order = new Order();
		order.setId('2');
		try {
			order.setCutasingleTime(sft.parse('20170508112730'));
			order.setConfirmDeliveryTime(sft.parse('20170508112730'));
			order.setConfirmDeliveryNumbers(123);
			order.setConfirmOrder(456);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		mapper.updateOrderTime(order);
	}*/
	
	public static void main(String[] args) {
		int a = 1010;
		System.out.println(a/100);
	}

}