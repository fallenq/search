package com.service.tool;

import com.service.config.SparrowConfig;
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

	/**
	 * Get the instance
	 * 
	 * @return
	 */
	public static MobileTool getInstance() {
		return new MobileTool();
	}

	/**
	 * Send mobile message
	 * 
	 * @param mobile
	 * @param content
	 * @param templateId
	 * @return
	 */
	public ResponseModel sendMobileMsg(String mobile, String content, String templateId) {
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
	public ResponseModel sendMobileCode(String mobile, String vcode, String ipAddress) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		// TODO limit send count by ip
		ResponseModel sendResult = sendMobileMsg(mobile, vcode, "");
		if (responseService.isSuccess(sendResult)) {
			RedisTool.getCommonRedis().set(SparrowConfig.MOBILE_VALIDATE_CODE_REDIS_KEY_PREFIX + mobile, vcode, 300);
			responseService.successStatus();
			responseService.setDataValue("vcode", vcode);
		} else {
			responseService.setMessage(sendResult.getMessage());
		}
		return responseService.combineResponse();
	}


}
