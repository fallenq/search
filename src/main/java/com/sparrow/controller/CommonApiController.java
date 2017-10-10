package com.sparrow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.model.ResponseModel;
import com.service.tool.CommonTool;
import com.service.tool.impl.ResponseImpl;
import com.sparrow.common.AddressTool;
import com.sparrow.common.impl.LoginCodeValidateImpl;
import com.sparrow.common.impl.MobileAccessValidateImpl;

@Controller
@RequestMapping("/api/common")
public class CommonApiController {

	/**
	 * Get login validate
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login/validate", method = RequestMethod.POST)
	public ResponseModel loginValidate(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		String ipAddress = CommonTool.getCLientIp(request);
		if (AddressTool.getInstance().determineIpLimit(ipAddress, MobileAccessValidateImpl.getInstance(ipAddress))) {
			responseService.successStatus();
		}
		return responseService.combineResponse();
	}

	/**
	 * Get mobile code validate
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mobile/validate", method = RequestMethod.POST)
	public ResponseModel mobileValidate(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		String ipAddress = CommonTool.getCLientIp(request);
		if (AddressTool.getInstance().determineIpLimit(ipAddress, LoginCodeValidateImpl.getInstance(ipAddress))) {
			responseService.successStatus();
		}
		return responseService.combineResponse();
	}

}
