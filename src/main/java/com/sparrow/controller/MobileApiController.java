package com.sparrow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.config.ToolConfig;
import com.service.config.WarnMsgConfig;
import com.service.model.ResponseModel;
import com.service.tool.MobileTool;
import com.service.tool.nozzle.ResponseServiceI;

@Controller
@RequestMapping("/api/mobile")
public class MobileApiController {

	private ResponseServiceI responseService;

	public ResponseServiceI getRepsonseService() {
		return responseService;
	}

	@Autowired
	public void setRepsonseService(ResponseServiceI responseService) {
		this.responseService = responseService;
	}
	
	@ResponseBody
	@RequestMapping(value = "/validate/code", method = RequestMethod.POST)
	public ResponseModel validateCode(String mobile) {
		String validateCode = MobileTool.getValidateNumber(ToolConfig.VALIDATE_CODE_LENGTH_FOUR);
		if (validateCode.length() == 0) {
			responseService.setMessage(WarnMsgConfig.getCommonValue(WarnMsgConfig.COMMON_SUBMIT_ERROR));
		} else {
			ResponseModel sendResponse = MobileTool.sendMobileCode(mobile, validateCode);
			if (responseService.isSuccess(sendResponse)) {
				responseService.successStatus();
			} else {
				responseService.setMessage(sendResponse.getMessage());
			}
		}
		return responseService.combineResponse();
	}
	
}
