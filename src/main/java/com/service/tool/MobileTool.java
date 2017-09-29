package com.service.tool;

import com.service.model.ResponseModel;
import com.service.tool.impl.ResponseImpl;

public class MobileTool {

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

	public static ResponseModel sendMobileMsg() {
		ResponseImpl responseService = ResponseImpl.getInstance();
		return responseService.combineResponse();
	}

	/**
	 * Send validate code to mobile
	 * 
	 * @param mobile
	 * @param vcode
	 * @param ipAddress
	 * @return
	 */
	public static ResponseModel sendMobileCode(String mobile, String vcode, String ipAddress) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		ResponseModel sendModel = MobileTool.sendMobileMsg();
		if (responseService.isSuccess(sendModel)) {
			responseService.successStatus();
		} else {
			responseService.setMessage(sendModel.getMessage());
		}
		return responseService.combineResponse();
	}

}
