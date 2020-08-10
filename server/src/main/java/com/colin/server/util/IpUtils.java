package com.colin.server.util;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

/**
 * @author zhaolz
 */
public class IpUtils {

	/**
	 * 获取请求客户端的IP
	 */
	public static String getClientIp(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getRemoteAddr();
		}
		if (ip != null && ip.indexOf(",") > 0) {
			String[] ips = ip.split(",");
			ip = StringUtils.trimToEmpty(ips[0]);
		}

		ip = (ip == null||"0:0:0:0:0:0:0:1".equals(ip))?"127.0.0.1":ip;

		return ip;
	}

}
