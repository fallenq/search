package com.test.controller;

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

@Controller
@RequestMapping("/api/test")
public class TestApiController {

	@ResponseBody
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ResponseModel test(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		return responseService.combineResponse();
	}

	@ResponseBody
	@RequestMapping(value = "/session", method = RequestMethod.POST)
	public ResponseModel testSession(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		responseService.successStatus();
		return responseService.combineResponse();
	}
}
