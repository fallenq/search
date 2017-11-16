package com.service.tool;

import java.util.UUID;

public class StringTool {

	/**
	 * 获取uuId
	 * 
	 * @return
	 */
	public static String uuId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	/**
	 * 获取uuCode
	 * 
	 * @param point
	 * @return
	 */
	public static String uuCode() {
		String[] uSplit = StringTool.splitString(StringTool.uuId(), "-");
		return uSplit[uSplit.length - 1];
	}

	/**
	 * Split string to array
	 * 
	 * @param content
	 * @param regex
	 * @return
	 */
	public static String[] splitString(String content, String regex) {
		return content.split(regex);
	}

	/**
	 * Format mobile with method
	 * 
	 * @param mobile
	 * @param method
	 * @param params
	 * @return
	 */
	public static String parseMobile(String mobile, int method, String... params) {
		switch (method) {
		case 1:
			StringBuilder mobileBuilder = new StringBuilder(mobile);
			mobileBuilder.replace(3, 7, "****");
			return mobileBuilder.toString();
		default:
			return mobile;
		}
	}
	
	/**
	 * Validate whether error param
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isAvailableParam(String value) {
		if (value != null && !value.isEmpty()) {
			return true;
		}
		return false;
	}

}
