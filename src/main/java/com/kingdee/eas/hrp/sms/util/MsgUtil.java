package com.kingdee.eas.hrp.sms.util;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import net.sf.jsqlparser.statement.execute.Execute;

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
	public static void sendSMS(String[] mobiles, String smsContent){
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(new Runnable() {			
			@Override
			public void run() {
				MsgHttpClient client = MsgHttpClient.getInstance();
				 client.sendSMS(mobiles, smsContent);				
			}
		});
		executorService.shutdown();	
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
	public static void sendtimesMS(String[] mobiles, String smsContent,String times){
		
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(new Runnable() {			
			@Override
			public void run() {
				MsgHttpClient client = MsgHttpClient.getInstance();
				 client.sendtimesMS(mobiles, smsContent, times);			
			}
		});
		executorService.shutdown();	
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
		String[] mobiles = new String[] { "18802088452"};
		String smsContent = "好好上班！";
		String times = "20170519155100";

		MsgUtil.sendSMS(mobiles, smsContent);
		System.out.println("123212");
		//MsgUtil.sendtimesMS(mobiles, smsContent, times);
		//System.out.println(client.getBalance());
		
	}
	
}
