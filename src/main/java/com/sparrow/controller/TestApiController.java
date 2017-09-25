package com.sparrow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.config.WarnMsgConfig;
import com.service.model.ResponseModel;
import com.service.tool.nozzle.ResponseServiceI;

@Controller
@RequestMapping("/api/sparrow/test")
public class TestApiController {

	@Autowired
	private ResponseServiceI responseService;

	@ResponseBody
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ResponseModel test(HttpServletRequest request) {
		responseService.setMessage(WarnMsgConfig.getCommonValue(WarnMsgConfig.COMMON_SUBMIT_ERROR));
		return responseService.combineResponse();
	}

	@ResponseBody
	@RequestMapping(value = "/session", method = RequestMethod.POST)
	public ResponseModel testSession(HttpServletRequest request) {
		return responseService.combineResponse();
	}
}
