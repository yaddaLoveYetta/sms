package com.kingdee.eas.hrp.sms.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kingdee.eas.hrp.sms.util.http.HttpUtil;

/**
 * 类名称：MsgHttpClient.java
 * 
 * @version 类说明：
 */
public class MsgHttpClient {
	private static Logger logger = LoggerFactory.getLogger(MsgHttpClient.class);
	private String baseUrl;
	private String softwareSerialNo;
	private String key;
	private String password;
	private String code;
	private static volatile MsgHttpClient instance = null;

	private MsgHttpClient() {
		init();
	}

	private void init() {
		this.baseUrl = "http://sdk4http.eucp.b2m.cn:8080/sdkproxy/";
		this.softwareSerialNo = "3SDK-EGD-0130-LKQST";
		this.key = "014844";
		this.password = "521319";
		this.code = "";
	}

	public static MsgHttpClient getInstance() {
		if (instance == null)
			synchronized (MsgHttpClient.class) {
				if (instance == null)
					instance = new MsgHttpClient();
			}
		return instance;
	}

	private Map<String, String> sendHttp(String url, String param) {
		url = url + "?" + param;
		String responseString = HttpUtil.doGetRequest(url);
		;
		responseString = responseString.trim();
		return xmlResponse(responseString);
	}

	// 统一解析格式
	private Map<String, String> xmlResponse(String response) {
		Map<String, String> resMap = new HashMap<String, String>();
		Document document = null;
		try {
			document = DocumentHelper.parseText(response);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		resMap.put("message", root.elementText("message"));
		resMap.put("error", root.elementText("error"));
		return resMap;
	}

	/**
	 * 调用该方法进行即时短信发送.
	 * 
	 * @param mobiles
	 *            手机号码
	 * @param smsContent
	 *            发送内容（最多500个汉字或1000个纯英文）
	 * @return 成功返回0, 失败或异常返回带负号'-'的字符串.
	 */
	public String sendSMS(String[] mobiles, String smsContent) {
		String url = baseUrl + "sendsms.action";
		String phone = StringUtils.join(mobiles, ",");
		String param = "cdkey=" + softwareSerialNo + "&password=" + key + "&phone=" + phone + "&message=" + smsContent
				+ "&addserial=" + code + "&seqid=" + 0;
		Map resultMap = sendHttp(url, param);
		if (!resultMap.isEmpty()) {
			return resultMap.get("error").toString();
		}
		return "-250";
	}
	
	/**
	 * 调用该方法进行定时短信发送.
	 * 
	 * @param mobiles
	 *            手机号码
	 * @param smsContent
	 *            发送内容（最多500个汉字或1000个纯英文）
	 * @param times yyyymmddhhmmss          
	 * @return 成功返回0, 失败或异常返回带负号'-'的字符串.
	 */
	public String sendtimesMS(String[] mobiles, String smsContent,String times) {
		String url = baseUrl + "sendtimesms.action";
		String phone = StringUtils.join(mobiles, ",");
		String param = "cdkey=" + softwareSerialNo + "&password=" + key + "&phone=" + phone + "&message=" + smsContent
				+ "&addserial=" + code +"&sendtime="+times+ "&seqid=" + 0;
		Map resultMap = sendHttp(url, param);
		if (!resultMap.isEmpty()) {
			return resultMap.get("error").toString();
		}
		return "-250";
	}

	/**
	 * 调用该方法获取账号余额.
	 * 
	 * @return
	 */
	public String getBalance() {
		String param = "cdkey=" + softwareSerialNo + "&password=" + key;
		String url = baseUrl + "querybalance.action";
		Map resultMap = sendHttp(url, param);
		if (!resultMap.isEmpty()) {
			return resultMap.get("message").toString();
		}
		return "获取余额失败";
	}

	public static void main(String[] args) {
		String[] mobiles = new String[] { "18802088452","18500303025"};
		String smsContent = "好好上班！";
		String times = "20170519155100";

		MsgHttpClient client = MsgHttpClient.getInstance();
		//client.sendSMS(mobiles, smsContent);
		client.sendtimesMS(mobiles, smsContent, times);
		//System.out.println(client.getBalance());
	}

}