package com.kingdee.eas.hrp.sms.service.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.message.SOAPHeaderElement;
import org.springframework.stereotype.Service;

import com.kingdee.eas.hrp.sms.service.api.IWebService;
import com.kingdee.eas.hrp.sms.util.SystemParamUtil;
import com.kingdee.eas.hrp.sms.util.WSContext;

@Service
public class WebService extends BaseService implements IWebService {

	public String webService(String json, String name) {
		String response = "";
		try {
			org.apache.axis.client.Service sv = new org.apache.axis.client.Service();
			Call call = (Call) sv.createCall();
			call.setUseSOAPAction(true);
			call.setTargetEndpointAddress(new URL(SystemParamUtil.getString("sys", "hrp-sync-url")));
			call.setOperationName(new QName(SystemParamUtil.getString("sys", "hrp-sync-namespace"), name));
			call.setUseSOAPAction(true);
			call.addParameter("json", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);// 设置参数名,第二个参数表示String类型,第三个参数表示入参
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setTimeout(60000);// 设置超时时间
			// 返回参数类型
			call.setReturnClass(String.class);
			// // 由于需要认证，需要设置sessionId
			SOAPHeaderElement soapHeaderElement = new SOAPHeaderElement(
					SystemParamUtil.getString("sys", "hrp-sync-header-namespace"), "SessionId");
			soapHeaderElement.setValue(loginInEAS());
			call.addHeader(soapHeaderElement);
			response = (String) call.invoke(new Object[] { json });
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
			call.setTargetEndpointAddress(new URL(SystemParamUtil.getString("sys", "hrp-login-url"))); // 设置要调用的接口地址以上一篇的为例子
			call.setOperationName(new QName(SystemParamUtil.getString("sys", "hrp-login-namespace"), "login")); // 设置要调用的接口方法
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
			WSContext wsContext = (WSContext) call.invoke(new Object[] {
					SystemParamUtil.getString("sys", "hrp-login-userName"), 
					SystemParamUtil.getString("sys", "hrp-login-psw"),
					SystemParamUtil.getString("sys", "hrp-login-slnName"),
					SystemParamUtil.getString("sys", "hrp-login-dcName"),
					SystemParamUtil.getString("sys", "hrp-login-language"),
					SystemParamUtil.getInt("sys", "hrp-login-dbType"),
					SystemParamUtil.getString("sys", "hrp-login-authPattern")});
					 
			System.out.println(wsContext);// 打印字符串
			sessionId = wsContext.getSessionId();

		} catch (RemoteException | ServiceException | MalformedURLException e) {
			e.printStackTrace();
		}
		return sessionId;
	}

}
