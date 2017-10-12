package com.sparrow.common;

import com.sparrow.common.impl.LoginCodeValidateImpl;
import com.sparrow.common.impl.MobileAccessValidateImpl;
import com.sparrow.common.impl.MobileSendValidateImpl;
import com.sparrow.common.nozzle.ValidateModelServiceI;

public class ValidateTool {

	public static ValidateTool getInstance() {
		return new ValidateTool();
	}

	/**
	 * Get validate service by type
	 * 
	 * @param type
	 * @return
	 */
	public ValidateModelServiceI getValidateService(int type) {
		switch (type) {
		case 1:
			return LoginCodeValidateImpl.getInstance();
		case 2:
			return MobileAccessValidateImpl.getInstance();
		case 3:
			return MobileSendValidateImpl.getInstance();
		}
		return null;
	}
	
	/**
	 * switch determine limit
	 * 
	 * @param type
	 * @param service
	 * @param params
	 * @return
	 */
	public boolean determine(int type, ValidateModelServiceI service, Object... params) {
		if (service == null) {
			return false;
		}
		String mobile = "", ipAddress = "";
		switch (type) {
		case 1:
		case 2:
			ipAddress = (String) params[0];
			return determine(ipAddress, service);
		case 3:
			mobile = (String) params[0];
			ipAddress = (String) params[1];
			return determine(mobile, ipAddress, service);
		}
		return false;
	}

	/**
	 * Determine mobile code send limit
	 * 
	 * @param mobile
	 * @param ipAddress
	 * @return
	 */
	public boolean determine(String mobile, String ipAddress, ValidateModelServiceI validateImpl) {
		// TODO limit send count by mobile and ip
		validateImpl.setRedisKey(mobile);
		if (validateImpl.determineLimit()) {
			return true;
		}
		return false;
	}

	/**
	 * Determine validate access limit
	 * 
	 * @param keyName
	 * @return
	 */
	public boolean determine(String keyName, ValidateModelServiceI validateImpl) {
		// limit access count by keyName
		validateImpl.setRedisKey(keyName);
		if (validateImpl.determineLimit()) {
			return true;
		}
		return false;
	}

}
