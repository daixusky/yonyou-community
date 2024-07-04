package com.yonyou.community.open.request;

import java.util.Map;

import com.yonyou.community.open.bulider.ParamBulider;
import com.yonyou.community.open.enums.EncryptTypeEnum;
import com.yonyou.community.open.utils.HttpUtil;

public abstract class Request {
	
	protected ParamBulider params;
	
	protected Map<String, String> header;
	
	protected String method = "GET";
	
	protected String url;
	
	protected String ak;
	
	protected String sk;
	
	protected String token;
	
	protected EncryptTypeEnum type = EncryptTypeEnum.md5;
	
	protected long diffTime = 0L;
	
	protected String body;
	
	protected Request() {
		params = ParamBulider.create();
	}
	
	/**
	 * 重新设置加密方式
	 * @param type
	 * @return
	 */
	public Request setEncryptType(EncryptTypeEnum type) {
		if(type == null) {
			return this;
		}
		//this.init(type);
		this.type = type;
		return this;
	}
	
	/**
	 * 设置时间偏移量，单位：秒
	 * @param diffTime
	 * @return
	 */
	public Request setDiffTime(long diffTime) {
		this.diffTime = diffTime;
		return this;
	}
	
	protected abstract void init(EncryptTypeEnum type);
	
	/**
	 * 添加请求参数
	 * @param key
	 * @param value
	 * @return
	 */
	public Request addParam(String key,Object value) {
		this.params.addParam(key, value);
		return this;
	}
	
	public Request setBody(String jsonBody) {
		this.body = jsonBody;
		return this;
	}
	
	/**
	 * 添加请求参数
	 * @param params
	 * @return
	 */
	public Request addParams(Map<String, Object> params) {
		this.params.addParams(params);
		return this;
	}
	
	public String ok() throws Exception {
		init(this.type);
		return HttpUtil.requestHTTPContent(url, method, header, params,body);
	}

}
