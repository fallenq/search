package com.sparrow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.model.ResponseModel;
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
	
	public ResponseModel validateCode(String mobile) {
		return responseService.combineResponse();
	}
	
}
