package com.sparrow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.service.tool.nozzle.RedisServiceI;

@Controller
@RequestMapping("/api/mobile")
public class MobileApiController {
	
	@Autowired
	private RedisServiceI redisService;
	
	@ResponseBody
	@RequestMapping(value = "/validate/code", method = RequestMethod.POST)
	public ResponseModel validateCode(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		String mobile = request.getParameter("mobile");
		String validateCode = CommonTool.getValidateNumber(ToolConfig.VALIDATE_CODE_LENGTH_FOUR);
		if (validateCode.length() == 0) {
			responseService.setMessage(WarnMsgConfig.getCommonValue(WarnMsgConfig.COMMON_SUBMIT_ERROR));
		} else {
			ResponseModel sendResponse = MobileTool.getInstance().sendMobileCode(mobile, validateCode, CommonTool.getCLientIp(request), redisService);
			if (responseService.isSuccess(sendResponse)) {
				responseService.successStatus();
			} else {
				responseService.setMessage(sendResponse.getMessage());
			}
		}
		return responseService.combineResponse();
	}
	
}
