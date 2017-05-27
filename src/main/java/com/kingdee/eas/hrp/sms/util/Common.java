package com.kingdee.eas.hrp.sms.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONNull;
//import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class Common {

	/*
	 * public static void main(String[] args) { System.out.println(getBarCodeISN(16)); }
	 */

	/*
	 * 获取随机的字符
	 */
	public static String createUUID() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}

	/**
	 * 获取条形码内码
	 * 
	 * 说明：长度最小不能小于8位，否则该算法无用 随机码由时间戳+随机数组成 时间戳转为36进制，对剩余位数使用随机数补充
	 * 
	 * @param length
	 *            长度
	 * @return
	 */
	public static String getBarCodeISN(Integer length) {
		if (null == length)
			length = 12;

		Long timestamp = System.currentTimeMillis();

		String timeStr = Long.toString(timestamp, 36);

		int timeLength = timeStr.length();

		if (timeLength < length) {
			int randomLength = length - timeLength;

			Integer maxNum = (int) (Math.pow(10, randomLength) - 1);

			Integer minNum = (int) Math.pow(10, randomLength - 1);

			Integer xx = (int) (minNum + Math.random() * (maxNum - minNum));

			timeStr = timeStr + xx;

			return timeStr.toUpperCase();
		}

		return null;
	}

	/**
	 * 获取当前时间的字符串
	 * 
	 * @return
	 */
	public static String getCurrentTimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date()).toString();
	}

	/**
	 * 生成一个唯一key
	 * 
	 * 两个UUID拼凑
	 * 
	 * @return
	 */
	public static String getUUIDKey() {
		return createUUID() + "_" + createUUID();
	}

	// public static void main(String[] args) {
	// String regEx = "^[1-9][0-9]{4,11}$";
	// Pattern pat = Pattern.compile(regEx);
	// Matcher mat = pat.matcher("458789584578");
	// boolean rs = mat.find();
	// System.out.println("rs:" + rs);
	// }

	// MD5加密算法，用于用户密码加密
	public static String MD5(String password) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes("UTF-8"));
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
			// System.out.println("MD5(" + pwd + ",32) = " + result); //32位MD5加密
			// System.out.println("MD5(" + pwd + ",16) = " +
			// buf.toString().substring(8, 24));
			// //16位MD5加密
		} catch (UnsupportedEncodingException e) {
			System.out.println(e);
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return result;
	}

	// String转date日期
	public static Date String2Date(String str) {
		Date date = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.parse(str);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将json格式的字符串解析成Map对象
	 * <li>json格式：{"name":"admin","retries":"3fff","testname" :"ddd","testretries":"fffffffff"}
	 */
	public static HashMap<String, Object> toHashMap(JSONObject object) {

		HashMap<String, Object> data = new HashMap<String, Object>();
		// 将json字符串转换成jsonObject

		@SuppressWarnings("unchecked")
		Iterator<String> it = object.keySet().iterator();

		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext()) {
			String key = it.next();
			Object value = object.get(key);
			if (null == value)
				continue;
			data.put(key, value);
		}
		return data;
	}

	/**
	 * 将json格式的字符串解析成Map对象
	 * <li>json格式：{"name":"admin","retries":"3fff","testname" :"ddd","testretries":"fffffffff"}
	 */
	public static HashMap<String, Object> toHashMap(String jsonStr) {

		HashMap<String, Object> data = new HashMap<String, Object>();
		// 将json字符串转换成jsonObject

		JSONObject object = JSONObject.parseObject(jsonStr);
		@SuppressWarnings("unchecked")
		Iterator<String> it = object.keySet().iterator();
		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext()) {
			String key = it.next();
			Object value = object.get(key);
			if (null == value)
				continue;
			data.put(key, value);
		}
		return data;
	}

	/**
	 * 将JavaBean转换成Map<String,Object> <br>
	 * 利用Introspector和PropertyDescriptor
	 * 
	 * @param o
	 * @return
	 */
	public static Map<String, Object> transBean2Map(Object o) {

		if (o == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(o.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(o);

					if (value == null || "".equals(value)) {
						continue;
					}

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}

	// 格式化Date
	public static String dateFormatToStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 将Map按照key的字典顺序排序，返回TreeMap
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, Object> sortMapByKey(Map<String, Object> map) {

		if (map == null || map.isEmpty()) {
			return null;
		}

		Map<String, Object> sortMap = new TreeMap<String, Object>(new Comparator<String>() {

			@Override
			public int compare(String str1, String str2) {
				return str1.compareTo(str2);
			}
		});

		sortMap.putAll(map);

		return sortMap;
	}

	/**
	 * 将map转换成url
	 * 
	 * @param map
	 * @return
	 */
	private static String getUrlParamsByMap(Map<String, Object> map) {

		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		try {

			for (Map.Entry<String, Object> entry : map.entrySet()) {
				// sb.append(entry.getKey() + "=" + entry.getValue());
				// sb.append("&");

				sb.append(entry.getKey() + "=" + java.net.URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
				sb.append("&");
			}

		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
		}

		String s = sb.toString();
		if (s.endsWith("&")) {
			s = StringUtils.substringBeforeLast(s, "&");
		}
		return s;
	}

	/**
	 * 产生指定长度的常用代码
	 * 
	 * @author yadda
	 * @param lenght
	 *            代码长度
	 * @param type
	 *            类别 0：数字 1：小写字母2：大写字母3:数字与小写字母混搭4：数字与大写字母混搭5：数字-小写字母-大写字母混搭
	 * @return
	 */
	public static String getCode(int lenght, int type) {

		StringBuilder sb = null;
		StringBuilder retSb = new StringBuilder();
		Random r = new Random();

		switch (type) {
		case 0:
			sb = new StringBuilder("0123456789");
			break;
		case 1:
			sb = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
			break;
		case 2:
			sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			break;
		case 3:
			sb = new StringBuilder("0123456789abcdefghijklmnopqrstuvwxyz");
			break;
		case 4:
			sb = new StringBuilder("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			break;
		case 5:
			sb = new StringBuilder("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
			break;
		}

		if (null == sb) {
			return "";
		}

		int range = sb.length();
		for (int i = 0; i < lenght; ++i) {
			retSb.append(sb.charAt(r.nextInt(range)));
		}

		return retSb.toString();
	}

	/**
	 * 创建发货单订单号
	 * 
	 * @Title createShipOrderNo
	 * @return String
	 * @date 2017-05-20 09:46:06 星期六
	 */
	public static String createShipOrderNo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss", Locale.CHINA);
		Random rm = new Random();
		// 获得随机数
		double pross = (1 + rm.nextDouble()) * Math.pow(10, 3);
		// 将获得的获得随机数转化为字符串
		String fixLenthString = String.valueOf(pross);
		return "Pur-" + sdf.format(new Date()) + fixLenthString.substring(1, 3 + 1);

	}

	/**
	 * 
	 * 创建发货单批次
	 */
	public static String createNo(int Num, String SDF, String ZF) {
		SimpleDateFormat sdf = new SimpleDateFormat(SDF, Locale.CHINA);
		Random rm = new Random();
		// 获得随机数
		double pross = (1 + rm.nextDouble()) * Math.pow(10, Num);
		// 将获得的获得随机数转化为字符串
		String fixLenthString = String.valueOf(pross);
		return sdf.format(new Date()) + ZF + fixLenthString.substring(1, Num + 1);

	}

	/**
	 * 判断日期是否为正确的长日期(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param timeStr
	 * @return
	 */
	public static boolean valiDateTimeWithLongFormat(String timeStr) {
		String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) " + "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(timeStr);
		if (matcher.matches()) {
			pattern = Pattern.compile("(\\d{4})-(\\d+)-(\\d+).*");
			matcher = pattern.matcher(timeStr);
			if (matcher.matches()) {
				int y = Integer.valueOf(matcher.group(1));
				int m = Integer.valueOf(matcher.group(2));
				int d = Integer.valueOf(matcher.group(3));
				if (d > 28) {
					Calendar c = Calendar.getInstance();
					c.set(y, m - 1, 1);
					int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
					return (lastDay >= d);
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 判断日期是否为长日期(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @Title isLongDate
	 * @param timeStr
	 * @return
	 * @return boolean
	 * @date 2017-05-27 16:41:17 星期六
	 */
	public static boolean isLongDate(String timeStr) {
		String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) " + "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(timeStr);
		return matcher.matches();
	}

	public static void main(String[] args) {

	}
}
