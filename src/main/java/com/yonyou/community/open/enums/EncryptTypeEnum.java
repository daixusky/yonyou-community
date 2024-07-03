package com.yonyou.community.open.enums;

/**
 * 加密类型枚举
 * @author daixusky
 *
 */
public enum EncryptTypeEnum {
	md5("MD5","md5"),sha256("SHA-256","sha256"),sha512("SHA-512","sha512");
	
	private String name;
    private String value;
	
	EncryptTypeEnum(String name,String value){
		this.setName(name);
		this.setValue(value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
