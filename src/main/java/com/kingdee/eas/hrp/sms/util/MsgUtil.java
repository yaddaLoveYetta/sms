package com.kingdee.eas.hrp.sms.util;
/**
* 类名称：MsgUtil.java
* @version 
* 类说明：
*/
public class MsgUtil {
	
	/**
	 * 调用该方法进行即时短信发送.
	 * 
	 * @param mobiles
	 *            手机号码
	 * @param smsContent
	 *            发送内容（最多500个汉字或1000个纯英文）
	 * @return 成功返回0, 失败或异常返回带负号'-'的字符串.
	 */
	public static String sendSMS(String[] mobiles, String smsContent){
		MsgHttpClient client = MsgHttpClient.getInstance();
		return client.sendSMS(mobiles, smsContent);		
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
	public static String sendtimesMS(String[] mobiles, String smsContent,String times){
		MsgHttpClient client = MsgHttpClient.getInstance();
		return client.sendtimesMS(mobiles, smsContent, times);			
	}
	
	/**
	 * 调用该方法获取账号余额.
	 * 
	 * @return
	 */
	public static String getBalance(){
		MsgHttpClient client = MsgHttpClient.getInstance();
		return client.getBalance();
	}
	
	public static void main(String[] args) {
		String[] mobiles = new String[] { "18825166236"};
		String smsContent = "好好上班！";
		String times = "20170519155100";

		MsgUtil.sendSMS(mobiles, smsContent);
		//MsgUtil.sendtimesMS(mobiles, smsContent, times);
		//System.out.println(client.getBalance());		
	}
	
}
