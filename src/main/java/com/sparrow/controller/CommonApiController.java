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
		if (AddressTool.determineLoginIpLimit(CommonTool.getCLientIp(request), 1)) {
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
		if (AddressTool.determineMobileIpLimit(CommonTool.getCLientIp(request))) {
			responseService.successStatus();
		}
		return responseService.combineResponse();
	}

}
