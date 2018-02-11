package com.kingdee.eas.hrp.sms.service.impl;

import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.service.api.IWebService;
import com.kingdee.eas.hrp.sms.util.SystemParamUtil;
import com.kingdee.eas.hrp.sms.util.WSContext;
import org.apache.axis.client.Call;
import org.apache.axis.message.SOAPHeaderElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;
import java.net.URL;

@Service
public class WebService extends BaseService implements IWebService {

    private static final Logger logger = LoggerFactory.getLogger(WebService.class);

    @Override
    public String webService(String json, String name) {
        String response = "";
        String sessionId = "";
        try {
            org.apache.axis.client.Service sv = new org.apache.axis.client.Service();
            Call call = (Call) sv.createCall();
            call.setUseSOAPAction(true);
            call.setTargetEndpointAddress(new URL(SystemParamUtil.getString("sys", "hrp-sync-url")));
            call.setOperationName(new QName(SystemParamUtil.getString("sys", "hrp-sync-namespace"), name));
            call.setUseSOAPAction(true);
            // 设置参数名,第二个参数表示String类型,第三个参数表示入参
            call.addParameter("json", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
            // 设置超时时间
            call.setTimeout(60000);
            // 返回参数类型
            call.setReturnClass(String.class);
            // // 由于需要认证，需要设置sessionId
            SOAPHeaderElement soapHeaderElement = new SOAPHeaderElement(
                    SystemParamUtil.getString("sys", "hrp-sync-header-namespace"), "SessionId");

            sessionId = loginInEAS();

            logger.debug(name + "==loginInEAS()获取到sessionId=" + sessionId);

            soapHeaderElement.setValue(sessionId);

            call.addHeader(soapHeaderElement);
            response = (String) call.invoke(new Object[]{json});
        } catch (Exception e) {
            logger.error("sessionId=" + sessionId);
            logger.error(e.getMessage(), e);
            throw new BusinessLogicRunTimeException(e);
        }
        return response;
    }

    public String loginInEAS() {

        String sessionId = "";
        try {
            org.apache.axis.client.Service sv = new org.apache.axis.client.Service();
            Call call = (Call) sv.createCall();
            call.setUseSOAPAction(true);
            // 设置要调用的接口地址以上一篇的为例子
            call.setTargetEndpointAddress(new URL(SystemParamUtil.getString("sys", "hrp-login-url")));
            // 设置要调用的接口方法
            call.setOperationName(new QName(SystemParamUtil.getString("sys", "hrp-login-namespace"), "login"));
            // 设置参数名,第二个参数表示String类型,第三个参数表示入参
            call.addParameter("userName", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.addParameter("password", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.addParameter("slnName", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.addParameter("dcName", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.addParameter("language", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.addParameter("dbType", org.apache.axis.encoding.XMLType.XSD_INT, javax.xml.rpc.ParameterMode.IN);
            call.addParameter("authPattern", org.apache.axis.encoding.XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);
            // 超时时间为20s
            call.setTimeout(20000);

            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_ANYTYPE);
            // 返回参数类型
            call.setReturnClass(WSContext.class);
            WSContext wsContext = (WSContext) call.invoke(new Object[]{
                    SystemParamUtil.getString("sys", "hrp-login-userName"),
                    SystemParamUtil.getString("sys", "hrp-login-psw"),
                    SystemParamUtil.getString("sys", "hrp-login-slnName"),
                    SystemParamUtil.getString("sys", "hrp-login-dcName"),
                    SystemParamUtil.getString("sys", "hrp-login-language"),
                    SystemParamUtil.getInt("sys", "hrp-login-dbType"),
                    SystemParamUtil.getString("sys", "hrp-login-authPattern")});

            // 打印字符串
            System.out.println(wsContext);
            sessionId = wsContext.getSessionId();

        } catch (Exception e) {
            throw new BusinessLogicRunTimeException(e);
        }
        return sessionId;
    }

}
