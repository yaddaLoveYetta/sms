package com.kingdee.eas.hrp.sms.common;

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
}
