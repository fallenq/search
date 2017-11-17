package com.sparrow.common;

import com.service.tool.StringTool;
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
		String keyName = (String) params[0];
		if (!StringTool.isAvailableString(keyName)) {
			return false;
		}
		switch (type) {
			case 1:
			case 2:
				return determine(keyName, service);
			case 3:
				String ipAddress = (String) params[1];
				return determine(keyName, ipAddress, service);
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
		// limit send count by mobile and ip
		validateImpl.setRedisLimitKey(mobile);
		if (validateImpl.determineLimit()) {
			return determine(mobile, validateImpl);
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
		if (validateImpl.determine()) {
			return true;
		}
		return false;
	}

}
