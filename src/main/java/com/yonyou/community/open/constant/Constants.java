package com.yonyou.community.open.constant;

import com.yonyou.community.open.entity.URL;
import com.yonyou.community.open.enums.HttpMethodEnum;

/**
 * 常量
 * @author daixusky
 *
 */
public class Constants {
	
	/**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";
    
    /**
     * 开发社区地址
     */
    public static final String BASE_URL = "https://community.yonyou.com";
    
    /**
     * 获取token地址
     */
    public static final URL TOKEN_URL = new URL("https://community.yonyou.com/open-platform/api/pub/getTokenByTicket", HttpMethodEnum.GET);

}
