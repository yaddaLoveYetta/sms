package com.kingdee.eas.hrp.sms.common;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class Common {

	@Test
	public void md5() {
		HashFunction md5 = Hashing.md5();
		HashCode hashString = md5.hashString("123", Charsets.UTF_8);
		System.out.println(hashString.toString());
	}

	@Test
	public void encoded() throws Exception {
		String ss = "你好吗"; // utf-8
		String utf_8 = new String(ss.getBytes(), Charsets.UTF_8);
		System.out.println(utf_8);
		
		String ios8859_1 = new String(utf_8.getBytes(), Charsets.ISO_8859_1);
		System.out.println(ios8859_1);
		
		String utf_82 = new String(ios8859_1.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
		System.out.println(utf_82);

	}
	@Test
	public void out2Excel(){
		// 声明工作簿
		HSSFWorkbook wb= new HSSFWorkbook();
		// 声明工作表
		HSSFSheet sheet= wb.createSheet("供应商资质");
	}
}
