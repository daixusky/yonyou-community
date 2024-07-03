package com.yonyou.community.open.utils;

import java.security.MessageDigest;

/**
 * 加密处理类
 * @author daixusky
 *
 */
public class EncryptUtil {
	
	public static String hash(String s,String type)
    {
        try
        {
            return new String(toHex(encrypt(s,type)).getBytes("UTF-8"), "UTF-8");
        }
        catch (Exception e)
        {
        	throw new RuntimeException("not supported charset...{}", e);
        }
    }
	
	private static final String toHex(byte hash[])
    {
        if (hash == null)
        {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++)
        {
            if ((hash[i] & 0xff) < 0x10)
            {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }
	
	private static byte[] encrypt(String s,String type)
    {
        MessageDigest algorithm;
        try
        {
            algorithm = MessageDigest.getInstance(type);
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        }
        catch (Exception e)
        {
        	throw new RuntimeException("encrypt Error...", e);
        }
    }

}
