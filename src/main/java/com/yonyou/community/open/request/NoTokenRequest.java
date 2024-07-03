package com.yonyou.community.open.request;

import java.util.HashMap;

import com.yonyou.community.open.entity.URL;
import com.yonyou.community.open.enums.EncryptTypeEnum;
import com.yonyou.community.open.utils.EncryptUtil;

public class NoTokenRequest extends Request {
	
	private NoTokenRequest() {
		super();
	}
	
	public NoTokenRequest(URL url,String ak,String sk) {
		this();
		this.url = url.getUrl();
		this.method = url.getMethod();
		this.ak = ak;
		this.sk = sk;
		//this.init();
	}

	@Override
	protected void init(EncryptTypeEnum type) {
		header = new HashMap<String, String>();
		header.put("ak", ak);
		long timestamp = System.currentTimeMillis() + diffTime * 1000L;
		header.put("v", "1.0");
		header.put("sign-type", type.getValue());
		header.put("timestamp", timestamp+"");
		header.put("sign", EncryptUtil.hash(ak + sk + timestamp, type.getName()));
	}

}
