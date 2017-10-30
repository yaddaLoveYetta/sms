package com.kingdee.eas.hrp.sms.domain;

public enum DataTypeeEnum {

	NUMBER(1, "数字"), TEXT(2, "文本"), TIME(3, "日期时间"), BOOLEAN(4, "布尔值");

	private int number;
	private String name;

	private DataTypeeEnum(int number, String name) {
		this.number = number;
		this.name = name;
	}

	public static DataTypeeEnum getTypeEnum(int number) {

		for (DataTypeeEnum d : DataTypeeEnum.values()) {
			if (d.number == number) {
				return d;
			}
		}
		return DataTypeeEnum.TEXT;
	}
}
