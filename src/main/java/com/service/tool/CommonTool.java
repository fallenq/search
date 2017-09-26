package com.service.tool;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class CommonTool {

	public static Map<String, Object> emptyMap() {
		Map<String, Object> emptyMap = new HashMap<String, Object>();
		return emptyMap;
	}

	public static Map<String, String> emptyStringMap() {
		Map<String, String> emptyMap = new HashMap<String, String>();
		return emptyMap;
	}

	public static Map<Integer, String> emptyIntMap() {
		Map<Integer, String> emptyMap = new HashMap<Integer, String>();
		return emptyMap;
	}

	/**
	 * Create random int in range
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int createRandomInt(int min, int max) {
		return (int) (min + Math.random() * (max - min + 1));
	}

	/**
	 * Get real client ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getCLientIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
//		String ip = request.getHeader("x-forwarded-for");  
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
//            ip = request.getHeader("Proxy-Client-IP");  
//        }  
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
//            ip = request.getHeader("WL-Proxy-Client-IP");  
//        }  
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
//            ip = request.getHeader("HTTP_CLIENT_IP");  
//        }  
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
//            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
//        }  
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
//            ip = request.getRemoteAddr();  
//        }  
//        return ip;  
	}
}
