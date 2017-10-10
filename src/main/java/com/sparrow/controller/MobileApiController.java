package com.sparrow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.config.ToolConfig;
import com.service.config.WarnMsgConfig;
import com.service.model.ResponseModel;
import com.service.tool.CommonTool;
import com.service.tool.MobileTool;
import com.service.tool.impl.ResponseImpl;
import com.sparrow.common.AddressTool;
import com.sparrow.common.impl.MobileSendValidateImpl;

@Controller
@RequestMapping("/api/mobile")
public class MobileApiController {

	/**
	 * Send valiate code in sms
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/validate/code", method = RequestMethod.POST)
	public ResponseModel validateCode(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		String mobile = request.getParameter("mobile");
		MobileSendValidateImpl validateService = MobileSendValidateImpl.getInstance();
		if (AddressTool.getInstance().determineIpLimit(mobile, CommonTool.getCLientIp(request), validateService)) {
			String validateCode = CommonTool.getValidateNumber(ToolConfig.VALIDATE_CODE_LENGTH_FOUR);
			if (validateCode.length() == 0) {
				responseService.setMessage(WarnMsgConfig.getCommonValue(WarnMsgConfig.COMMON_SUBMIT_ERROR));
			} else {
				validateService.setRedisValue(validateCode);
				ResponseModel sendResponse = MobileTool.getInstance().sendMobileCode(mobile, validateCode);
				if (responseService.isSuccess(sendResponse)) {
					responseService.successStatus();
				} else {
					validateService.removeRedisValue();
					responseService.setMessage(sendResponse.getMessage());
				}
			}
		} else {
			responseService.setMessage(WarnMsgConfig.getSparrowValue(WarnMsgConfig.SPARROW_USER_MOBILE_SENDED));
		}
		return responseService.combineResponse();
	}

}
