package com.kingdee.eas.hrp.sms.controller;


import org.junit.Test;

import com.kingdee.eas.hrp.sms.util.http.HttpParam;
import com.kingdee.eas.hrp.sms.util.http.HttpUtil;

public class OrderControllerTest extends BaseControllerTest {


    @Test
    public void invoice() {
        HttpParam param = HttpParam.init();
        param.setCookieParams(cookie);
        String str = "5DwH8qtqSgi57JS4URzj4zFxv60=";
        param.addCommon("size", "2");
        param.addCommon("list", str);

        String ret = HttpUtil.sendGet(BASE_URL + "order/invoice", param);
        System.out.println(ret);
    }

    @Test
    public void syncOrder() {

        HttpParam param = HttpParam.init();
        param.setCookieParams(cookie);

        String list = "[{'baseStatus':4,'bizDate':1514822400000,'createTime':1514875185674,'currency':'dfd38d11-00fd-1000-e000-1ebdc0a8100dDEB58FDC','entries':[{'actualTaxPrice':7.60000000,'amount':76.0000,'deliveryDate':1514875161657,'discountAmount':0.0000,'discountRate':0E-8,'id':'axX1SDsVTRas5ufe5h5OESYEHMU=','localAmount':76.0000,'material':'8a97N4VHRSK7aHJYI9yGtEQJ5/A=','parent':'4E4CClQjQ4SO6iaK1mDiDzFxv60=','price':7.60000000,'qty':10.00000000,'seq':1,'tax':0.0000,'taxPrice':7.60000000,'taxRate':0E-8,'unit':'CojHwrrRQOGyqSOna54dWVuCXFc='}],'id':'4E4CClQjQ4SO6iaK1mDiDzFxv60=','isInTax':1,'isQuicken':0,'number':'PO2018000003','saleProxy':1,'supplier':'rqD5GB2CR9C1eDl2YsOfuDfGffw=','totalAmount':76.0000,'totalTax':0.0000,'totalTaxAmount':76.0000}]";

        param.addCommon("list", list);

        String ret = HttpUtil.sendPost(BASE_URL + "order/acquisitionOrder", param);
        System.out.println(ret);

    }
}
