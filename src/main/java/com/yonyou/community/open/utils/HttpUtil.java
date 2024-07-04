package com.yonyou.community.open.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * http请求工具类
 * 
 * @author daixusky
 *
 */
public class HttpUtil {

	// 将请求头键值对添加到 HTTP 请求中
	private static void addHeadersToRequest(HttpURLConnection httpConn, Map<String, ?> headers) {
		httpConn.setRequestProperty("Accept", "application/json");
		httpConn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
		httpConn.setRequestProperty("charset", "UTF-8");
		if (headers != null) {
			for (Map.Entry<String, ?> entry : headers.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();

				if (value.getClass().isArray()) {
					// 如果是数组类型，则遍历数组并添加请求头
					for (Object v : (Object[]) value) {
						httpConn.setRequestProperty(key, v.toString());
					}
				} else {
					// 如果不是数组类型，则直接添加请求头
					httpConn.setRequestProperty(key, value.toString());
				}
			}
		}
	}

	// 将请求参数拼接进 URL
	public static String concatParamsToURL(String staticURL, String paramsStr) {
		return staticURL + paramsStr;
	}

	public static String concatParamsToURL(String staticURL, Map<String, ?> params) throws Exception {
		// staticURL 是字符串形式的静态 URL
		// params 键与值分别是参数名与参数值，URL 有重复同名参数时将多个值放进数组

		// 判断参数是否为空
		if (params.isEmpty()) {
			return staticURL;
		}

		StringBuilder sb = new StringBuilder(staticURL);

		// 判断 URL 中是否已经包含参数
		boolean hasParams = staticURL.indexOf("?") != -1;

		// 遍历参数
		for (Map.Entry<String, ?> entry : params.entrySet()) {
			String key = entry.getKey(); // 参数名
			Object value = entry.getValue(); // 参数值

			// 判断参数值是否为数组
			if (value.getClass().isArray()) {
				// 如果是数组，遍历数组并添加参数
				for (Object v : (Object[]) value) {
					sb.append(hasParams ? "&" : "?").append(URLEncoder.encode(key, "utf-8")).append("=")
							.append(URLEncoder.encode(v.toString(), "utf-8"));
					hasParams = true;
				}
			} else {
				// 如果不是数组，直接添加参数
				sb.append(hasParams ? "&" : "?").append(URLEncoder.encode(key, "utf-8")).append("=")
						.append(URLEncoder.encode(value.toString(), "utf-8"));
				hasParams = true;
			}
		}

		return sb.toString();
	}


	public static String requestHTTPContent(String strURL, String method, Map<String, ?> headers, Map<String, ?> params,String jsonBody)
			throws Exception {
		// strURL 是 String 类型的 URL
		// method 是 String 类型的请求方法，为 "GET" 或 "POST"
		// headers 键与值分别是请求头名与请求头值，有重复同名请求头时将多个值放进数组
		// params 键与值分别是参数名与参数值，URL 有重复同名参数时将多个值放进数组
		// GET 方法下，query 参数拼接在 URL 字符串末尾
		//if (method.equals("GET") && params != null) {
		//	strURL = concatParamsToURL(strURL, params);
		//}
		strURL = concatParamsToURL(strURL, params);

		URL url = new URL(strURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod(method);
		// 添加 HTTP 请求头
		addHeadersToRequest(httpConn, headers);
		// 请求时是否使用缓存
		httpConn.setUseCaches(false);

		// POST 方法请求必须设置下面两项
		// 设置是否从 HttpUrlConnection 的对象写
		httpConn.setDoOutput(true);
		// 设置是否从 HttpUrlConnection 的对象读入
		httpConn.setDoInput(true);

		// 此处默认 POST 方法发送的内容就是 JSON 形式的 body 参数，可以自行更改
		if (method.equals("POST") && jsonBody != null) {
			// 发送请求
			OutputStream out = new DataOutputStream(httpConn.getOutputStream());
			// getBytes() 作用为根据参数给定的编码方式，将一个字符串转化为一个字节数组
			
			out.write(jsonBody.getBytes("UTF-8"));
			out.flush();
		} else {
			// 发送请求
			httpConn.connect();
		}

		// String contentType = httpConn.getContentType();
		String result = readResponseContent(httpConn);

		return result;
	}

	// 读取响应内容
	public static String readResponseContent(HttpURLConnection httpConn) throws Exception {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"))) {
			// 此处不用 StringBuffer 而用 StringBuilder 是出于线程安全的考虑
			StringBuilder builder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			return builder.toString();
		}
	}
}
