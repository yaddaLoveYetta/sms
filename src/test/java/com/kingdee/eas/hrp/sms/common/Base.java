package com.kingdee.eas.hrp.sms.common;

public class Base {

	String baseName = "base";

	public Base() {
		callName();
	}

	public void callName() {
		System.out.println(baseName);
	}

	class Sub extends Base {
		private  final  String baseName = "sub";

		public void callName() {
			System.out.println(baseName);
		}
	}

	public static void main(String[] args) {
		Base b =new Base().new Sub();
	}

}
