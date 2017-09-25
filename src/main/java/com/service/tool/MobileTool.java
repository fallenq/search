package com.service.tool;

import com.service.config.ToolConfig;
import com.service.model.ResponseModel;
import com.service.tool.impl.ResponseImpl;

public class MobileTool {

	/**
	 * Get validate code in number limited by length
	 * 
	 * @param length
	 * @return
	 */
	public static String getValidateNumber(int length) {
		if (length == 0) {
			length = ToolConfig.VALIDATE_CODE_LENGTH_DEFAULT;
		}
		String validateCode = "";
		for (int countIndex = 0; countIndex < length; countIndex++) {
			validateCode += String.valueOf(CommonTool.createRandomInt(0, 9));
		}
		return validateCode;
	}
	
	/**
	 * Compare mobile code
	 * 
	 * @param vcode
	 * @return
	 */
	public static boolean compareMobileCode(String vcode) {
		boolean isPass = false;
		return isPass;
	}
	
	/**
	 * Send validate code to mobile
	 * 
	 * @param mobile
	 * @param vcode
	 * @return
	 */
	public static ResponseModel sendMobileCode(String mobile, String vcode) {
		ResponseImpl response = ResponseImpl.getInstance();
		return response.combineResponse();
	}

}
