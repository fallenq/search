package com.service.config;

import java.util.Map;

import com.service.tool.CommonTool;

public class WarnMsgConfig {
	/**
	 * common
	 */
	public static int COMMON_SUBMIT_ERROR = 1;
	public static int COMMON_SYSTEM_BUSY  = 2;
	/**
	 * sparrow
	 */
	public static int SPARROW_USER_SUBMIT_SUCCESS = 1;
	public static int SPARROW_USER_SUBMIT_FAILURE = 2;
	public static int SPARROW_USER_REGIST_SUCCESS = 3;
	public static int SPARROW_USER_REGIST_FAILURE = 4;
	public static int SPARROW_USER_MOBILE_EXISTS  = 5;
	public static int SPARROW_USER_NOEXISTS  	  = 6;
	public static int SPARROW_USER_LOGININFO_ERROR	= 7;
	
	public static Map<Integer, String> putValue(Map<Integer, String> warnMap, int msgId, String value) {
		warnMap.put(msgId, value);
		return warnMap;
	}
	
	public static Map<Integer, String> getCommonMap() {
		Map<Integer, String> commonMap = CommonTool.emptyIntMap();
		commonMap.put(1, "提交失败");
		commonMap.put(2, "系统繁忙，清稍后");
		return commonMap;
	}
	
	public static Map<Integer, String> getSparrowMap() {
		Map<Integer, String> sparrowMap = CommonTool.emptyIntMap();
		sparrowMap.put(1, "提交成功");
		sparrowMap.put(2, "提交失败");
		sparrowMap.put(3, "注册成功");
		sparrowMap.put(4, "注册失败，请稍后尝试");
		sparrowMap.put(5, "该手机号已注册");
		sparrowMap.put(6, "账号不存在");
		sparrowMap.put(7, "用户名与密码不一致");
		return sparrowMap;
	}
	
	public static String getCommonValue(int msgId) {
		Map<Integer, String> commonMap = WarnMsgConfig.getCommonMap();
		if (commonMap.containsKey(msgId)) {
			return commonMap.get(msgId);
		}
		return "";
	}
	
	public static String getSparrowValue(int msgId) {
		Map<Integer, String> commonMap = WarnMsgConfig.getSparrowMap();
		if (commonMap.containsKey(msgId)) {
			return commonMap.get(msgId);
		}
		return "";
	}
	
}
