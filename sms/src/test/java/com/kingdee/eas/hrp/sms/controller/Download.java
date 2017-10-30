package com.kingdee.eas.hrp.sms.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class Download extends BaseControllerTest {

	@Test
	public void downloadNet() throws IOException {
		// 下载网络文件
		int bytesum = 0;
		int byteread = 0;

		URL url = new URL("http://127.0.0.1:8080/sms/file/download");

		InputStream in = null;
		HttpURLConnection conn = null;

		try {

			conn = (HttpURLConnection) url.openConnection();

			if (conn.getResponseCode() != 200) {
				System.out.println(conn.getResponseMessage());
				System.out.println(convertStreamToString(conn.getInputStream()));
				return;
			}

			in = conn.getInputStream();

			FileInputStream fin = new FileInputStream("");

			FileOutputStream out = new FileOutputStream("C:/file-upload/files/3010/aa.png");

			byte[] buffer = new byte[1204];

			while ((byteread = in.read(buffer)) != -1) {
				bytesum += byteread;
				System.out.println(bytesum);
				out.write(buffer, 0, byteread);
			}

		} catch (FileNotFoundException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static String convertStreamToString(InputStream is) {
		/*
		 * 3 * To convert the InputStream to String we use the BufferedReader.readLine() 4 * method. We iterate until
		 * the BufferedReader return null which means 5 * there's no more data to read. Each line will appended to a
		 * StringBuilder 6 * and returned as String. 7
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}
}
