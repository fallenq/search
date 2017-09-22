package com.sparrow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.model.ResponseModel;
import com.service.tool.ResponseTool;

@Controller
@RequestMapping("/api/sparrow/test")
public class TestApiController {

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public ResponseModel test(HttpServletRequest request) {
		return ResponseTool.getInstance().combineResponse();
	}

	@ResponseBody
	@RequestMapping(value = "/test/session", method = RequestMethod.POST)
	public ResponseModel testSession(HttpServletRequest request) {
		ResponseTool response = ResponseTool.getInstance();
		return response.combineResponse();
	}
}
