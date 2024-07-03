package com.yonyou.community.open.enums;

/**
 * 请求方法枚举
 * @author daixusky
 *
 */
public enum HttpMethodEnum {
	GET("GET"),POST("POST"),PUT("PUT");
	
	private String name;
	
	HttpMethodEnum(String name){
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
