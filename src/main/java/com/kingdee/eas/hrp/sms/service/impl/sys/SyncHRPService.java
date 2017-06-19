package com.kingdee.eas.hrp.sms.service.impl.sys;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.message.SOAPHeaderElement;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kingdee.eas.hrp.sms.dao.generate.SysProfileMapper;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.log.ServiceLog;
import com.kingdee.eas.hrp.sms.model.SysProfile;
import com.kingdee.eas.hrp.sms.model.SysProfileExample;
import com.kingdee.eas.hrp.sms.model.SysProfileExample.Criteria;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.sys.ISyncHRPService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;
import com.kingdee.eas.hrp.sms.util.MsgUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;
import com.kingdee.eas.hrp.sms.util.SystemParamUtil;
import com.kingdee.eas.hrp.sms.util.WSContext;

@Service
public class SyncHRPService extends BaseService implements ISyncHRPService {

	@Resource
	ITemplateService templateService;

	@ServiceLog(desc = "同步item到HRP")
	@Override
	public String sendItem(int classId, String data) {

		StringBuilder result = new StringBuilder("");
		String[] idString = data.split(",");
		List<String> idList = new ArrayList<String>(Arrays.asList(idString));
		List<String> idTargetList = new ArrayList<String>();
		JSONArray targetList = new JSONArray();
		StringBuilder msgToSupplier = new StringBuilder("");
		Map<String, Object> successSupplier = new HashMap<String, Object>();

		for (int i = 0; i < idList.size(); i++) {
			Map<String, Object> item = templateService.getItemById(classId, idList.get(i));
			String id = (String) item.get("id");
			if (0 == (short) item.get("syncStatus") || null == item.get("syncStatus")
					|| "".equals(item.get("syncStatus"))) {
				idTargetList.add(id);
				String supplierId = (String) item.get("supplier");
				Map<String, Object> supplier = templateService.getItemById(1005, supplierId);
				successSupplier.put(id, supplier.get("name"));
				String targetItem = JSON.toJSONString(item, SerializerFeature.WriteMapNullValue);
				JSONObject targetJson = JSONObject.parseObject(targetItem);
				System.out.println(targetItem);

				targetList.add(targetJson);
			}
		}

		if (idTargetList.size() == 0) {
			throw new RuntimeException("没有可同步项！");
		}

		// 拼接classId和list
		JSONObject jsonData = new JSONObject(true);
		jsonData.put("classId", classId);
		jsonData.put("list", targetList.toString());

		String sessionId = loginInEAS();
		if (null == sessionId || "".equals(sessionId)) {
			throw new RuntimeException("连接医院服务器异常！");
		}
		String ret = syncItemByWS(sessionId, jsonData.toString(), "sms2hrpBaseData");
		if (null == ret || "".equals(ret)) {
			throw new RuntimeException("连接医院服务器异常！");
		}
		JSONObject jsonRet = JSONObject.parseObject(ret);
		// HRP验证到一个错误就都不同步
		if (StatusCode.BUSINESS_LOGIC_ERROR == jsonRet.getIntValue("code")) {
			throw new BusinessLogicRunTimeException(jsonRet.getString("msg"));
		}

		List<String> failIdList = new ArrayList<String>();
		for (String id : idTargetList) {
			try {
				templateService.editItem(classId, id, "{}");
			} catch (Exception e) {
				failIdList.add(id);
				successSupplier.remove(id);
			}
		}

		System.out.println(targetList.toString());
		// 获取同步发送电话
		String mobie = SystemParamUtil.getString("sys", "hrp-sync-mobie", "");
		if (!mobie.equals("")) {
			for (Object key : successSupplier.keySet()) {
				msgToSupplier.append(successSupplier.get(key)).append(",");
			}
			msgToSupplier.deleteCharAt(msgToSupplier.length() - 1);
			msgToSupplier.append("的资料已同步，请及时查看！");
			MsgUtil.sendSMS(new String[] { mobie }, msgToSupplier.toString());
		}

		if (failIdList.isEmpty()) {
			return "";
		}

		result.append("代码：");
		for (String id : failIdList) {
			Map<String, Object> failData = templateService.getItemById(classId, id);
			result.append((String) failData.get("number")).append("，");
		}
		result.append("设置同步状态失败");
		return result.toString();
	}

