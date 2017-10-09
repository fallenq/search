package com.sparrow.common;

public class AddressTool {

	/**
	 * Determine mobile code send limit
	 * 
	 * @param mobile
	 * @param ipAddress
	 * @return
	 */
	public static boolean determineMobileIpLimit(String mobile, String ipAddress) {
		// TODO limit send count by ip
		return true;
	}

	/**
	 * Determine mobile validate access limit
	 * 
	 * @param ipAddress
	 * @return
	 */
	public static boolean determineMobileIpLimit(String ipAddress) {
		// TODO limit send count by ip
		return true;
	}

	/**
	 * Determine mobile validate access limit
	 * 
	 * @param ipAddress
	 * @param method
	 *            1-登录验证码
	 * @return
	 */
	public static boolean determineLoginIpLimit(String ipAddress, int method) {
		// TODO limit send count by ip
		return true;
	}

}
