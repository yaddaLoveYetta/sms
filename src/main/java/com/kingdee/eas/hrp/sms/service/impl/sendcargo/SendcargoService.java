package com.kingdee.eas.hrp.sms.service.impl.sendcargo;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.message.SOAPHeaderElement;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.dao.customize.SendcargoDaoMapper;
import com.kingdee.eas.hrp.sms.dao.generate.SendcargoMapper;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.model.Sendcargo;
import com.kingdee.eas.hrp.sms.service.api.sendcargo.ISendcargoService;
import com.kingdee.eas.hrp.sms.service.api.sys.ISyncHRPService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class SendcargoService extends BaseService implements ISendcargoService {

	@Resource
	ISyncHRPService iSyncHRPService;

	@Override
	public List<Map<String, Object>> getCode(String items) {

		SendcargoDaoMapper sendcargoDaoMapper = sqlSession.getMapper(SendcargoDaoMapper.class);
		List<String> list = new ArrayList<String>();
		String[] split = items.split("\\,");
		for (int i = 0; i < split.length; i++) {
			String id = split[i];
			list.add(id);
		}

		List<Map<String, Object>> map = sendcargoDaoMapper.selectInvoiceById(list);
		if (map == null || map.size() == 0) {
			throw new BusinessLogicRunTimeException("发货单无个体码数据");
		}
		return map;

	}

	@Override
	public String sendCargoHrp(String items) {

		SendcargoDaoMapper sendcargoDaoMapper = sqlSession.getMapper(SendcargoDaoMapper.class);

		JSONArray lists = new JSONArray();

		String[] split = items.split("\\,");

		for (int i = 0; i < split.length; i++) {
			List<Map<String, Object>> map = sendcargoDaoMapper.selectSendCargoANDEntry(split[i]);
			JSONObject entry = new JSONObject();

			JSONObject json = new JSONObject();
			ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			if (null != map) {
				for (int j = 0; j < map.size(); j++) {

					JSONObject entrys = new JSONObject();

					Map<String, Object> sendCargo = map.get(j);

					if (j == 0) {
						// 表头
						json.put("id", sendCargo.get("id"));
						json.put("supplier", sendCargo.get("supplier"));
						json.put("number", sendCargo.get("number"));
					}
					entrys.put("entryId", sendCargo.get("entryId"));
					entrys.put("seq", sendCargo.get("seq"));
					entrys.put("purOrder", sendCargo.get("orderId"));
					entrys.put("purOrderEntry", sendCargo.get("orderEntryId"));
					entrys.put("material", sendCargo.get("material"));
					entrys.put("lot", sendCargo.get("lot"));
					entrys.put("plotNum", sendCargo.get("dyBatchNum"));
					entrys.put("innerCode", sendCargo.get("code"));
					entrys.put("dyPrice", sendCargo.get("price"));
					entrys.put("unit", sendCargo.get("unit"));
					entrys.put("qty", sendCargo.get("qty"));
					entrys.put("dyProDate", sendCargo.get("dyProDate"));
					entrys.put("dyProduct", sendCargo.get("dyManufacturer"));
					entrys.put("dyRegistNum", sendCargo.get("registrationNo"));
					entrys.put("dyEffDate", sendCargo.get("effectiveDate"));
					entrys.put("dyamount", sendCargo.get("amount"));
					list.add(entrys);
				}

				entry.put("1", list);
				json.put("entry", entry);
				lists.add(json);
			}
		}
		String response = "";
		try {
			org.apache.axis.client.Service sv = new org.apache.axis.client.Service();
			Call call = (Call) sv.createCall();
			call.setUseSOAPAction(true);
			call.setTargetEndpointAddress(new URL("http://10.0.1.37:56898/ormrpc/services/WSDataSynWSFacade?wsdl"));
			call.setOperationName(
					new QName("http://10.0.1.37:56898/ormrpc/services/WSDataSynWSFacade", "sms2hrpSendCargo"));
			String headerNamespace = "http://login.webservice.bos.kingdee.com";
			call.setUseSOAPAction(true);
			call.addParameter("json", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);// 设置参数名,第二个参数表示String类型,第三个参数表示入参
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setTimeout(60000);// 设置超时时间
			// 返回参数类型
			call.setReturnClass(String.class);
			// // 由于需要认证，需要设置sessionId
			SOAPHeaderElement soapHeaderElement = new SOAPHeaderElement(headerNamespace, "SessionId");
			soapHeaderElement.setValue(iSyncHRPService.loginInEAS());
			call.addHeader(soapHeaderElement);
			JSONObject json = new JSONObject();
			response = (String) call.invoke(new Object[] { lists.toString() });
			System.out.println(response);// 打印字符串

		} catch (RemoteException | ServiceException | MalformedURLException e) {
			e.printStackTrace();
		}
		JSONObject rps = JSONObject.parseObject(response);
		if(rps.get("code").equals("200")){
			SendcargoMapper sendcargoMapper = sqlSession.getMapper(SendcargoMapper.class);
			Sendcargo sendcargo = new Sendcargo();
			for (int i = 0; i < split.length; i++) {
				sendcargo.setId(split[i]);
				sendcargo.setType(Byte.parseByte("1"));
				sendcargoMapper.updateByPrimaryKeySelective(sendcargo);
			}
		}

		return "success";
	}
}
