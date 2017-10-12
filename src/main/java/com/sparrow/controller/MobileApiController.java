package com.sparrow.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.config.SparrowConfig;
import com.service.config.ToolConfig;
import com.service.config.WarnMsgConfig;
import com.service.model.ResponseModel;
import com.service.tool.CommonTool;
import com.service.tool.MobileTool;
import com.service.tool.impl.ResponseImpl;
import com.sparrow.common.ValidateTool;
import com.sparrow.common.nozzle.ValidateModelServiceI;

@Controller
@RequestMapping("/api/mobile")
public class MobileApiController {

	/**
	 * Send validate code in sms
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/validate/code", method = RequestMethod.POST)
	public ResponseModel validateCode(HttpServletRequest request) {
		int type = SparrowConfig.MOBILE_VALIDATE_SEND_TYPE;
		String mobile = request.getParameter("mobile");
		String ipAddress = CommonTool.getCLientIp(request);
		ResponseImpl responseService = ResponseImpl.getInstance();
		ValidateTool validateTool = ValidateTool.getInstance();
		ArrayList<String> params = new ArrayList<String>();
		params.add(mobile);
		params.add(ipAddress);
		ValidateModelServiceI validateService = validateTool.getValidateService(type);
		if (validateTool.determine(type, validateService, params)) {
			String validateCode = CommonTool.getValidateNumber(ToolConfig.VALIDATE_CODE_LENGTH_FOUR);
			if (validateCode.length() == 0) {
				responseService.setMessage(WarnMsgConfig.getCommonValue(WarnMsgConfig.COMMON_SUBMIT_ERROR));
			} else {
				ResponseModel sendResponse = MobileTool.getInstance().sendMobileCode(mobile, validateCode);
				if (responseService.isSuccess(sendResponse)) {
					validateService.setRedisValue(validateCode);
					responseService.successStatus();
				} else {
					responseService.setMessage(sendResponse.getMessage());
				}
			}
		} else {
			responseService.setMessage(WarnMsgConfig.getSparrowValue(WarnMsgConfig.SPARROW_USER_MOBILE_SENDED));
		}
		return responseService.combineResponse();
	}

}
