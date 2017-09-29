package com.service.tool;

import com.service.model.ResponseModel;
import com.service.tool.impl.RedisImpl;
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

	/**
	 * Send mobile message
	 * 
	 * @param mobile
	 * @param content
	 * @param templateId
	 * @return
	 */
	public static ResponseModel sendMobileMsg(String mobile, String content, String templateId) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		responseService.successStatus();
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
		RedisImpl redisImpl = RedisImpl.getInstance();
		// TODO limit send count by ip
		ResponseModel sendResult = MobileTool.sendMobileMsg(mobile, vcode, "");
		if (responseService.isSuccess(sendResult)) {
//			redisImpl.
			responseService.successStatus();
			responseService.setDataValue("vcode", vcode);
		} else {
			responseService.setMessage(sendResult.getMessage());
		}
		return responseService.combineResponse();
	}

}
