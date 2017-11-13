package com.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.service.model.ResponseModel;
import com.service.tool.impl.ResponseImpl;

@RestController
@RequestMapping("/api/test")
public class TestApiController {

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ResponseModel test(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		return responseService.combineResponse();
	}

	@RequestMapping(value = "/session", method = RequestMethod.POST)
	public ResponseModel testSession(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		responseService.successStatus();
		return responseService.combineResponse();
	}
}
