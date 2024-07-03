package com.yonyou.community.open.entity;

import com.yonyou.community.open.enums.HttpMethodEnum;

/**
 * 接口请求url定义模型
 * @author daixusky
 *
 */
public class URL {
	
	private String url;
	
	private String method;
	
	@SuppressWarnings("unused")
	private URL() {}
	
	public URL(String url,HttpMethodEnum method) {
		this.url = url;
		this.method = method.getName();
	}

	public String getUrl() {
		return url;
	}

	public String getMethod() {
		return method;
	}

}
