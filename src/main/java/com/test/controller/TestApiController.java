package com.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.service.config.ServiceConfig;
//import com.service.model.LoginInfoModel;
import com.service.model.ResponseModel;
//import com.service.sparrow.nozzle.SpUserFuncServiceI;
//import com.service.tool.SessionTool;
import com.service.tool.impl.ResponseImpl;

@RestController
@RequestMapping("/api/test")
public class TestApiController {
	@Autowired
//	private SpUserFuncServiceI userFuncService;

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ResponseModel test(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		return responseService.combineResponse();
	}

	@RequestMapping(value = "/session", method = RequestMethod.GET)
	public ResponseModel testSession(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		responseService.setDataValue("test", ServiceConfig.USER_LOGIN_INFO);
//		SessionTool sessionTool = SessionTool.getInstance(request);
//		userFuncService.setLoginInfo(SessionTool.getInstance(request), new LoginInfoModel(1, "test"));
//		responseService.setDataValue("model", sessionTool.getSessionRedisObject(ServiceConfig.USER_LOGIN_INFO));
		responseService.successStatus();
		return responseService.combineResponse();
	}
}
