package com.sparrow.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.sparrow.nozzle.SpUserServiceI;
import com.service.tool.ResponseTool;
import com.sparrow.entity.SparrowUser;

@Controller
@RequestMapping("/api/sparrow/user")
public class UserApiController {

	private SpUserServiceI userService;

	public SpUserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(SpUserServiceI userService) {
		this.userService = userService;
	}

	/**
	 * Check the user's info for login
	 * 
	 * @param username
	 * @param password
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login/check", method = RequestMethod.POST)
	public Map<String, Object> loginCheck(String username, String password, Integer type) {
		boolean isPass = false;
		ResponseTool response = ResponseTool.getInstance();
		response.setStatus(ResponseTool.FAILURE);
		SparrowUser sparrowUser = userService.getUserByParams(username, type);
		try {
			isPass = userService.compareUserLoginPwd(sparrowUser, password);
			if (isPass) {
				response.setStatus(ResponseTool.SUCCESS);
			} else {
				response.setMessage("用户名与密码不一致");
			}
		} catch (Exception e) {
			response.setMessage("账号不存在");
		}
		return response.combineMap();
	}

	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Map<String, Object> register() {
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public Map<String, Object> test() {
		ResponseTool response = ResponseTool.getInstance();
		response.setStatus(ResponseTool.FAILURE);
		return response.combineMap();
	}

}
