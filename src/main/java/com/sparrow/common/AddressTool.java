package com.sparrow.common;

import com.sparrow.common.impl.LoginCodeValidateImpl;
import com.sparrow.common.impl.MobileAccessValidateImpl;
import com.sparrow.common.impl.MobileSendValidateImpl;

public class AddressTool {

	public static AddressTool getInstance() {
		return new AddressTool();
	}

	/**
	 * Determine mobile code send limit
	 * 
	 * @param mobile
	 * @param ipAddress
	 * @return
	 */
	public boolean determineIpLimit(String mobile, String ipAddress, MobileSendValidateImpl validateImpl) {
		// TODO limit send count by mobile and ip
		validateImpl.setRedisKey(mobile);
		if (validateImpl.determineLimit()) {
			return true;
		}
		return false;
	}

	/**
	 * Determine mobile validate access limit
	 * 
	 * @param ipAddress
	 * @return
	 */
	public boolean determineIpLimit(String ipAddress, MobileAccessValidateImpl validateImpl) {
		// TODO limit send count by ip
		validateImpl.setRedisKey(ipAddress);
		if (validateImpl.determineLimit()) {
			return true;
		}
		return false;
	}

	/**
	 * Determine mobile validate access limit
	 * 
	 * @param ipAddress
	 * @param method
	 *            1-登录验证码
	 * @return
	 */
	public boolean determineIpLimit(String ipAddress, LoginCodeValidateImpl validateImpl) {
		// TODO limit send count by ip
		validateImpl.setRedisKey(ipAddress);
		if (validateImpl.determineLimit()) {
			return true;
		}
		return false;
	}

}
