package com.yonyou.community.open.service;

import com.yonyou.community.open.constant.Constants;
import com.yonyou.community.open.entity.URL;
import com.yonyou.community.open.exception.CustomException;
import com.yonyou.community.open.request.NoTokenRequest;
import com.yonyou.community.open.request.Request;
import com.yonyou.community.open.request.UseTokenRequest;

public class openService {
	
	private String ak;
	
	private String sk;
	
	private openService() {}
	
	private static openService instance = new openService();
	
	/**
	 * 创建Service
	 * @param ak	社区申请的ak
	 * @param sk	社区申请的sk
	 * @return
	 */
	public static openService getInstance(String ak,String sk) {
		instance.setAk(ak);
		instance.setSk(sk);
		return instance;
	}
	
	/**
	 * 构建无需用户信息的request
	 * @param url	请求地址
	 * @return
	 */
	public Request buliderRequest(URL url) {
		return new NoTokenRequest(url, ak, sk);
	}
	
	/**
	 * 构建需要用户信息的request
	 * @param url	请求地址
	 * @param token 用户token
	 * @return
	 */
	public Request buliderRequest(URL url,String token) {
		return new UseTokenRequest(url, ak, sk,token);
	}

	private void setAk(String ak) {
		this.ak = ak;
	}

	private void setSk(String sk) {
		this.sk = sk;
	}
	/**
	 * 根据ticket获取token
	 * @param ticket 临时票据
	 * @return
	 */
	public String getTokenByTicket(String ticket) {
		try {
			return buliderRequest(Constants.TOKEN_URL).addParam("ticket", ticket).ok();
		} catch (Exception e) {
			throw new CustomException("获取token失败", e);
		}
	}

}
