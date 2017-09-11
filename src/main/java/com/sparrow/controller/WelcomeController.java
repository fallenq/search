package com.sparrow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;

import com.service.dao.nozzle.SparrowTestServiceI;
//import com.service.tool.ResponseTool;
import com.sparrow.dao.model.SparrowTest;

@Controller
@RequestMapping("/sprrow/welcome")
public class WelcomeController {

	private SparrowTestServiceI sparrowTestService;
	
	public SparrowTestServiceI getSparrowTestServiceI() {
		return sparrowTestService;
	}

	@Autowired
	public void setSparrowTestServiceI(SparrowTestServiceI sparrowTestService) {
		this.sparrowTestService = sparrowTestService;
	}

//	@ResponseBody
//	@RequestMapping("/ajax")
//	public Map<String, Object> testAjax()
//	{
//		SparrowTest sparrowTest = sparrowTestService.selectByPrimaryKey(1);
//		String testValue = "";
//		try {
//			testValue = sparrowTest.getName();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("test");
//			e.printStackTrace();
//		}
//		ResponseTool response = ResponseTool.getInstance();
//		response.setData("key1", testValue);
//		return response.combineMap(200, null, null);
//	}

	@RequestMapping("/test")
	public String test(Model model)
	{
		SparrowTest sparrowTest = sparrowTestService.selectByPrimaryKey(1);
		model.addAttribute("test", sparrowTest);
		return "hello";
	}
}
