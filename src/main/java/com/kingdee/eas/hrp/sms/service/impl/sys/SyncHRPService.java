package com.kingdee.eas.hrp.sms.service.impl.sys;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.kingdee.eas.hrp.sms.log.ServiceLog;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.sys.ISyncHRPService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;
import com.kingdee.eas.hrp.sms.util.WSContext;

@Service
public class SyncHRPService extends BaseService implements ISyncHRPService {

	@Resource
	ITemplateService templateService;

	@ServiceLog(desc = "同步item到HRP")
	@Override
	public String sendItem(int classId, String data, String userType) {

		StringBuilder result = new StringBuilder("");
		String[] idString = data.split(",");
		List<String> idList = new ArrayList<String>(Arrays.asList(idString));
		List<String> idTargetList = new ArrayList<String>();
		JSONArray targetList = new JSONArray();

		for (int i = 0; i < idList.size(); i++) {
			Map<String, Object> item = templateService.getItemById(classId, idList.get(i), userType);
			String id = (String) item.get("id");
			if (idList.contains(id) && ("0".equals(item.get("syncStatus")) || item.get("syncStatus") == null)
					|| item.get("syncStatus").equals("")) {
				idTargetList.add(id);
				JSONObject targetItem = (JSONObject) JSONObject.toJSON(item);
				targetList.add(targetItem);
				System.out.println(targetItem.toJSONString());
				System.out.println(targetList.toString());
			}

		}

		String sessionId = loginInEAS();
		String failId = sendItemByWS(sessionId, targetList.toString(), "sms2hrpSupplier");
		String[] failIdStr = failId.split(",");
		List<String> failIdList = new ArrayList<String>(Arrays.asList(failIdStr));

		for (String id : idTargetList) {
			try {
				if (failIdList.size() != 0)
					if (failIdList.contains(id))
						continue;
				templateService.editItem(classId, id, "{}", userType);
			} catch (Exception e) {
				failIdList.add(id);
			}
		}
		for (String id : failIdList) {
			Map<String, Object> failData = templateService.getItemById(classId, id, userType);
			result.append((String) failData.get("number")).append("\n");
		}
		return result.toString();
	}

	private String sendItemByWS(String sessionId, String data, String method) {

		String response = "";
		try {
			URL url = new URL("http://10.0.1.37:56898/ormrpc/services/WSDataSynWSFacade?wsdl");
			String nameSpace = "http://10.0.1.37:56898/ormrpc/services/WSDataSynWSFacade";
			String headerNamespace = "http://login.webservice.bos.kingdee.com";
			// String method = "sms2hrpSupplier";

			org.apache.axis.client.Service sv = new org.apache.axis.client.Service();
			Call call = (Call) sv.createCall();
			call.setUseSOAPAction(true);
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName(nameSpace, method)); // 设置要调用的接口方法
			call.addParameter("supplier", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);// 设置参数名,第二个参数表示String类型,第三个参数表示入参

			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//
			// 返回参数类型
			call.setReturnClass(String.class);

			// // 由于需要认证，需要设置sessionId
			SOAPHeaderElement soapHeaderElement = new SOAPHeaderElement(headerNamespace, "SessionId");
			soapHeaderElement.setValue(sessionId);
			call.addHeader(soapHeaderElement);

			response = (String) call.invoke(new Object[] { data });
			System.out.println(response);// 打印字符串

		} catch (RemoteException | ServiceException | MalformedURLException e) {
			e.printStackTrace();
		}

		return response;

	}

	public String loginInEAS() {

		String sessionId = "";
		try {
			org.apache.axis.client.Service sv = new org.apache.axis.client.Service();
			Call call = (Call) sv.createCall();
			call.setUseSOAPAction(true);
			call.setTargetEndpointAddress(new URL("http://10.0.1.37:56898/ormrpc/services/EASLogin?wsdl")); // 设置要调用的接口地址以上一篇的为例子
			call.setOperationName(new QName("http://10.0.1.37:56898/ormrpc/services/EASLogin", "login")); // 设置要调用的接口方法
			call.addParameter("userName", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);// 设置参数名,第二个参数表示String类型,第三个参数表示入参
			call.addParameter("password", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
			call.addParameter("slnName", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
			call.addParameter("dcName", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
			call.addParameter("language", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
			call.addParameter("dbType", org.apache.axis.encoding.XMLType.XSD_INT, javax.xml.rpc.ParameterMode.IN);
			call.addParameter("authPattern", org.apache.axis.encoding.XMLType.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);

			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_ANYTYPE);//
			// 返回参数类型
			call.setReturnClass(WSContext.class);
			WSContext wsContext = (WSContext) call
					.invoke(new Object[] { "user", "kduser100", "eas", "gshrp", "L2", 1, "BaseDB" });
			System.out.println(wsContext);// 打印字符串
			sessionId = wsContext.getSessionId();

		} catch (RemoteException | ServiceException | MalformedURLException e) {
			e.printStackTrace();
		}
		return sessionId;
	}

	@Override
	public String delItem(int classId, String data, String userType) {

		StringBuilder result = new StringBuilder("");
		String[] idString = data.split(",");
		List<String> idList = new ArrayList<String>(Arrays.asList(idString));

		String sessionId = loginInEAS();
		String failId = sendItemByWS(sessionId, data, "sms2hrpSupplier");
		String[] failIdStr = failId.split(",");
		List<String> failIdList = new ArrayList<String>(Arrays.asList(failIdStr));

		for (String id : idList) {
			try {
				if (failIdList.size() != 0)
					if (failIdList.contains(id))
						continue;
				templateService.delItem(classId, id, userType);
			} catch (Exception e) {
				failIdList.add(id);
			}
		}
		for (String id : failIdList) {
			Map<String, Object> failData = templateService.getItemById(classId, id, userType);
			result.append((String) failData.get("number")).append("\n");
		}
		return result.toString();
	}

}
