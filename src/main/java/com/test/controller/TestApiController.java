package com.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.service.config.ServiceConfig;
import com.service.model.LoginInfoModel;
import com.service.model.ResponseModel;
import com.service.tool.SessionTool;
import com.service.tool.impl.ResponseImpl;
import com.service.tool.nozzle.ResponseServiceI;

@RestController
@RequestMapping("/api/test")
public class TestApiController {

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ResponseModel test(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		return responseService.combineResponse();
	}

	@RequestMapping(value = "/session", method = RequestMethod.GET)
	public ResponseModel testSession(HttpServletRequest request) {
//		SessionTool tool = SessionTool.getInstance(request);
		ResponseImpl responseService = ResponseImpl.getInstance();
//		tool.setSessionParam(ServiceConfig.USER_LOGIN_INFO, new LoginInfoModel(1, "test"));
//		responseService.setDataValue("test", new LoginInfoModel(1, "test"));
//		responseService.setDataValue("sessionid", tool.getSessionId());
		responseService.successStatus();
		return responseService.combineResponse();
	}
}
