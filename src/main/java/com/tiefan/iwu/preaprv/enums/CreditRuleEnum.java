package com.tiefan.iwu.preaprv.enums;

public enum CreditRuleEnum {

	
	WORKING("待处理"),

	REJECT("拒绝"),
	
	PASS("通过"),
	UNKNOWN("未知"),
	
	;
	
	private String msg;
	
	private CreditRuleEnum(String code)
	{
		this.msg = code;
	}
	
	public String getTypeMsg()
	{
		return msg;
	}
}