	@Override
	public String syncItemByWS(String sessionId, String data, String method) {

		String response = "";
		SysProfileMapper mapper = sqlSession.getMapper(SysProfileMapper.class);
		SysProfileExample example = new SysProfileExample();
		Criteria criteria = example.createCriteria();
		criteria.andKeyLike("%hrp-sync-%");
		List<SysProfile> selectByExample = mapper.selectByExample(example);
		String urlStr = "";
		String nameSpace = "";
		String headerNamespace = "";
		int i = 0;
		for (SysProfile sysProfile : selectByExample) {
			String key = sysProfile.getKey();
			switch (key) {
			case "hrp-sync-url":
				if (null == sysProfile.getValue() || "".equals(sysProfile.getValue())) {
					throw new BusinessLogicRunTimeException("同步到HRP系统参数不能设置为空");
				}
				urlStr = sysProfile.getValue();
				i++;
				break;
			case "hrp-sync-namespace":
				if (null == sysProfile.getValue() || "".equals(sysProfile.getValue())) {
					throw new BusinessLogicRunTimeException("同步到HRP系统参数不能设置为空");
				}
				nameSpace = sysProfile.getValue();
				i++;
				break;
			case "hrp-sync-header-namespace":
				if (null == sysProfile.getValue() || "".equals(sysProfile.getValue())) {
					throw new BusinessLogicRunTimeException("同步到HRP系统参数不能设置为空");
				}
				headerNamespace = sysProfile.getValue();
				i++;
				break;

			default:
				break;
			}
		}
		if (i != 3) {
			throw new BusinessLogicRunTimeException("系统参数设置有误，请核实！");
		}
		try {
			URL url = new URL(urlStr);

			org.apache.axis.client.Service sv = new org.apache.axis.client.Service();
			Call call = (Call) sv.createCall();
			call.setUseSOAPAction(true);
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName(nameSpace, method)); // 设置要调用的接口方法
			call.addParameter("strJson", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);// 设置参数名,第二个参数表示String类型,第三个参数表示入参

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

	@Override
	public String loginInEAS() {

		String sessionId = "";

		SysProfileMapper mapper = sqlSession.getMapper(SysProfileMapper.class);
		SysProfileExample example = new SysProfileExample();
		Criteria criteria = example.createCriteria();
		criteria.andKeyLike("%hrp-login-%");
		List<SysProfile> selectByExample = mapper.selectByExample(example);
		String urlStr = "";
		String nameSpace = "";
		String userName = "";
		String password = "";
		String slnName = "";
		String dcName = "";
		String language = "";
		int dbType = -1;
		String authPattern = "";
		int i = 0;
		for (SysProfile sysProfile : selectByExample) {
			String key = sysProfile.getKey();
			switch (key) {
			case "hrp-login-url":
				if (null == sysProfile.getValue() || "".equals(sysProfile.getValue())) {
					throw new BusinessLogicRunTimeException("同步到HRP系统参数不能设置为空");
				}
				urlStr = sysProfile.getValue();
				i++;
				break;
			case "hrp-login-namespace":
				if (null == sysProfile.getValue() || "".equals(sysProfile.getValue())) {
					throw new BusinessLogicRunTimeException("同步到HRP系统参数不能设置为空");
				}
				nameSpace = sysProfile.getValue();
				i++;
				break;
			case "hrp-login-userName":
				if (null == sysProfile.getValue() || "".equals(sysProfile.getValue())) {
					throw new BusinessLogicRunTimeException("同步到HRP系统参数不能设置为空");
				}
				userName = sysProfile.getValue();
				i++;
				break;
			case "hrp-login-psw":
				if (null == sysProfile.getValue() || "".equals(sysProfile.getValue())) {
					throw new BusinessLogicRunTimeException("同步到HRP系统参数不能设置为空");
				}
				password = sysProfile.getValue();
				i++;
				break;
			case "hrp-login-slnName":
				if (null == sysProfile.getValue() || "".equals(sysProfile.getValue())) {
					throw new BusinessLogicRunTimeException("同步到HRP系统参数不能设置为空");
				}
				slnName = sysProfile.getValue();
				i++;
				break;
			case "hrp-login-dcName":
				if (null == sysProfile.getValue() || "".equals(sysProfile.getValue())) {
					throw new BusinessLogicRunTimeException("同步到HRP系统参数不能设置为空");
				}
				dcName = sysProfile.getValue();
				i++;
				break;
			case "hrp-login-language":
				if (null == sysProfile.getValue() || "".equals(sysProfile.getValue())) {
					throw new BusinessLogicRunTimeException("同步到HRP系统参数不能设置为空");
				}
				language = sysProfile.getValue();
				i++;
				break;
			case "hrp-login-dbType":
				if (null == sysProfile.getValue() || "".equals(sysProfile.getValue())) {
					throw new BusinessLogicRunTimeException("同步到HRP系统参数不能设置为空");
				}
				dbType = Integer.parseInt(sysProfile.getValue());
				i++;
				break;
			case "hrp-login-authPattern":
				if (null == sysProfile.getValue() || "".equals(sysProfile.getValue())) {
					throw new BusinessLogicRunTimeException("同步到HRP系统参数不能设置为空");
				}
				authPattern = sysProfile.getValue();
				i++;
				break;

			default:
				break;
			}
		}
		if (i != 9) {
			throw new BusinessLogicRunTimeException("系统参数设置有误，请核实！");
		}
		try {
			org.apache.axis.client.Service sv = new org.apache.axis.client.Service();
			Call call = (Call) sv.createCall();
			call.setUseSOAPAction(true);
			call.setTargetEndpointAddress(new URL(urlStr)); // 设置要调用的接口地址以上一篇的为例子
			call.setOperationName(new QName(nameSpace, "login")); // 设置要调用的接口方法
			call.addParameter("userName", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);// 设置参数名,第二个参数表示String类型,第三个参数表示入参
			call.addParameter("password", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
			call.addParameter("slnName", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
			call.addParameter("dcName", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
			call.addParameter("language", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
			call.addParameter("dbType", org.apache.axis.encoding.XMLType.XSD_INT, javax.xml.rpc.ParameterMode.IN);
			call.addParameter("authPattern", org.apache.axis.encoding.XMLType.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);

			call.setTimeout(20000);// 超时时间为20s
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_ANYTYPE);//
			// 返回参数类型
			call.setReturnClass(WSContext.class);
			WSContext wsContext = (WSContext) call
					.invoke(new Object[] { userName, password, slnName, dcName, language, dbType, authPattern });
			System.out.println(wsContext);// 打印字符串
			sessionId = wsContext.getSessionId();

		} catch (RemoteException | ServiceException | MalformedURLException e) {
			e.printStackTrace();
		}
		return sessionId;
	}

}
