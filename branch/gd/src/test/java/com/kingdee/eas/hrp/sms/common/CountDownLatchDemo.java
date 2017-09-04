package com.kingdee.eas.hrp.sms.common;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.httpclient.HttpException;

import com.kingdee.eas.hrp.sms.util.http.HttpParam;
import com.kingdee.eas.hrp.sms.util.http.HttpUtil;

public class CountDownLatchDemo {

	private volatile static int success = 0;
	private volatile static int worry = 0;

	public static void main(String[] args) {

		CountDownLatchDemo demo = new CountDownLatchDemo();

		CountDownLatch latch = new CountDownLatch(1);
		CountDownLatch worker = new CountDownLatch(200);

		for (int i = 0; i < worker.getCount(); i++) {
			new Thread(demo.new Login(latch, worker)).start();
		}

		latch.countDown();
		try {
			worker.await();
		} catch (InterruptedException e) {
			System.out.println("main:" + e.getMessage());
		}

		System.out.println("all Login has done!");
		System.out.println("success:" + success + "===== worry:" + worry);
	}

	private class Login implements Runnable {

		CountDownLatch latch;
		CountDownLatch worker;

		protected String BASE_URL = "http://127.0.0.1:8080/sms/";
		private String LOGIN_URL = BASE_URL + "user/login";
		private String user = "test";
		private String pwd = "202cb962ac59075b964b07152d234b70"; // 123
		private String type = "QpXq24FxxE6c3lvHMPyYCxACEAI="; // 管理后台

		@Override
		public void run() {
			try {
				latch.await();

				System.err.println("cd count exist:" + latch.getCount());
				getCookies();
				success += 1;
				System.out.println(Thread.currentThread().getName() + ":login done !");
			} catch (Exception e) {
				worry += 1;
				System.out.println(Thread.currentThread().getName() + "----------->:login error !");
				System.out.println(Thread.currentThread().getName() + "----->" + e.getMessage());
			} finally {

				worker.countDown();

			}

		}

		public Login(CountDownLatch latch, CountDownLatch worker) {
			this.latch = latch;
			this.worker = worker;
		}

		public void getCookies() throws HttpException, IOException {

			HttpParam hp = HttpParam.init();

			hp.addCommon("user", user);
			hp.addCommon("pwd", pwd);
			hp.addCommon("type", type);

			Map<String, String> cookies = HttpUtil.sendPostForCookie(LOGIN_URL, hp);

			if (cookies == null || !cookies.containsKey("JSESSIONID")) {
				throw new RuntimeException("登录失败");
			}
			// System.out.println(Thread.currentThread().getName() + cookies.toString());
		}
	}
}
