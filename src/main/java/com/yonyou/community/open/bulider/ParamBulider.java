package com.yonyou.community.open.bulider;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数构建器
 * @author daixusky
 *
 */
public class ParamBulider extends HashMap<String, Object> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ParamBulider() {}
	
	public static ParamBulider create() {
		return new ParamBulider();
	}
	
	public ParamBulider addParam(String key,Object value) {
		super.put(key, value);
		return this;
	}
	
	public ParamBulider addParams(Map<String, Object> params) {
		super.putAll(params);
		return this;
	}
}
