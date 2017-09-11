package com.service.tool;

import java.util.UUID;

public class StringTool {

	/**
	 * 获取uuId
	 * @return
	 */
	public static String uuId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	/**
	 * 获取uuCode
	 * @param point
	 * @return
	 */
	public static String uuCode() {
		String uuString = StringTool.uuId();
		String[] uSplit = uuString.split("-");
		return uSplit[uSplit.length-1];
	}
	
}
